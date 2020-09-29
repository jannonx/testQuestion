package com.guyuan.dear.focus.sales.view.contractSum;

import androidx.databinding.library.baseAdapters.BR;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ComContractsBinding;

/**
 * 我的关注-销售首页-合同订单概况
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 15:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsSumFragment extends BaseMvvmFragment<ComContractsBinding, ComContractsSumViewModel> {

    public static ComContractsSumFragment getInstance() {
        return new ComContractsSumFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_summary_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_company_contracts_summary;
    }
}
