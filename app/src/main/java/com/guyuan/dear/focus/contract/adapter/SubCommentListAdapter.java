package com.guyuan.dear.focus.contract.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemSubContractCommentBinding;
import com.guyuan.dear.focus.contract.bean.ContractComment;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 17:32
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SubCommentListAdapter extends BaseDBRecycleAdapter<ContractComment, ItemSubContractCommentBinding> {

    public SubCommentListAdapter(List<ContractComment> listData) {
        super(listData, R.layout.item_sub_contract_comment);
    }

    @Override
    protected void bindDataToView(Holder holder, ContractComment item, int position) {
        holder.binding.setData(item);
    }
}
