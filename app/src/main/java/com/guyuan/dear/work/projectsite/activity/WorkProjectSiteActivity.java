package com.guyuan.dear.work.projectsite.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.fragment.WorkCheckSafeListFragment;
import com.guyuan.dear.work.projectsite.fragment.WorkProjectReportListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的工作--工程现场
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkProjectSiteActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkProjectSiteViewModel> {


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkProjectSiteActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_project_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(WorkProjectReportListFragment.newInstance(ProjectReportType.TYPE_SITE_EXPLORATION));
        fragmentList.add(WorkProjectReportListFragment.newInstance(ProjectReportType.TYPE_CHECK_GOODS));
        fragmentList.add(WorkProjectReportListFragment.newInstance(ProjectReportType.TYPE_CHECK_SAFE));
        fragmentList.add(WorkProjectReportListFragment.newInstance(ProjectReportType.TYPE_INSTALLATION_DEBUG));
        fragmentList.add(WorkProjectReportListFragment.newInstance(ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE));
        return fragmentList;
    }


    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_work_site_explore_selector);
        tabDrawableList.add(R.drawable.tab_work_check_goods_selector);
        tabDrawableList.add(R.drawable.tab_work_check_safe_selector);
        tabDrawableList.add(R.drawable.tab_work_install_debug_selector);
        tabDrawableList.add(R.drawable.tab_work_customer_acceptance_selector);
        return tabDrawableList;
    }


    @Override
    public WorkProjectSiteViewModel getViewModel() {
        return viewModel;
    }


}
