package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.client.activity.FocusClientDetailActivity;
import com.guyuan.dear.focus.client.adapter.ClientListAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;


import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注-客户
 * @author: 许建宁
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientFragment extends BaseListSearchFragment<ClientCompanyBean, FragmentListBinding, FocusClientViewModel> {

    public static final String TAG = FocusClientFragment.class.getSimpleName();

    public static FocusClientFragment newInstance() {
        Bundle args = new Bundle();
        FocusClientFragment fragment = new FocusClientFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        ClientListAdapter listAdapter = new ClientListAdapter(getContext(), listData,
                R.layout.item_focus_client);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusClientDetailActivity.start(getContext(), listData.get(position));
            }
        });

        initData();
    }

    /**
     * 设置数据
     */
    private void initData() {
        searchBar.setHint("输入客户名称、手机号");
        viewModel.getClientList(getListRequestBody(true));
        viewModel.getClientListEvent().observe(getActivity(), new Observer<RefreshBean<ClientCompanyBean>>() {
            @Override
            public void onChanged(RefreshBean<ClientCompanyBean> dataRefreshBean) {
                LogUtils.showLog("dataRefreshBean=" + dataRefreshBean.getContent().size());
                setListData(dataRefreshBean.getContent());
            }
        });
    }


    /**
     * 客户列表请求参数配置
     *
     * @param isRefresh 是否刷新
     * @return
     */
    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setName(searchContent);
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }



    @Override
    protected void refresh() {
        viewModel.getClientList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getClientList(getListRequestBody(false));
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
}
