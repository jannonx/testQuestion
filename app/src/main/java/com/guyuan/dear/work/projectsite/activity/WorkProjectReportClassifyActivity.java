package com.guyuan.dear.work.projectsite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.fragment.WorkProjectReportListFragment;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的工作--工程现场
 *               --现场勘查报告/货物清点报告/安全排查报告/安装调试报告/客户验收报告/
 *               --列表页面
 * @author: 许建宁
 * @since: 2020/11/18 10:27
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkProjectReportClassifyActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkProjectSiteViewModel> {

    public static void start(Context context, ProjectReportType type) {
        Intent intent = new Intent(context, WorkProjectReportClassifyActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, type);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ProjectReportType reportType = (ProjectReportType) getIntent().getSerializableExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(reportType.getDes());
        WorkProjectReportListFragment mFragment = WorkProjectReportListFragment.newInstance(reportType);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                WorkProjectReportListFragment.TAG);
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
