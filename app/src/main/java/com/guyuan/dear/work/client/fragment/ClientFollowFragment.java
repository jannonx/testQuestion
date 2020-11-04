package com.guyuan.dear.work.client.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.work.client.activity.WorkClientDetailActivity;
import com.guyuan.dear.work.client.adapter.ClientFollowAdapter;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class ClientFollowFragment extends BaseListSearchFragment<ClientCompanyBean, FragmentListBinding,WorkClientViewModel> {

    public static final String TAG = ClientFollowFragment.class.getSimpleName();

    public static ClientFollowFragment newInstance() {
        Bundle args = new Bundle();
        ClientFollowFragment fragment = new ClientFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        etSearch.setHint("输入客户名称");

        ClientFollowAdapter listAdapter = new ClientFollowAdapter(getContext(), listData,
                R.layout.item_work_follow_customer);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                WorkClientDetailActivity.start(getContext(), listData.get(position));
            }
        });

        viewModel.getMyClientList(getListRequestBody(true));
        viewModel.getClientListEvent().observe(getActivity(), new Observer<RefreshBean<ClientCompanyBean>>() {
            @Override
            public void onChanged(RefreshBean<ClientCompanyBean> dataRefreshBean) {
                setListData(dataRefreshBean.getContent());
            }
        });
    }



    @Override
    protected void onSearch(String keyWord) {
        viewModel.getMyClientList(getListRequestBody(true));
    }

    @Override
    protected void editEmptyChange() {
        viewModel.getMyClientList(getListRequestBody(true));
    }

    @Override
    protected void refresh() {
        viewModel.getMyClientList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getMyClientList(getListRequestBody(false));
    }

    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
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
}
