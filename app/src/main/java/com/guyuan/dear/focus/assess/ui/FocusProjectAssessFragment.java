package com.guyuan.dear.focus.assess.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 18:07
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusProjectAssessFragment extends BaseListFragment<Object, FragmentListBinding> {

    private static final String TAG = "FocusProjectAssessFragment";

    public static FocusProjectAssessFragment newInstance() {
        Bundle args = new Bundle();
        FocusProjectAssessFragment fragment = new FocusProjectAssessFragment();
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
