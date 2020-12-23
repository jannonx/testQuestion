package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.contract.bean.ComponentStateBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 10:41
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComponentStatesAdapter extends BaseRecyclerAdapter<ComponentStateBean> {


    public ComponentStatesAdapter(Context context, @NonNull List<ComponentStateBean> listData) {
        super(context, listData, R.layout.item_contract_prg_detail_component);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ComponentStateBean item, int position) {
        holder.setText(R.id.item_contract_prg_detail_component_tv_component_state,item.getProductionState());
        holder.setText(R.id.item_contract_prg_detail_component_tv_component_model_name,item.getModelName());
        holder.setText(R.id.item_contract_prg_detail_component_tv_component_name,item.getComponentName());
    }
}
