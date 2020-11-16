package com.guyuan.dear.focus.purchase.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusPurchaseOverviewBinding;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:55
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseOverviewFragment extends BaseDataBindingFragment<FragmentFocusPurchaseOverviewBinding, FocusPurchaseViewModel> {

    public static final String TAG = "FocusPurchaseOverviewFragment";

    public static FocusPurchaseOverviewFragment newInstance() {

        Bundle args = new Bundle();

        FocusPurchaseOverviewFragment fragment = new FocusPurchaseOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_purchase_overview;
    }

    @Override
    protected void initialization() {

    }
}