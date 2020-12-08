package com.guyuan.dear.analyse.operate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.analyse.operate.fragment.OperateListFragment;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--运营效益--详情
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class OperateListActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, OperateViewModel> {

    public static void start(Context context, OperateOverViewBean viewBean) {
        Intent intent = new Intent(context, OperateListActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, viewBean);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        OperateOverViewBean overViewBean = (OperateOverViewBean) getIntent().getSerializableExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(overViewBean.getOperateType().getDes());
        OperateListFragment mFragment = OperateListFragment.newInstance(overViewBean);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                OperateListFragment.TAG);
    }

    @Override
    public OperateViewModel getViewModel() {
        return viewModel;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }


}
