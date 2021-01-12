package com.guyuan.dear.focus.contract.view.contractApplyDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 18:24
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyDetailFragment extends BaseMvvmFragment<FragmentContractApplyDetailBinding, ContractRestartApplyDetailViewModel> {

    private long examineId;

    public static ContractApplyDetailFragment getInstance(Long examineId) {
        ContractApplyDetailFragment fragment = new ContractApplyDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ConstantValue.KEY_EXAMINE_ID, examineId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_apply_detail_vm;
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

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_apply_detail;
    }
}
