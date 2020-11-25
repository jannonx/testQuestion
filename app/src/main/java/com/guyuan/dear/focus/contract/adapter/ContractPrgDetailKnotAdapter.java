package com.guyuan.dear.focus.contract.adapter;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemContractPrgDetailRoundKnotBinding;
import com.guyuan.dear.focus.contract.bean.ContractPrgKnot;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/13 17:11
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailKnotAdapter extends BaseDBRecycleAdapter<ContractPrgKnot, ItemContractPrgDetailRoundKnotBinding> {
    public ContractPrgDetailKnotAdapter(List<ContractPrgKnot> listData) {
        super(listData, R.layout.item_contract_prg_detail_round_knot);
    }

    @Override
    protected void bindDataToView(Holder holder, ContractPrgKnot item, int position) {
        holder.binding.setData(item);
    }

}
