package com.guyuan.dear.focus.purchase.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseListFragment extends BaseListSearchFragment<Object,
        FragmentListSearchBinding, FocusPurchaseViewModel> {

    public static final String TAG = "FocusPurchaseListFragment";

    public static FocusPurchaseListFragment newInstance() {

        Bundle args = new Bundle();

        FocusPurchaseListFragment fragment = new FocusPurchaseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {

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