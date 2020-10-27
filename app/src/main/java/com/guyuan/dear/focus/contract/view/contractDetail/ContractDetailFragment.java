package com.guyuan.dear.focus.contract.view.contractDetail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractDetailBinding;
import com.guyuan.dear.focus.contract.adapter.ContractDetailPagerAdapter;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.view.contractProgress.ContractPrgDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同详情
 * @since: 2020/10/10 10:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailFragment extends BaseMvvmFragment<FragmentContractDetailBinding, ContractDetailViewModel> {
    private String mContractId;

    public static ContractDetailFragment getInstance(String contractId) {
        ContractDetailFragment fragment = new ContractDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_CONTRACT_ID, contractId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mContractId = bundle.getString(ConstantValue.KEY_CONTRACT_ID);


    }

    @Override
    protected void initViews() {
        ViewPager2 viewPager = getViewDataBinding().fragmentContractDetailViewPager;
        TabLayout tabLayout = getViewDataBinding().fragmentContractDetailTabLayout;
        ContractDetailPagerAdapter adapter = new ContractDetailPagerAdapter(
                getParentFragmentManager(), getLifecycle(), System.currentTimeMillis()
        );
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.getTitle(position));
            }
        }
        );
        mediator.attach();
        getViewModel().getContractBean().observe(
                getViewLifecycleOwner(),
                new Observer<DetailContractBean>() {
                    @Override
                    public void onChanged(DetailContractBean bean) {
                        adapter.update(bean);
                    }
                });
        getViewModel().loadContractDetail();


    }

    @Override
    protected void initListeners() {
        getViewModel().setOnClickCheckProgress(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractPrgDetailActivity.start(getActivity(),"合同订单进度详情",
                        getViewModel().getContractBean().getValue().getContractId());

            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_detail;
    }
}
