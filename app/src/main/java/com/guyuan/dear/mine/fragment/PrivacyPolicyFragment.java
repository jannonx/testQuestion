package com.guyuan.dear.mine.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentPrivacyPolicyBinding;

/**
 * @description: 我的--隐私政策
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class PrivacyPolicyFragment extends BaseDataBindingFragment<FragmentPrivacyPolicyBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static PrivacyPolicyFragment newInstance() {
        Bundle args = new Bundle();
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_privacy_policy;
    }

    @Override
    protected void initialization() {

    }
}
