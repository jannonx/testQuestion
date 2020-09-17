package com.guyuan.dear.work.purchase.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.purchase.data.WorkPurchaseViewModel;
import com.guyuan.dear.work.purchase.fragment.ComponentPurchaseFragment;
import com.guyuan.dear.work.purchase.fragment.MaterialPurchaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的工作--采购计划
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkPurchaseActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkPurchaseViewModel> {

    private MaterialPurchaseFragment providerFragment;
    private ComponentPurchaseFragment contractProgressFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkPurchaseActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_purchase_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        providerFragment = MaterialPurchaseFragment.newInstance();
        contractProgressFragment = ComponentPurchaseFragment.newInstance();
        fragmentList.add(providerFragment);
        fragmentList.add(contractProgressFragment);
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
    public WorkPurchaseViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
