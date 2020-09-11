package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.approve.fragment.ApplyCopyFragment;
import com.guyuan.dear.utils.ActivityUtils;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 掌上办公--审批--抄送我的
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ApplyCopyActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, ApproveViewModel> {


    private ApplyCopyFragment fragment;

    public static void start(Context context) {
        Intent starter = new Intent(context, ApplyCopyActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("抄送我的");
        fragment = ApplyCopyFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.fragment_container,
                ApplyCopyFragment.TAG);

    }


    @Override
    public ApproveViewModel getViewModel() {
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
