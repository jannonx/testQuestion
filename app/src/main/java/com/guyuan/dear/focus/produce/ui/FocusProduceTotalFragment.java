package com.guyuan.dear.focus.produce.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/10 11:22
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusProduceTotalFragment extends BaseListFragment<Object, FragmentListBinding> {

    public static final String TAG = "FocusProduceTotalFragment";

    public static FocusProduceTotalFragment newInstance() {

        Bundle args = new Bundle();

        FocusProduceTotalFragment fragment = new FocusProduceTotalFragment();
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
