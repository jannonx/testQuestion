package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :我的关注-评审页
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
        exceptionFragment = FocusAssessListFragment.newInstance(10, "",
                FocusAssessListFragment.FROM_OTHER);
        totalFragment = FocusAssessListFragment.newInstance(FocusAssessListFragment.TOTAL, "",
                FocusAssessListFragment.FROM_OTHER);
        fragmentList.add(overviewFragment);
        fragmentList.add(exceptionFragment);
        fragmentList.add(totalFragment);
        return fragmentList;
    }


    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        setOverviewFragment();
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }

    private void setOverviewFragment() {
        viewModel.assessNotPassListBean.observe(this, new Observer<AssessListBean>() {
            @Override
            public void onChanged(AssessListBean assessListBean) {
                exceptionFragment.setListData(assessListBean.getContent());
            }
        });

        viewModel.assessTotalListBean.observe(this, new Observer<AssessListBean>() {
            @Override
            public void onChanged(AssessListBean assessListBean) {
                totalFragment.setListData(assessListBean.getContent());
            }
        });
    }


}
