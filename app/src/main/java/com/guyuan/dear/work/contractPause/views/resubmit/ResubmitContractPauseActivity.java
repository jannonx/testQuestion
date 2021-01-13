package com.guyuan.dear.work.contractPause.views.resubmit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractPauseBean;
import com.umeng.message.common.Const;

/**
 * 暂停被驳回后，重新提交合同暂停申请
 * @author 廖华凯
 */
public class ResubmitContractPauseActivity extends BaseActionBarActivity {


    public static void startForResult(Fragment fragment,ContractPauseBean bean,int rqCode){
        Intent intent = new Intent(fragment.getContext(),ResubmitContractPauseActivity.class);
        intent.putExtra(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN,bean);
        fragment.startActivityForResult(intent,rqCode);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_resubmit_contract_pause;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("修改暂停申请");
        Intent intent = getIntent();
        ContractPauseBean bean = intent.getParcelableExtra(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN);
        ResubmitContractPauseFragment fragment = ResubmitContractPauseFragment.getInstance(bean);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_resubmit_pause_contract_apply_fragment_container,fragment)
                .commit();
    }
}