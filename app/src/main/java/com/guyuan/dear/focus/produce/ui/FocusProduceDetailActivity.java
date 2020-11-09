package com.guyuan.dear.focus.produce.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.fragment.FocusClientDetailFragment;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的关注-客户详情
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusProduceDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProduceViewModel> {

    public static void start(Context context, FocusProduceBean data) {
        Intent intent = new Intent(context, FocusProduceDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        FocusProduceBean bean = (FocusProduceBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText("生产详情");
        FocusProduceDetailFragment mFragment = FocusProduceDetailFragment.newInstance(bean);
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
