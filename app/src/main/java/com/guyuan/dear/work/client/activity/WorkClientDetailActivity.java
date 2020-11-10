package com.guyuan.dear.work.client.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.client.data.WorkClientViewModel;
import com.guyuan.dear.work.client.fragment.WorkClientDetailFragment;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的工作-客户详情
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkClientDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkClientViewModel> {

    public static void start(Context context, ClientCompanyBean data) {
        Intent intent = new Intent(context, WorkClientDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ClientCompanyBean bean = (ClientCompanyBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText("客户详情");
        WorkClientDetailFragment mFragment = WorkClientDetailFragment.newInstance(bean);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                WorkClientDetailFragment.TAG);
    }

    @Override
    public WorkClientViewModel getViewModel() {
        return viewModel;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
