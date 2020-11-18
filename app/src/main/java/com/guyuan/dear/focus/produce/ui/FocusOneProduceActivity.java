package com.guyuan.dear.focus.produce.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusOneProduceFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 一个产品(或者所有产品)下的所有生产列表
 * @author: 许建宁
 * @since: 2020/11/16 16:45
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusOneProduceActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProduceViewModel> {

    public static void start(Context context, String data) {
        Intent intent = new Intent(context, FocusOneProduceActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String bean = getIntent().getStringExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText("生产列表");
        FocusOneProduceFragment mFragment = FocusOneProduceFragment.newInstance(bean);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusOneProduceFragment.TAG);
    }

    @Override
    public FocusProduceViewModel getViewModel() {
        return viewModel;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}