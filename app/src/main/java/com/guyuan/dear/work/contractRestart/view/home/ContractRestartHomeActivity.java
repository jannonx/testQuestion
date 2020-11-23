package com.guyuan.dear.work.contractRestart.view.home;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.views.applyWindow.ContractApplyFragment;
import com.guyuan.dear.work.contractPause.views.myAppliedList.MyApplyListFragment;

import java.util.ArrayList;
import java.util.List;

public class ContractRestartHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, ContractRestartHomeViewModel> {

    public static void start(Context context,String title) {
        Intent starter = new Intent(context, ContractRestartHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        context.startActivity(starter);
    }


    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>(){
            {
                add("合同重启");
                add("我的申请");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>(){
            {
                add(ContractApplyFragment.getInstance(ContractApplyBean.APPLY_TYPE_RESUME));
                add(MyApplyListFragment.getInstance(MyApplyListFragment.TYPE_MY_RESTART_APPLY_LIST));
            }
        };
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
}