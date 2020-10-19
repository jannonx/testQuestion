package com.guyuan.dear.focus.sales.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.customizeview.flowlayout.TagFlowLayout;
import com.guyuan.dear.focus.sales.bean.ContractBaseBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/12 17:39
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgHomeListAdapter extends BaseRecyclerAdapter<ContractBaseBean> {
    public ContractPrgHomeListAdapter(Context context, @NonNull List<ContractBaseBean> listData) {
        super(context, listData, R.layout.item_contract_prg_home);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ContractBaseBean item, int position) {
        holder.setText(R.id.item_contract_prg_home_tv_contract_id,item.getContractId());
        holder.setText(R.id.item_contract_prg_home_tv_buyer,item.getBuyer());
        holder.setText(R.id.item_contract_prg_home_tv_date,
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(item.getDate()));
        TagFlowLayout view = holder.getView(R.id.item_contract_prg_home_tag_flow_layout);
        view.setAdapter(new TagAdapter(item.getTags()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View rootView = LayoutInflater.from(context).inflate(R.layout.item_tag_flow_layout_contract_prg_home_tag, parent, false);
                AppCompatTextView tv = rootView.findViewById(R.id.item_tag_flow_layout_contract_prg_home_tag_tv_name);
                tv.setText(item.getTags().get(position));
                return rootView;
            }
        });

    }
}
