package com.guyuan.dear.focus.contract.view.contractProgress;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractPrgDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情-合同进度详情
 * @since: 2020/10/12 18:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailFragment extends BaseMvvmFragment<FragmentContractPrgDetailBinding, ContractPrgDetailViewModel> {

    private int mContractId;

    public static ContractPrgDetailFragment getInstance(int contractId) {
        Bundle arg = new Bundle();
        arg.putInt(ConstantValue.KEY_CONTRACT_ID, contractId);
        ContractPrgDetailFragment fragment = new ContractPrgDetailFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_prg_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mContractId = bundle.getInt(ConstantValue.KEY_CONTRACT_ID);
        getViewModel().loadStatusFlowFromNet(mContractId);
    }

    @Override
    protected void initViews() {
        initLogList();
    }

    private void initLogList() {

    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_prg_detail;
    }
}
