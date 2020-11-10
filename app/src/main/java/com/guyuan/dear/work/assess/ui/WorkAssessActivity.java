package com.guyuan.dear.work.assess.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/4 19:20
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class WorkAssessActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkAssessViewModel> {

    private WorkAssessListFragment listFragment;
    private WorkAssessCreateFragment createFragment;
    private WorkAssessListFragment myCreateListFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkAssessActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_assess_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        listFragment = WorkAssessListFragment.newInstance(WorkAssessListFragment.TOTAL);
        createFragment = WorkAssessCreateFragment.newInstance();
        myCreateListFragment = WorkAssessListFragment.newInstance(WorkAssessListFragment.CREATE);
        fragmentList.add(listFragment);
        fragmentList.add(createFragment);
        fragmentList.add(myCreateListFragment);
        return fragmentList;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    private void setObserver() {

    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }


}