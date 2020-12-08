package com.guyuan.dear.work.customerfollow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.customerfollow.data.WorkCustomerViewModel;
import com.guyuan.dear.work.customerfollow.fragment.CustomerDetailFragment;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的工作--客户跟进--详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class CustomerDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkCustomerViewModel> {

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, CustomerDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
        CustomerDetailFragment mFragment = CustomerDetailFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                CustomerDetailFragment.TAG);
    }

    @Override
    public WorkCustomerViewModel getViewModel() {
        return viewModel;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
