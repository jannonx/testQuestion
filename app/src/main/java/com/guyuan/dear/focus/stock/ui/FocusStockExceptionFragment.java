package com.guyuan.dear.focus.stock.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.stock.data.FocusStockViewModel;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:50
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusStockExceptionFragment extends BaseListFragment<Object, FragmentListBinding, FocusStockViewModel> {

    public static final String TAG = "FocusStockExceptionFragment";

    public static FocusStockExceptionFragment newInstance() {

        Bundle args = new Bundle();

        FocusStockExceptionFragment fragment = new FocusStockExceptionFragment();
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

    @Override
    protected int getVariableId() {
        return 0;
    }
}
