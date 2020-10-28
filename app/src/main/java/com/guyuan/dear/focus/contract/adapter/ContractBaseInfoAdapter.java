package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.contract.bean.ProductComponent;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractBaseInfoAdapter extends BaseRecyclerAdapter<ProductComponent> {
    public ContractBaseInfoAdapter(Context context, @NonNull List<ProductComponent> listData) {
        super(context, listData, R.layout.item_contract_base_info);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProductComponent item, int position) {
        holder.setText(R.id.item_contract_base_info_tv_name,item.getComponentName());
        holder.setText(R.id.item_contract_base_info_tv_model,item.getModelName());
        holder.setText(R.id.item_contract_base_info_tv_blue_print_id,item.getBluePrintId());
        String quantity = item.getCount()+item.getUnit();
        holder.setText(R.id.item_contract_base_info_tv_quantity,quantity);
    }
}