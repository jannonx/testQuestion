package com.guyuan.dear.focus.produce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceAdapter extends BaseRecyclerAdapter<FocusProduceBean> {
    public FocusProduceAdapter(Context context, @NonNull List<FocusProduceBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, FocusProduceBean item,
                                  int position) {

        holder.setText(R.id.tv_product_name, item.getName());
        //设置生产状态
        TextView tvStatus = holder.getView(R.id.tv_produce_status);
        TextView tvSubStatus = holder.getView(R.id.tv_sub_status);
        holder.setText(R.id.tv_produce_status, item.getStatusText());
        tvStatus.setBackgroundResource(item.getStatusTextBg());
        int color_blue_ff1b97fc = item.getStatusTextColor();
        tvStatus.setTextColor(context.getResources().getColor(color_blue_ff1b97fc));
        tvSubStatus.setVisibility(ProductStatusType.TYPE_PRODUCE_DELAY == item.getStatusType()
                ? View.VISIBLE : View.GONE);

        holder.setText(R.id.tv_product_code, item.getCode());
        holder.getView(R.id.tv_produce_company).setVisibility(
                TextUtils.isEmpty(item.getPrincipalDept()) ? View.GONE : View.VISIBLE);
        holder.setText(R.id.tv_produce_company, item.getPrincipalDept());

        //生产状态:待生产，只显示上部分内容
        holder.getView(R.id.cl_middle_content).setVisibility(
                ProductStatusType.TYPE_PRODUCE_WAIT == item.getStatusType()
                        ? View.GONE : View.VISIBLE);
        holder.getView(R.id.cl_footer_content).setVisibility(
                TextUtils.isEmpty(item.getDisplayReason()) ? View.GONE : View.VISIBLE);

        holder.setText(R.id.tv_operator, item.getOperatorStr());
        holder.setText(R.id.label_activate_time, item.getTimeTypeStr());
        holder.setText(R.id.tv_activate_time, item.getActualEndTime());


        holder.setText(R.id.tv_activate_time, item.getDisplayTime());
        holder.setText(R.id.label_reason, item.getReasonTypeStr());
        holder.setText(R.id.tv_reason, item.getDisplayReason());
    }
}
