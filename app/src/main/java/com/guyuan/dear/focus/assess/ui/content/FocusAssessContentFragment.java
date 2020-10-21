package com.guyuan.dear.focus.assess.ui.content;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessContentBinding;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessContentFragment extends BaseDataBindingFragment<FragmentFocusAssessContentBinding> {

    public static final String TAG = "FocusAssessContentFragment";

    public static FocusAssessContentFragment newInstance() {

        Bundle args = new Bundle();

        FocusAssessContentFragment fragment = new FocusAssessContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_content;
    }

    @Override
    protected void initialization() {

    }
}