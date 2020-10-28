package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:12
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusAssessActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusAssessViewModel> {

    private FocusAssessOverviewFragment overviewFragment;
    private FocusAssessListFragment exceptionFragment;
    private FocusAssessListFragment totalFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FocusAssessActivity.class);
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
        overviewFragment = FocusAssessOverviewFragment.newInstance();
        exceptionFragment = FocusAssessListFragment.newInstance(FocusAssessListFragment.EXCEPTION, "");
        totalFragment = FocusAssessListFragment.newInstance(FocusAssessListFragment.TOTAL, "");
        fragmentList.add(overviewFragment);
        fragmentList.add(exceptionFragment);
        fragmentList.add(totalFragment);
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
