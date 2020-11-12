package com.guyuan.dear.work.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ListProduceRequestBody;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.produce.data.WorkProduceViewModel;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产计划--分类查询
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceFragment extends BaseListSearchFragment<FocusProduceBean, FragmentListBinding, WorkProduceViewModel> {

    public static final String TAG = WorkProduceFragment.class.getSimpleName();

    public static WorkProduceFragment newInstance() {
        Bundle args = new Bundle();
        WorkProduceFragment fragment = new WorkProduceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                FocusProduceDetailActivity.start(getContext());
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
    protected void editEmptyChange() {
        viewModel.getProduceList(getListRequestBody(true));
    }

    @Override
    protected void onSearch(String text) {
        viewModel.getProduceList(getListRequestBody(true));
    }

    @Override
    protected void refresh() {
        viewModel.getProduceList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getProduceList(getListRequestBody(false));
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
        filtersBean.setName(etSearch.getText().toString());
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
