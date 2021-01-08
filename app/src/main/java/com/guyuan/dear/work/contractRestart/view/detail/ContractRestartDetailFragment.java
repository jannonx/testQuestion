package com.guyuan.dear.work.contractRestart.view.detail;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractRestartApplyDetailBinding;
import com.guyuan.dear.focus.contract.view.contractApplyDetail.ContractApplyDetailViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/7 19:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractRestartDetailFragment extends BaseMvvmFragment<FragmentContractRestartApplyDetailBinding, ContractApplyDetailViewModel> {

    private long examineId;

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
                //todo
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_restart_apply_detail;
    }
}
