package com.guyuan.dear.mine.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.databinding.FragmentSafetyCenterBinding;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SafetyCenterFragment extends BaseDataBindingFragment<FragmentSafetyCenterBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static SafetyCenterFragment newInstance() {
        Bundle args = new Bundle();
        SafetyCenterFragment fragment = new SafetyCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_safety_center;
    }

    @Override
    protected void initialization() {

    }
}
