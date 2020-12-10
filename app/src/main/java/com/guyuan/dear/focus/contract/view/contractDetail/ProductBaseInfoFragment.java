package com.guyuan.dear.focus.contract.view.contractDetail;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentProductBaseInfoBinding;

/**
 * @author: 廖华凯
 * @description: 我的关注-合同-合同概况-合同详情-销售产品
 * @since: 2020/10/10 12:16
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductBaseInfoFragment extends BaseMvvmFragment<FragmentProductBaseInfoBinding, ContractDetailViewModel> {

    public static ProductBaseInfoFragment getInstance(){
        return new ProductBaseInfoFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_product_base_info_vm;
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
        return R.layout.fragment_product_base_info;
    }

}
