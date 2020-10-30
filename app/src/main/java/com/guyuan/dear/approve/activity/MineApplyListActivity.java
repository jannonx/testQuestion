package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.approve.fragment.MineApplyListFragment;
import com.guyuan.dear.utils.ActivityUtils;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 掌上办公--审批--我发起的
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class MineApplyListActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, ApproveViewModel> {

    private MineApplyListFragment fragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineApplyListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("我发起的");
        fragment = MineApplyListFragment.getInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.fragment_container,
                MineApplyListFragment.TAG);

    }


    @Override
    public ApproveViewModel getViewModel() {
        return viewModel;
    }




    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
