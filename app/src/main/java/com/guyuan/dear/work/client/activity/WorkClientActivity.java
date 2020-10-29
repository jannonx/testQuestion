package com.guyuan.dear.work.client.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.client.data.WorkClientViewModel;
import com.guyuan.dear.work.client.fragment.AllClientFragment;
import com.guyuan.dear.work.client.fragment.CustomerFollowFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--售后
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkClientActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkClientViewModel> {


    private AllClientFragment allClientFragment;

    private CustomerFollowFragment customerFollowFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkClientActivity.class);
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
        allClientFragment = AllClientFragment.newInstance();
        customerFollowFragment = CustomerFollowFragment.newInstance();
        fragmentList.add(allClientFragment);
        fragmentList.add(customerFollowFragment);
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
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }


    @Override
    public WorkClientViewModel getViewModel() {
        return viewModel;
    }


}
