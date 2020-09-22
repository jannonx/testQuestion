package com.guyuan.dear.work.customerfollow.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.customerfollow.data.WorkCustomerViewModel;
import com.guyuan.dear.work.customerfollow.fragment.AllCustomerFragment;
import com.guyuan.dear.work.customerfollow.fragment.MyCustomerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的工作--客户跟进
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkCustomerActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkCustomerViewModel> {


    private AllCustomerFragment materialListFragment;
    private MyCustomerFragment qualityExceptFragment;


    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkCustomerActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_customer_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();

        materialListFragment = AllCustomerFragment.newInstance();
        qualityExceptFragment = MyCustomerFragment.newInstance();

        fragmentList.add(materialListFragment);
        fragmentList.add(qualityExceptFragment);
        return fragmentList;
    }



    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabIconList() {
        return null;
    }



    @Override
    public WorkCustomerViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
