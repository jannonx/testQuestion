package com.guyuan.dear.work.produce.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.fragment.FocusClientDetailFragment;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceClassifyFragment;
import com.guyuan.dear.focus.produce.ui.FocusProduceActivity;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.produce.data.WorkProduceViewModel;
import com.guyuan.dear.work.produce.fragment.WorkProduceFragment;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * @description: 我的关注--生产计划--分类查询
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkProduceActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkProduceViewModel> {

    public static void start(Context context, ClientCompanyBean data) {
        Intent intent = new Intent(context, WorkProduceActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WorkProduceActivity.class);
        context.startActivity(intent);
    }
    public static void start(Context context, String title) {
        Intent starter = new Intent(context, WorkProduceActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }
    @Override
    protected void initFragment(Bundle savedInstanceState) {
//        ClientCompanyBean bean = (ClientCompanyBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
//        FocusProduceDetailFragment mFragment = FocusProduceDetailFragment.newInstance(bean);
        WorkProduceFragment mFragment = WorkProduceFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                WorkProduceFragment.TAG);
    }

    @Override
    public WorkProduceViewModel getViewModel() {
        return viewModel;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
