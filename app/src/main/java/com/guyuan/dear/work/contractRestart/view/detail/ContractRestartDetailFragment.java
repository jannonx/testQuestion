package com.guyuan.dear.work.contractRestart.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractRestartApplyDetailBinding;
import com.guyuan.dear.focus.contract.view.contractApplyDetail.ContractRestartApplyDetailViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractRestart.view.resubmit.ResubmitContractRestartActivity;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/7 19:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractRestartDetailFragment extends BaseMvvmFragment<FragmentContractRestartApplyDetailBinding, ContractRestartApplyDetailViewModel> {

    private long examineId;
    private static final int REQ_CODE_RESUBMIT_RESTART = 541;

    public static ContractRestartDetailFragment getInstance(Long examineId) {
        ContractRestartDetailFragment fragment = new ContractRestartDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ConstantValue.KEY_EXAMINE_ID, examineId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_restart_apply_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        examineId = arguments.getLong(ConstantValue.KEY_EXAMINE_ID);
        addDisposable(getViewModel().getContractDetailFromNet(examineId));
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().onClickReSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResubmitContractRestartActivity.startForResult(
                        ContractRestartDetailFragment.this,
                        getViewModel().genApplyBean(),
                        REQ_CODE_RESUBMIT_RESTART);
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_restart_apply_detail;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_RESUBMIT_RESTART || resultCode == RESULT_OK){
            addDisposable(getViewModel().getContractDetailFromNet(examineId));
        }
    }
}
