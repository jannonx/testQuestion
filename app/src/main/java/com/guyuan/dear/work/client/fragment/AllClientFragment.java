package com.guyuan.dear.work.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.work.client.activity.WorkClientActivity;
import com.guyuan.dear.work.client.activity.WorkClientDetailActivity;
import com.guyuan.dear.work.client.adapter.ClientAllAdapter;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class AllClientFragment extends BaseListSearchFragment<ClientCompanyBean, FragmentListBinding, WorkClientViewModel> {

    public static final String TAG = AllClientFragment.class.getSimpleName();
    private WorkClientViewModel viewModel;

    public static AllClientFragment newInstance() {

        Bundle args = new Bundle();

        AllClientFragment fragment = new AllClientFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        etSearch.setHint("输入客户名称");
        for (int i = 0; i < 5; i++) {
            ClientCompanyBean contactBean = new ClientCompanyBean();
            contactBean.setCusName("ClientCompanyBean" + i);
            listData.add(contactBean);
        }
        ClientAllAdapter listAdapter = new ClientAllAdapter(getContext(), listData,
                R.layout.item_work_all_customer);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                WorkClientDetailActivity.start(getContext(), listData.get(position));
            }
        });


        initData();
    }

    private void initData() {
        viewModel.getClientList(getListRequestBody(FIRST_PAGE));
        viewModel.getClientListEvent().observe(getActivity(), new Observer<ResultBean<List<ClientCompanyBean>>>() {
            @Override
            public void onChanged(ResultBean<List<ClientCompanyBean>> dataRefreshBean) {
            }
        });
    }

    private RequestBody getListRequestBody(int pageNum) {
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        body.setFilters(filtersBean);
        body.setPageNum(pageNum);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void onSearch(String text) {
        viewModel.getClientListByName(text);
        viewModel.getClientListEvent().observe(getActivity(), new Observer<ResultBean<List<ClientCompanyBean>>>() {
            @Override
            public void onChanged(ResultBean<List<ClientCompanyBean>> dataRefreshBean) {
            }
        });
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            WorkClientActivity activity = (WorkClientActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
