package com.guyuan.dear.focus.contract.view.contractRestartDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentRestartedContractDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/29 20:40
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractDetailFragment extends BaseMvvmFragment<FragmentRestartedContractDetailBinding,RestartedContractDetailViewModel> {
    private String contractId;


    public static RestartedContractDetailFragment getInstance(String contractId){
        RestartedContractDetailFragment fragment = new RestartedContractDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_CONTRACT_ID,contractId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_restarted_contract_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        contractId = bundle.getString(ConstantValue.KEY_CONTRACT_ID);
        getViewModel().getRestartedContractDetailFromNet(contractId);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_restarted_contract_detail;
    }
}
