package com.guyuan.dear.focus.contract.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemLastYearDeliverContractListBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailActivity;
import com.guyuan.dear.focus.contract.view.contractProgress.ContractPrgDetailActivity;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/4 11:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LastYearDeliverListAdapter extends BaseDBRecycleAdapter<BaseContractBean, ItemLastYearDeliverContractListBinding> {
    public LastYearDeliverListAdapter(List<BaseContractBean> listData) {
        super(listData, R.layout.item_last_year_deliver_contract_list);
    }

    @Override
    protected void bindDataToView(Holder holder, BaseContractBean item, int position) {
        ItemLastYearDeliverContractListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(item);
        binding.setOnClickShowDetail(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractDetailActivity.start(getContext(), "合同详情", (int) item.getContractId());
            }
        });
        binding.setOnClickShowExecutionStatus(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContractPrgDetailActivity.start(getContext(), "合同执行状态", (int) item.getContractId());
            }
        });
    }
}
