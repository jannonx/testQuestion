package com.guyuan.dear.work.contractRestart.view.home;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.views.myAppliedList.MyApplyListFragment;
import com.guyuan.dear.work.contractRestart.view.apply.ContractRestartFragment;

import java.util.ArrayList;
import java.util.List;

public class ContractRestartHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, ContractRestartHomeViewModel> {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ContractRestartHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }


    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>() {
            {
                add("合同重启");
                add("我的申请");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>() {
            {
//                add(ContractApplyFragment.getInstance(ContractApplyBean.APPLY_TYPE_RESUME));
                add(ContractRestartFragment.getInstance());
                add(MyApplyListFragment.getInstance(MyApplyListFragment.TYPE_MY_RESTART_APPLY_LIST));
            }
        };
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        getViewModel().refreshMyRestartApplyList.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    vpBase.setCurrentItem(1, true);
                }
            }
        });

    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.selector_tab_contract_restart_make_apply);
        tabDrawableList.add(R.drawable.selector_tab_pause_apply_my_apply_list);
        return tabDrawableList;
    }
}