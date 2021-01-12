package com.guyuan.dear.work.contractRestart.view.resubmit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractRestart.bean.ContractRestartBean;

/**
 * 重新提交合同重启申请
 *
 * @author 廖华凯
 */
public class ResubmitContractRestartActivity extends BaseActionBarActivity {

    public static void startForResult(Fragment fragment, ContractRestartBean bean, int rqCode) {
        Intent intent = new Intent(fragment.getContext(),ResubmitContractRestartActivity.class);
        intent.putExtra(ConstantValue.CONTRACT_RESTART, bean);
        fragment.startActivityForResult(intent, rqCode);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_resubmit_contract_restart;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("重新提交");
        Intent intent = getIntent();
        ContractRestartBean bean = intent.getParcelableExtra(ConstantValue.CONTRACT_RESTART);
        ResubmitContractRestartFragment fragment = ResubmitContractRestartFragment.getInstance(bean);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_resubmit_restart_contract_apply_fragment_container, fragment)
                .commit();
    }
}