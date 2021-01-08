package com.guyuan.dear.work.contractRestart.view.resubmit;

import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;

/**
 * 重新提交合同重启申请
 * @author 廖华凯
 */
public class ResubmitContractRestartActivity extends BaseActionBarActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_resubmit_contract_restart;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("重新提交");


    }
}