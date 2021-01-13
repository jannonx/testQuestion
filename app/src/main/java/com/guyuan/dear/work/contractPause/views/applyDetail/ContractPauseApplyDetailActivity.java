package com.guyuan.dear.work.contractPause.views.applyDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 合同暂停/重启的合同详情界面
 * @author 廖华凯
 */
public class ContractPauseApplyDetailActivity extends BaseActionBarActivity{

    private int examineId;

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, ContractPauseApplyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_apply_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        examineId = intent.getIntExtra(ConstantValue.KEY_ID, 0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_my_pause_apply_detail_frame_layout, ContractPauseApplyDetailFragment.getInstance(examineId))
                .commit();
    }
}