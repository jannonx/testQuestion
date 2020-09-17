package com.guyuan.dear.work.projectsite.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.fragment.CheckGoodsFragment;
import com.guyuan.dear.work.projectsite.fragment.CheckSafetyFragment;
import com.guyuan.dear.work.projectsite.fragment.CustomerAcceptanceFragment;
import com.guyuan.dear.work.projectsite.fragment.SiteExplorationFragment;
import com.guyuan.dear.utils.ConstantValue;

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

    private CheckGoodsFragment checkGoodsFragment;
    private CheckSafetyFragment checkSafetyFragment;
    private SiteExplorationFragment siteExplorationFragment;
    private CustomerAcceptanceFragment customerAcceptanceFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkProjectSiteActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_project_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        checkGoodsFragment = CheckGoodsFragment.newInstance();
        checkSafetyFragment = CheckSafetyFragment.newInstance();
        siteExplorationFragment = SiteExplorationFragment.newInstance();
        customerAcceptanceFragment = CustomerAcceptanceFragment.newInstance();
        fragmentList.add(checkGoodsFragment);
        fragmentList.add(checkSafetyFragment);
        fragmentList.add(siteExplorationFragment);
        fragmentList.add(customerAcceptanceFragment);
        return fragmentList;
    }

    @Override
    protected int getCustomViewId() {
        return R.layout.tab_common;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabSelectedIconList() {
        List<Integer> selectList = new ArrayList<>();
        selectList.add(R.mipmap.device_maintain);
        selectList.add(R.mipmap.device_fix);
        selectList.add(R.mipmap.device_maintain);
        selectList.add(R.mipmap.device_fix);
        return selectList;
    }

    @Override
    protected List<Integer> setTabUnselectedIconList() {
        List<Integer> unselected = new ArrayList<>();
        unselected.add(R.mipmap.device_maintain);
        unselected.add(R.mipmap.device_fix);
        unselected.add(R.mipmap.device_maintain);
        unselected.add(R.mipmap.device_fix);
        return unselected;
    }

    @Override
    public WorkProjectSiteViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
