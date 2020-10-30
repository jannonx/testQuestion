package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.approve.fragment.ApplyNotApproveFragment;
import com.guyuan.dear.approve.fragment.ApplyYetApprovedFragment;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 掌上办公--审批--我的审批(待我审批/我已审批)
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ApprovalsEntranceActivity extends BaseTabActivity<ActivityBaseTabBinding, ApproveViewModel> {


    private ApplyNotApproveFragment notApproveFragment;
    private ApplyYetApprovedFragment yetApprovedFragment;

    public static void start(Context context) {
        Intent starter = new Intent(context, ApprovalsEntranceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = {"待我审批", "我已审批"};
        return new ArrayList<>(Arrays.asList(titles));
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        notApproveFragment = ApplyNotApproveFragment.getInstance();
        yetApprovedFragment = ApplyYetApprovedFragment.getInstance();
        fragmentList.add(notApproveFragment);
        fragmentList.add(yetApprovedFragment);
        return fragmentList;
    }



    @Override
    protected void init() {
        setTitleCenter("我的审批");
        tlBase.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    protected List<Integer> setTabIconList() {
        return null;
    }




    @Override
    public ApproveViewModel getViewModel() {
        return viewModel;
    }




}
