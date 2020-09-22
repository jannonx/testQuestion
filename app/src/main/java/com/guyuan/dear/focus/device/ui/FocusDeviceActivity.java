package com.guyuan.dear.focus.device.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.device.data.FocusDeviceViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:04
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusDeviceActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusDeviceViewModel> {

    private FocusDeviceOverviewFragment overviewFragment;
    private FocusDeviceExceptionFragment exceptionFragment;
    private FocusDeviceProfileFragment profileFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusDeviceActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }


    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.focus_device_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        overviewFragment = FocusDeviceOverviewFragment.newInstance();
        exceptionFragment = FocusDeviceExceptionFragment.newInstance();
        profileFragment = FocusDeviceProfileFragment.newInstance();
        fragmentList.add(overviewFragment);
        fragmentList.add(exceptionFragment);
        fragmentList.add(profileFragment);
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
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }


    @Override
    public void viewModuleCallBack(Object o) {

    }

}
