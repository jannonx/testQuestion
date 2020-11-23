package com.guyuan.dear.focus.contract.adapter;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemContractCommentBinding;
import com.guyuan.dear.focus.contract.bean.ContractComment;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 15:56
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentListAdapter extends BaseDBRecycleAdapter<ContractComment, ItemContractCommentBinding> {
    public ContractCommentListAdapter(List<ContractComment> listData) {
        super(listData, R.layout.item_contract_comment);
    }

    @Override
    protected void bindDataToView(Holder holder, ContractComment item, int position) {
        holder.binding.setData(item);
    }

}
