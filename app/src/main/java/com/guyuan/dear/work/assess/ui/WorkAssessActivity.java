package com.guyuan.dear.work.assess.ui;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/4 19:20
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class WorkAssessActivity extends BaseTabActivity<ActivityBaseTabBinding, WorkAssessViewModel>{

    @Override
    protected List<String> getTitles() {
        return null;
    }

    @Override
    protected List<Fragment> getFragments() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<Integer> setTabIconList() {
        return null;
    }
}