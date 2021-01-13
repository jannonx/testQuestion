package com.guyuan.dear.work.contractRestart.view.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;
import com.umeng.message.common.Const;

/**
 *
 * @author 廖华凯
 */
public class MyRestartApplyDetailActivity extends BaseActionBarActivity {

    private long examineId;

    public static void start(Context context,long examineId) {
        Intent starter = new Intent(context, MyRestartApplyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_EXAMINE_ID,examineId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_restart_apply_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("合同重启申请详情");
        Intent intent = getIntent();
        examineId = intent.getLongExtra(ConstantValue.KEY_EXAMINE_ID,0);
        ContractRestartDetailFragment fragment = ContractRestartDetailFragment.getInstance(examineId);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_my_restart_apply_detail_fragment_container,fragment)
                .commit();

    }
}