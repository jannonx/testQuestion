package com.guyuan.dear.focus.sales.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.sales.bean.ContractBaseBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/12 11:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListAdapter extends BaseRecyclerAdapter<ContractBaseBean> {

    public ContractListAdapter(Context context, @NonNull List<ContractBaseBean> listData) {
        super(context, listData, R.layout.item_contract_list);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ContractBaseBean item, int position) {
        holder.setText(R.id.item_contract_list_tv_contact_id,item.getContractId());
        holder.setText(R.id.item_contract_list_tv_buyer,item.getBuyer());
        holder.setText(R.id.item_contract_list_tv_sales_person,item.getSalesPerson());
        holder.setText(R.id.item_contract_list_tv_date,
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(item.getDate()));

    }
}
