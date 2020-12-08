package com.guyuan.dear.focus.produce.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.client.fragment.FocusClientDetailFragment;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceClassifyFragment;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的关注--生产计划--分类查询
 * @author: 许建宁
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusProduceClassifyActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProduceViewModel> {

    public static void start(Context context, ProductStatusType data) {
        Intent intent = new Intent(context, FocusProduceClassifyActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);

        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ProductStatusType bean = (ProductStatusType) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText(bean.getDes());
        FocusProduceClassifyFragment mFragment = FocusProduceClassifyFragment.newInstance(bean);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusClientDetailFragment.TAG);
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
