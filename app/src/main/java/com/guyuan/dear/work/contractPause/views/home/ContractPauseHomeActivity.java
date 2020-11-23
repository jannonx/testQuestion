package com.guyuan.dear.work.contractPause.views.home;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.views.applyWindow.ContractApplyFragment;
import com.guyuan.dear.work.contractPause.views.myAppliedList.MyApplyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 */
public class ContractPauseHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, ContractPauseHomeViewModel> {


    public static void start(Context context,String title) {
        Intent starter = new Intent(context, ContractPauseHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>(){
            {
                add("合同暂停");
                add("我的申请");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>(){
            {
                add(ContractApplyFragment.getInstance(ContractApplyBean.APPLY_TYPE_PAUSE));
                add(MyApplyListFragment.getInstance(MyApplyListFragment.TYPE_MY_PAUSE_APPLY_LIST));
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