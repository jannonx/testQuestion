package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.IntentBean;
import com.guyuan.dear.focus.produce.bean.ListProduceRequestBody;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产计划--分类查询
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceClassifyFragment extends BaseListFragment<FocusProduceBean, FragmentListBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceClassifyFragment.class.getSimpleName();
    private IntentBean intentBean;

    public static FocusProduceClassifyFragment newInstance(IntentBean data) {
        Bundle bundle = new Bundle();
        FocusProduceClassifyFragment fragment = new FocusProduceClassifyFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        intentBean = (IntentBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        FocusProduceAdapter listAdapter = new FocusProduceAdapter(getContext(), listData,
                R.layout.item_focus_produce);
//        searchBar.setHint("输入产品名称、产品代号");
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusProduceDetailActivity.start(getContext(), listData.get(position), false);
            }
        });
        viewModel.getProduceListByStatus(getListRequestBody(true));
        viewModel.getStatusEvent().observe(getActivity(), new Observer<RefreshBean<FocusProduceBean>>() {
            @Override
            public void onChanged(RefreshBean<FocusProduceBean> dataRefreshBean) {
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
        viewModel.getProduceListByStatus(getListRequestBody(false));
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


    @Override
    protected int getVariableId() {
        return 0;
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
//        filtersBean.setName(searchContent);
        if (intentBean!=null){
            filtersBean.setStartTime(intentBean.getStartTime());
            filtersBean.setEndTime(intentBean.getEndTime());
            filtersBean.setStatus(intentBean.getType() != null ? intentBean.getType().getCode() : null);
        }
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }
}
