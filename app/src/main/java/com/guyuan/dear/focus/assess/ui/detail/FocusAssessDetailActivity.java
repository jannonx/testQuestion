package com.guyuan.dear.focus.assess.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.databinding.ActivityWithoutToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityFocusDeviceDetailBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :我的关注-评审详情页
 * @since: 2020/11/2 10:38
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusAssessDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding,
        FocusAssessViewModel> {

    private FocusAssessDetailFragment detailFragment;

    public static void start(Context context, String title, int id, String contractNumber) {
        Intent starter = new Intent(context, FocusAssessDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(FocusAssessDetailFragment.CONTRACT_NUMBER, contractNumber);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        String contractNumber = getIntent().getStringExtra(FocusAssessDetailFragment.CONTRACT_NUMBER);
        setTitleCenter(title);
        detailFragment = FocusAssessDetailFragment.newInstance(id, contractNumber);
        ActivityUtils.addFragmentToActivity(fragmentManager, detailFragment, R.id.fragment_container, FocusAssessDetailFragment.TAG);
        setObserver();
    }

    private void setObserver() {
        viewModel.assessDetailList.observe(this, new Observer<List<AssessDetailBean>>() {
            @Override
            public void onChanged(List<AssessDetailBean> assessDetailBeans) {
                detailFragment.initTab(assessDetailBeans);
            }
        });
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}