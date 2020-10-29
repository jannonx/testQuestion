package com.guyuan.dear.approve;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.data.ApproveViewModel;

import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 掌上办公--审批首页
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ApproveActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, ApproveViewModel> {

    public static void start(Context context, ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList, String title) {
        Intent starter = new Intent(context, ApproveActivity.class);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_APPROVE_MENU, menuList);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }


    @Override
    public void initFragment(Bundle savedInstanceState) {
        ArrayList<LoginBean.AppMenusBean.ChildrenBean> approveMenuList =
                getIntent().getParcelableArrayListExtra(ConstantValue.KEY_APPROVE_MENU);
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
        ApproveFragment mFragment = ApproveFragment.newInstance(approveMenuList);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                ApproveFragment.TAG);

    }

    @Override
    public ApproveViewModel getViewModel() {
        return null;
    }




    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
