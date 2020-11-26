package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.ItemFocusSaleRecordBinding;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 15:13
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusSaleRecordFragment extends BaseListFragment<Object, ItemFocusSaleRecordBinding, FocusAfterSaleViewModel> {

    public static FocusSaleRecordFragment newInstance() {

        Bundle args = new Bundle();

        FocusSaleRecordFragment fragment = new FocusSaleRecordFragment();
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