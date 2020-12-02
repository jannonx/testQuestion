package com.guyuan.dear.analyse.operate.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.bean.OperateType;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class OperateListAdapter extends BaseRecyclerAdapter<OperateAnalyseBean> {


    public OperateListAdapter(Context context, @NonNull List<OperateAnalyseBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, OperateAnalyseBean item, int position) {
        LogUtils.showLog("getTotalCost=" + item.getTotalCost() + "..getTradeReceivables=" + item.getTradeReceivables());
        holder.setText(R.id.tv_money, OperateType.TYPE_ACTUAL == item.getOperateType() ? item.getTradeReceivables() : item.getTotalCost());
        holder.setText(R.id.tv_customer_name, item.getCusName());
        holder.setText(R.id.tv_project_name, item.getProjectName());

    }
}