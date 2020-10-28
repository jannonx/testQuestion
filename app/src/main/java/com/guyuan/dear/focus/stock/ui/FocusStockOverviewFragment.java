package com.guyuan.dear.focus.stock.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusStockOverviewBinding;
import com.guyuan.dear.focus.stock.data.FocusStockViewModel;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:46
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusStockOverviewFragment extends BaseDataBindingFragment<FragmentFocusStockOverviewBinding, FocusStockViewModel> {

    public static final String TAG = "FocusStockOverviewFragment";

    public static FocusStockOverviewFragment newInstance() {

        Bundle args = new Bundle();

        FocusStockOverviewFragment fragment = new FocusStockOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_stock_overview;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
