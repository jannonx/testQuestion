package com.guyuan.dear.focus.contract.view.contractExptDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractExcptDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 18:24
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptDetailFragment extends BaseMvvmFragment<FragmentContractExcptDetailBinding,ContractExcptDetailViewModel> {

    private String contractId;

    public static ContractExcptDetailFragment getInstance(String contractId){
        ContractExcptDetailFragment fragment = new ContractExcptDetailFragment();
        Bundle args =new Bundle();
        args.putString(ConstantValue.KEY_CONTRACT_ID,contractId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_excpt_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        contractId = arguments.getString(ConstantValue.KEY_CONTRACT_ID);
        getViewModel().getContractDetailFromNet(contractId);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_excpt_detail;
    }
}
