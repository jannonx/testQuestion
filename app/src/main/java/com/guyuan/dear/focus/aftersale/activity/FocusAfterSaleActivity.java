package com.guyuan.dear.focus.aftersale.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.aftersale.fragment.ContractProgressFragment;
import com.guyuan.dear.focus.aftersale.fragment.ProviderFragment;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.ui.FocusAssessOverviewFragment;
import com.guyuan.dear.focus.assess.ui.FocusPlanAssessFragment;
import com.guyuan.dear.focus.assess.ui.FocusProjectAssessFragment;
import com.guyuan.dear.focus.assess.ui.FocusSellAssessFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusAfterSaleActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusAfterSaleViewModel> {

    //    private FocusAssessOverviewFragment overviewFragment;
//    private FocusPlanAssessFragment planAssessFragment;
    private ProviderFragment providerFragment;
    private ContractProgressFragment contractProgressFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusAfterSaleActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_assess_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
//        overviewFragment = FocusAssessOverviewFragment.newInstance();
        providerFragment = ProviderFragment.newInstance();
//        planAssessFragment = FocusPlanAssessFragment.newInstance();
        contractProgressFragment = ContractProgressFragment.newInstance();
        fragmentList.add(providerFragment);
        fragmentList.add(contractProgressFragment);
//        fragmentList.add(planAssessFragment);
//        fragmentList.add(projectAssessFragment);
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
//        selectList.add(R.mipmap.device_maintain);
//        selectList.add(R.mipmap.device_fix);
        return selectList;
    }

    @Override
    protected List<Integer> setTabUnselectedIconList() {
        List<Integer> unselected = new ArrayList<>();
        unselected.add(R.mipmap.device_maintain);
        unselected.add(R.mipmap.device_fix);
//        unselected.add(R.mipmap.device_maintain);
//        unselected.add(R.mipmap.device_fix);
        return unselected;
    }

    @Override
    public FocusAfterSaleViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
