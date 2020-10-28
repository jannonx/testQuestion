package com.guyuan.dear.focus.assess.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessDetailBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:27
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessDetailFragment extends BaseDataBindingFragment<FragmentFocusAssessDetailBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusAssessDetailFragment";

    public static FocusAssessDetailFragment newInstance() {

        Bundle args = new Bundle();

        FocusAssessDetailFragment fragment = new FocusAssessDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_detail;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}