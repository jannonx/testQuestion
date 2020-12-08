package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.approve.fragment.ApplyApproveByMeFragment;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 掌上办公--审批--我的审批--待我审批详情页面
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ApplyApproveByMeActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, ApproveViewModel> {


    private ApplyApproveByMeFragment fragment;

    public static void start(Context context, ApplyBean apply) {
        Intent starter = new Intent(context, ApplyApproveByMeActivity.class);
        starter.putExtra(ConstantValue.KEY_CONTENT, apply);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ApplyBean applyBean = (ApplyBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        setTitleCenter("待我审批");
        fragment = ApplyApproveByMeFragment.newInstant(applyBean);
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.fragment_container,
                ApplyApproveByMeFragment.TAG);


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
