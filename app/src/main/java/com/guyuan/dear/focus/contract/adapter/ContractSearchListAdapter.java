package com.guyuan.dear.focus.contract.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemContractSearchListBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 12:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchListAdapter extends BaseDBRecycleAdapter<BaseContractBean, ItemContractSearchListBinding> {
    public ContractSearchListAdapter(List<BaseContractBean> listData) {
        super(listData, R.layout.item_contract_search_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseContractBean item, int position) {
        holder.binding.setData(item);
    }
}
