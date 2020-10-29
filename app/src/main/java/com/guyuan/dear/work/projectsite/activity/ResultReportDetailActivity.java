package com.guyuan.dear.work.projectsite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.fragment.WorkReportDetailFragment;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的工作--工程现场--详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ResultReportDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkProjectSiteViewModel> {

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, ResultReportDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
        WorkReportDetailFragment mFragment = WorkReportDetailFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                WorkReportDetailFragment.TAG);
    }

    @Override
    public WorkProjectSiteViewModel getViewModel() {
        return viewModel;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
