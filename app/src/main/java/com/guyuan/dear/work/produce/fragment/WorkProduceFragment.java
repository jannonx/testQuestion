package com.guyuan.dear.work.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.bean.EventProduceListRefresh;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ListProduceRequestBody;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.produce.data.WorkProduceViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产计划--分类查询
 * @author: 许建宁
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceFragment extends BaseListSearchFragment<FocusProduceBean, FragmentListBinding, WorkProduceViewModel> {

    public static final String TAG = WorkProduceFragment.class.getSimpleName();
    public boolean isFirstLoad;

    public static WorkProduceFragment newInstance() {
        Bundle args = new Bundle();
        WorkProduceFragment fragment = new WorkProduceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        searchBar.setHint("输入产品名称、产品代号");
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.showLog("listData=" + (listData.get(position) == null));
                FocusProduceDetailActivity.start(getContext(), listData.get(position), true);
            }
        });
        viewModel.getProduceList(getListRequestBody(true));

        viewModel.getListEvent().observe(getActivity(), new Observer<RefreshBean<FocusProduceBean>>() {
            @Override
            public void onChanged(RefreshBean<FocusProduceBean> dataRefreshBean) {
                LogUtils.showLog("size=" + dataRefreshBean.getContent().size());
                setListData(dataRefreshBean.getContent());
            }
        });

    }

    @Override
    protected void refresh() {
        viewModel.getProduceList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getProduceList(getListRequestBody(false));
    }

    @Override
    public void onResume() {
        super.onResume();
        //第一次加载不刷新，再次进入页面刷新数据
        if (!isFirstLoad) {
            refresh();
        }
        isFirstLoad = false;
    }

    /**
     * 生产列表请求参数配置
     *
     * @param isRefresh 是否刷新
     * @return
     */
    protected RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListProduceRequestBody body = new ListProduceRequestBody();
        ListProduceRequestBody.FiltersBean filtersBean = new ListProduceRequestBody.FiltersBean();
        filtersBean.setName(searchContent);
        filtersBean.setStatus(null);
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshListMessage(EventProduceListRefresh event) {
        viewModel.getProduceList(getListRequestBody(true));
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
