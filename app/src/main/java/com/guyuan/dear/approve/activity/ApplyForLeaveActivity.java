package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.ApproveFragment;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;


/**
 * @description: 掌上办公--审核--请假
 * @author:Jannonx
 * @date: 2020/6/16 12:20
 */
public class ApplyForLeaveActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, ApproveViewModel> {

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, ApplyForLeaveActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
//        ApproveFragment mFragment = ApproveFragment.newInstance(approveMenuList);
//        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
//                ApproveFragment.TAG);
    }

    @Override
    public ApproveViewModel getViewModel() {
        return null;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

    @Override
    protected int getLayoutID() {
        return 0;
    }
}
