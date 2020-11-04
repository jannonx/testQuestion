package com.guyuan.dear.focus.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.client.activity.FocusClientActivity;
import com.guyuan.dear.focus.client.activity.FocusClientDetailActivity;
import com.guyuan.dear.focus.client.adapter.ClientListAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注-客户
 * @author: Jannonx
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
//        for (int i = 0; i < 5; i++) {
//            ClientCompanyBean contactBean = new ClientCompanyBean();
//            contactBean.setCusName("ClientCompanyBean" + i);
//            listData.add(contactBean);
//        }
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

    private void initData() {
        etSearch.setHint("输入客户名称、手机号");
        viewModel.getClientList(getListRequestBody(FIRST_PAGE, null));
        viewModel.getClientListEvent().observe(getActivity(), new Observer<RefreshBean<ClientCompanyBean>>() {
            @Override
            public void onChanged(RefreshBean<ClientCompanyBean> dataRefreshBean) {
                LogUtils.showLog("dataRefreshBean=" + dataRefreshBean.getContent().size());
                setListData(dataRefreshBean.getContent());
            }
        });
    }

    private RequestBody getListRequestBody(int pageNum, String name) {
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setName(name);
        body.setFilters(filtersBean);
        body.setPageNum(pageNum);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void onSearch(String keyWord) {
        currentType = REFRESH;
        viewModel.getClientList(getListRequestBody(FIRST_PAGE, keyWord));
    }

    @Override
    protected void editEmptyChange() {
        currentType = REFRESH;
        viewModel.getClientList(getListRequestBody(FIRST_PAGE, null));
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
    protected int getVariableId() {
        return 0;
    }
}
