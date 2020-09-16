package com.guyuan.dear.focus.assess.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:55
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessOverviewFragment extends BaseDataBindingFragment<FragmentFocusAssessOverviewBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static FocusAssessOverviewFragment newInstance() {
        Bundle args = new Bundle();
        FocusAssessOverviewFragment fragment = new FocusAssessOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_overview;
    }

    @Override
    protected void initialization() {

    }
}
