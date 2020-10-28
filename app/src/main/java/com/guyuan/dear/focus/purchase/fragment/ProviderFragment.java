package com.guyuan.dear.focus.purchase.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.purchase.adapter.ProviderAdapter;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--采购--供应商查询
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProviderFragment extends BaseListSearchFragment<SimpleTabBean, FragmentListBinding, FocusPurchaseViewModel> {

    public static final String TAG = ProviderFragment.class.getSimpleName();

    public static ProviderFragment newInstance() {

        Bundle args = new Bundle();

        ProviderFragment fragment = new ProviderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        ProviderAdapter providerAdapter = new ProviderAdapter(getContext(),
                listData, R.layout.item_focus_provider);
        adapter = new BaseRecyclerViewAdapter(providerAdapter);
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
