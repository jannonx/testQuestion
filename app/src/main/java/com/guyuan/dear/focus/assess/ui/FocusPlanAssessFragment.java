package com.guyuan.dear.focus.assess.ui;

import android.os.Bundle;

import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 18:05
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusPlanAssessFragment extends BaseListFragment<Object, FragmentListBinding> {

    public static final String TAG = "FocusPlanAssessFragment";

    public static FocusPlanAssessFragment newInstance() {

        Bundle args = new Bundle();

        FocusPlanAssessFragment fragment = new FocusPlanAssessFragment();
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
