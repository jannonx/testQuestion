package com.guyuan.dear.analyse.operate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.analyse.operate.fragment.OperateDetailFragment;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 我的关注--运营效益--概览
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class OperateDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, OperateViewModel> {

    public static void start(Context context, OperateAnalyseBean bean) {
        Intent intent = new Intent(context, OperateDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, bean);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        OperateAnalyseBean bean = (OperateAnalyseBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText("项目成本详情");
        OperateDetailFragment mFragment = OperateDetailFragment.newInstance(bean);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                OperateDetailFragment.TAG);
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
