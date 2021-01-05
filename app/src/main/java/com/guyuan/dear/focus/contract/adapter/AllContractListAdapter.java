package com.guyuan.dear.focus.contract.adapter;

import android.view.View;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemAllContractListBinding;
import com.guyuan.dear.databinding.ItemContractListBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
import com.guyuan.dear.focus.contract.view.contractProgress.ContractPrgDetailActivity;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/9 19:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllContractListAdapter extends BaseDBRecycleAdapter<BaseContractBean, ItemAllContractListBinding> {
    public AllContractListAdapter(List<BaseContractBean> listData) {
        super(listData, R.layout.item_all_contract_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseContractBean item, int position) {
        holder.binding.setData(item);
        holder.binding.setOnClickShowDetail(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractDetailActivity.start(getContext(), "合同详情", (int) item.getContractId());
            }
        });
        holder.binding.setOnClickShowExecutionStatus(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractPrgDetailActivity.start(getContext(), "合同执行状态", (int) item.getContractId());
            }
        });
    }
}
