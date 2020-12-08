package com.guyuan.dear.work.assess.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/11 10:58
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class WorkAssessDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, WorkAssessViewModel> {

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, WorkAssessDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        setTitleCenter(title);
        WorkAssessDetailFragment detailFragment = WorkAssessDetailFragment.newInstance(id);
        ActivityUtils.addFragmentToActivity(fragmentManager, detailFragment,
                R.id.fragment_container, WorkAssessDetailFragment.TAG);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}