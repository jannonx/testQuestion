package com.guyuan.dear.mine.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentSystemSetttingBinding;

/**
 * @description: 我的--系统设置
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SystemSettingFragment extends BaseDataBindingFragment<FragmentSystemSetttingBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static SystemSettingFragment newInstance() {
        Bundle args = new Bundle();
        SystemSettingFragment fragment = new SystemSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_system_settting;
    }

    @Override
    protected void initialization() {

    }
}
