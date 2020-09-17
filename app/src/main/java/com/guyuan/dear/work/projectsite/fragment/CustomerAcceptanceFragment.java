package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.projectsite.adapter.CustomerAcceptanceAdapter;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--客户验收
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CustomerAcceptanceFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding> {

    public static final String TAG = CustomerAcceptanceFragment.class.getSimpleName();

    public static CustomerAcceptanceFragment newInstance() {

        Bundle args = new Bundle();

        CustomerAcceptanceFragment fragment = new CustomerAcceptanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        CustomerAcceptanceAdapter customerAcceptanceAdapter = new CustomerAcceptanceAdapter(getContext(),
                listData, R.layout.item_work_customer_acceptance);
        adapter = new BaseRecyclerViewAdapter(customerAcceptanceAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(isPullEnable());
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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


}
