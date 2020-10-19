package com.guyuan.dear.focus.stock.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:51
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusStockTotalFragment extends BaseListFragment<Object,FragmentListBinding> {
    public static final String TAG = "FocusStockTotalFragment";

    public static FocusStockTotalFragment newInstance() {

        Bundle args = new Bundle();

        FocusStockTotalFragment fragment = new FocusStockTotalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

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
