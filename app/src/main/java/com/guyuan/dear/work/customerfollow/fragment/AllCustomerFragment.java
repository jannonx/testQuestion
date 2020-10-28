package com.guyuan.dear.work.customerfollow.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.customerfollow.adapter.AllCustomerAdapter;
import com.guyuan.dear.work.customerfollow.data.WorkCustomerViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--客户跟进--所有客户
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class AllCustomerFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, WorkCustomerViewModel> {

    public static final String TAG = AllCustomerFragment.class.getSimpleName();

    public static AllCustomerFragment newInstance() {

        Bundle args = new Bundle();

        AllCustomerFragment fragment = new AllCustomerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        AllCustomerAdapter allCustomerAdapter = new AllCustomerAdapter(getContext(),
                listData, R.layout.item_work_all_customer);
        adapter = new BaseRecyclerViewAdapter(allCustomerAdapter);
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


    @Override
    protected int getVariableId() {
        return 0;
    }
}
