package com.guyuan.dear.focus.sales.view.contractProgress;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractPrgDetailBinding;
import com.guyuan.dear.focus.sales.adapter.ContractLogsPagerAdapter;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情-合同进度详情
 * @since: 2020/10/12 18:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailFragment extends BaseMvvmFragment<FragmentContractPrgDetailBinding, ContractPrgDetailViewModel> {

    private String mContractId;

    public static ContractPrgDetailFragment getInstance(String contractId) {
        Bundle arg = new Bundle();
        arg.putString(ConstantValue.KEY_CONTRACT_ID, contractId);
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
        mContractId = bundle.getString(ConstantValue.KEY_CONTRACT_ID);
        getViewModel().loadDateFromNet(mContractId);
    }

    @Override
    protected void initViews() {
        initLogList();
    }

    private void initLogList() {
        TabLayout tabLayout = getViewDataBinding().fragmentContractPrgDetailTabLayoutContractLogs;
        ViewPager2 vp = getViewDataBinding().fragmentContractPrgDetailVpContractLogs;
        ContractLogsPagerAdapter adapter = new ContractLogsPagerAdapter(getParentFragmentManager(),getLifecycle());
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, vp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.getTitles()[position]);
            }
        });
        mediator.attach();
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_prg_detail;
    }
}
