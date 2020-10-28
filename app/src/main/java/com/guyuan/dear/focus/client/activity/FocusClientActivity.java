package com.guyuan.dear.focus.client.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.focus.client.fragment.FocusClientDetailFragment;
import com.guyuan.dear.focus.client.fragment.FocusClientDetailFragment;
import com.guyuan.dear.focus.client.fragment.FocusClientFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusClientActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusClientViewModel> {

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, FocusClientActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
        FocusClientFragment mFragment = FocusClientFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusClientFragment.TAG);
    }

    @Override
    public FocusClientViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
