package com.guyuan.dear.focus.contract.adapter;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemContractExceptionOrTotalBinding;
import com.guyuan.dear.focus.contract.bean.ContractBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/1 13:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ContractExceptionOrTotalAdapter extends BaseDBRecycleAdapter<ContractBean.ContentBean, ItemContractExceptionOrTotalBinding> {
    public ContractExceptionOrTotalAdapter(List<ContractBean.ContentBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, ContractBean.ContentBean item, int position) {
        holder.binding.setVariable(BR.focusContractListContent, item);
    }
}