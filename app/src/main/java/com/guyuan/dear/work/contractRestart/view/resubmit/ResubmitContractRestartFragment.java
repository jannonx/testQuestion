package com.guyuan.dear.work.contractRestart.view.resubmit;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentResubmitContractRestartBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractRestart.bean.ContractRestartBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/8 17:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ResubmitContractRestartFragment extends BaseMvvmFragment<FragmentResubmitContractRestartBinding,ResubmitContractRestartViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;
    private ContractRestartBean oldApply;

    public static ResubmitContractRestartFragment getInstance(ContractRestartBean oldApply){
        ResubmitContractRestartFragment fragment = new ResubmitContractRestartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_RESTART_APPLY,oldApply);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_resubmit_contract_restart_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        oldApply = bundle.getParcelable(ConstantValue.KEY_RESTART_APPLY);
        getViewModel().showApplyInfo(oldApply);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_resubmit_contract_restart;
    }
}
