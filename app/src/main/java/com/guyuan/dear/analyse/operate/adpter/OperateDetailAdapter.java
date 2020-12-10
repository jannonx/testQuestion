package com.guyuan.dear.analyse.operate.adpter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:11
 * @company : 固远（深圳）信息技术有限公司
 **/

public class OperateDetailAdapter extends BaseRecyclerAdapter<OperateAnalyseBean> {

    CalenderUtils calenderUtils = CalenderUtils.getInstance();

    public OperateDetailAdapter(Context context, @NonNull List<OperateAnalyseBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, OperateAnalyseBean item, int position) {
        holder.setText(R.id.tv_total, item.getTotalCost() + "元");
        long time = calenderUtils.parseSmartFactoryDateStringFormat(item.getMonthTime()).getTime();
        holder.setText(R.id.tv_month,  calenderUtils.toChineseYearMonth(time));
        holder.setText(R.id.tv_manpower, item.getLaborCost());
        holder.setText(R.id.tv_fabricating_cast, item.getManufacturingCosts());
        holder.setText(R.id.tv_install_cast, item.getInstallationCost());
        holder.setText(R.id.tv_material_cast, item.getMaterialCosts());

    }
}