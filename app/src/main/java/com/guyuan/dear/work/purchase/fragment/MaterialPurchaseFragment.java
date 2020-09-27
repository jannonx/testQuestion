package com.guyuan.dear.work.purchase.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.work.purchase.adapter.MaterialPurchaseAdapter;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--采购计划--采购原材料
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MaterialPurchaseFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding> {

    public static final String TAG = MaterialPurchaseFragment.class.getSimpleName();

    public static MaterialPurchaseFragment newInstance() {

        Bundle args = new Bundle();

        MaterialPurchaseFragment fragment = new MaterialPurchaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        MaterialPurchaseAdapter materialPurchaseAdapter = new MaterialPurchaseAdapter(getContext(),
                listData, R.layout.item_work_material_purchase);
        adapter = new BaseRecyclerViewAdapter(materialPurchaseAdapter);
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
