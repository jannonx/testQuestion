package com.guyuan.dear.work.contractPause.views.applyDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityMyPauseApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

public class MyPauseApplyDetailActivity extends BaseToolbarActivity<ActivityMyPauseApplyDetailBinding, MyPauseApplyDetailViewModel> {

    private MyPauseApplyBean bean;

    public static void start(Context context, String title, MyPauseApplyBean bean) {
        Intent starter = new Intent(context, MyPauseApplyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN, bean);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_pause_apply_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        bean = intent.getParcelableExtra(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_my_pause_apply_detail_frame_layout, MyPauseApplyDetailFragment.getInstance(bean))
                .commit();
    }
}