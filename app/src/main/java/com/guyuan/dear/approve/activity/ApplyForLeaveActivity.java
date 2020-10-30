package com.guyuan.dear.approve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.approve.fragment.ApplyForLeaveFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;



/**
 * @description: 掌上办公--审核--请假
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
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
        ApplyForLeaveFragment mFragment = ApplyForLeaveFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                ApplyForLeaveFragment.TAG);
    }

    @Override
    public ApproveViewModel getViewModel() {
        return viewModel;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
