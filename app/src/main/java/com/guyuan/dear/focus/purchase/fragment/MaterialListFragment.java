package com.guyuan.dear.focus.purchase.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.purchase.adapter.MaterialListAdapter;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--工程现场--原材料列表
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MaterialListFragment extends BaseListFragment<SimpleTabBean, FragmentListBinding, FocusPurchaseViewModel> {

    public static final String TAG = MaterialListFragment.class.getSimpleName();

    public static MaterialListFragment newInstance() {

        Bundle args = new Bundle();

        MaterialListFragment fragment = new MaterialListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        MaterialListAdapter materialListAdapter = new MaterialListAdapter(getContext(),
                listData, R.layout.item_focus_material_list);
        adapter = new BaseRecyclerViewAdapter(materialListAdapter);
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
