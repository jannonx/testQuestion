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
    private FocusPlanAssessFragment planAssessFragment;
    private FocusProjectAssessFragment projectAssessFragment;
    private FocusSellAssessFragment sellAssessFragment;


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
        sellAssessFragment = FocusSellAssessFragment.newInstance();
        planAssessFragment = FocusPlanAssessFragment.newInstance();
        projectAssessFragment = FocusProjectAssessFragment.newInstance();
        fragmentList.add(overviewFragment);
        fragmentList.add(sellAssessFragment);
        fragmentList.add(planAssessFragment);
        fragmentList.add(projectAssessFragment);
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
    public FocusAssessViewModel getViewModel() {
        return null;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
