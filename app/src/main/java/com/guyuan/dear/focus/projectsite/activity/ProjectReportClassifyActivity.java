package com.guyuan.dear.focus.projectsite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.type.ProjectReportType;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.focus.projectsite.fragment.ProjectReportClassifyFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--工程现场
 *               --现场勘查报告/货物清点报告/安全排查报告/安装调试报告/客户验收报告/
 *               --列表页面
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ProjectReportClassifyActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProjectSiteViewModel> {

    public static void start(Context context, ProjectReportType type) {
        Intent intent = new Intent(context, ProjectReportClassifyActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, type);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ProjectReportType reportType = (ProjectReportType) getIntent().getSerializableExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(reportType.getDes());
        ProjectReportClassifyFragment mFragment = ProjectReportClassifyFragment.newInstance(reportType);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                ProjectReportClassifyFragment.TAG);
    }

    @Override
    public FocusProjectSiteViewModel getViewModel() {
        return viewModel;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }


}
