package com.guyuan.dear.focus.projectsite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailComplexFragment;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.focus.projectsite.fragment.FocusSiteExplorationDetailFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面
 * @author: 许建宁
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusSiteExplorationDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProjectSiteViewModel> {

    public static void start(Context context, ProjectReportType data) {
        Intent intent = new Intent(context, FocusSiteExplorationDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ProjectReportType bean = (ProjectReportType) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText(bean.getDes() + "详情");
        BaseFragment mFragment = FocusSiteExplorationDetailFragment.newInstance(bean);

        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusSiteExplorationDetailFragment.TAG);
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
