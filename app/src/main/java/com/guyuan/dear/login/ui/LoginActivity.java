package com.guyuan.dear.login.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithoutToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.data.LoginViewModel;
import com.guyuan.dear.utils.ActivityUtils;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * created by tl
 * created at 2020/8/24
 */
@AndroidEntryPoint
public class LoginActivity extends BaseNoToolbarActivity<ActivityWithoutToolbarBinding, LoginViewModel> {

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_without_toolbar;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        LoginFragment loginFragment = LoginFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, loginFragment, R.id.container, LoginFragment.TAG);
    }


    @Override
    public LoginViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

}
