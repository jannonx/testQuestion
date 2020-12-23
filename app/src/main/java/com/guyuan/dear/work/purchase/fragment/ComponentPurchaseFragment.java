package com.guyuan.dear.work.purchase.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.purchase.adapter.ComponentPurchaseAdapter;
import com.guyuan.dear.work.purchase.data.WorkPurchaseViewModel;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--采购计划--采购关键零部件
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ComponentPurchaseFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, WorkPurchaseViewModel> {

    public static final String TAG = ComponentPurchaseFragment.class.getSimpleName();

    public static ComponentPurchaseFragment newInstance() {

        Bundle args = new Bundle();

        ComponentPurchaseFragment fragment = new ComponentPurchaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        ComponentPurchaseAdapter componentPurchaseAdapter = new ComponentPurchaseAdapter(getContext(),
                listData, R.layout.item_work_component_purchase);
        adapter = new BaseRecyclerViewAdapter(componentPurchaseAdapter);
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
