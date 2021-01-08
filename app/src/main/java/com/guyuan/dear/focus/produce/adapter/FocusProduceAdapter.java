package com.guyuan.dear.focus.produce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceAdapter extends BaseRecyclerAdapter<FocusProduceBean> {
    public static final int FROM_EXCEPTION = 640;
    public static final int FROM_TOTAL = 650;
    //    private boolean isException = false;
    private int fromSection = 0;

    public FocusProduceAdapter(Context context, @NonNull List<FocusProduceBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    public FocusProduceAdapter(Context context, @NonNull List<FocusProduceBean> listData, int layoutID, int fromSection) {
        super(context, listData, layoutID);
        this.fromSection = fromSection;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, FocusProduceBean item,
                                  int position) {

        holder.setText(R.id.tv_product_name, item.getName());
        //设置生产状态
        TextView tvStatus = holder.getView(R.id.tv_produce_status);

        holder.setText(R.id.tv_produce_status, item.getStatusText());

        tvStatus.setBackgroundResource(item.getStatusTextBg());
        int color_blue_ff1b97fc = item.getStatusTextColor();
        tvStatus.setTextColor(context.getResources().getColor(color_blue_ff1b97fc));

        holder.setText(R.id.tv_product_code, item.getCode());
        holder.getView(R.id.tv_produce_company).setVisibility(
                TextUtils.isEmpty(item.getPrincipalDept()) ? View.GONE : View.VISIBLE);
        holder.setText(R.id.tv_produce_company, item.getPrincipalDept());

        //生产状态:待生产，只显示上部分内容
        holder.getView(R.id.cl_middle_content).setVisibility(
                ProductStatusType.TYPE_PRODUCE_WAIT == item.getStatusType()
                        || ProductStatusType.TYPE_CONTRACT_PAUSE == item.getStatusType()
                        ? View.GONE : View.VISIBLE);
        holder.getView(R.id.cl_footer_content).setVisibility(
                TextUtils.isEmpty(item.getDisplayReason()) ? View.GONE : View.VISIBLE);

        holder.setText(R.id.tv_operator, fromSection == FROM_EXCEPTION || fromSection == FROM_TOTAL
                ? "操作员：" + item.getUpdateName() : item.getOperatorStr());
        holder.setText(R.id.label_activate_time, item.getTimeTypeStr());
        holder.setText(R.id.tv_activate_time, item.getActualEndTime());


        holder.setText(R.id.tv_activate_time, item.getDisplayTime());
//        TextView labelReason = holder.getView(R.id.label_reason);
        holder.setText(R.id.label_reason, fromSection == FROM_EXCEPTION ? "暂停原因：" : item.getReasonTypeStr());
        holder.setText(R.id.tv_reason, item.getDisplayReason());

    }
}
