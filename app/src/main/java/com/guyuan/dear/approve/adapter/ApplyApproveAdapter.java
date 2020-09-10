package com.guyuan.dear.approve.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApproveUserBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;



/**
 * @description: 掌上办公--审核--我的审批
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyApproveAdapter extends BaseRecyclerAdapter<ApplyBean> {

    public ApplyApproveAdapter(Context context, @NonNull List<ApplyBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ApplyBean item, int position) {

        ApproveUserBean bean = item.getUser();
        LogUtils.showLog("arType=" + item.getArType());
        String name = bean.getNickName();

        holder.setText(R.id.tv_title, name + "提交的" + item.getApplyType());
        TextView tvLeave = holder.getView(R.id.tv_type);
        //请假显示具体类型
        if (item.getArType() == ApplyConstant.INT_LEAVE) {
            tvLeave.setVisibility(View.VISIBLE);
            tvLeave.setText(item.getVacationType());
        } else {
            tvLeave.setVisibility(View.GONE);
        }

        holder.setText(R.id.tv_start_date, item.getSleaveTime());
        holder.setText(R.id.tv_end_date, item.getEleaveTime());
        switch (item.getStatus()) {
            case ApplyConstant.INT_STATUS_ING:
                holder.setVisible(R.id.iv_approve_status, true);
                holder.setVisible(R.id.iv_approve_mask, true);
                holder.setText(R.id.iv_approve_mask, item.getStatusDescription());
                holder.setTextColor(R.id.iv_approve_mask, R.color.color_yellow_f7b500);
                holder.setImageResource(R.id.iv_approve_status,
                        R.drawable.ic_svg_pending_for_approval);
                break;
            case ApplyConstant.INT_STATUS_PASS:
                holder.setVisible(R.id.iv_approve_status, true);
                holder.setVisible(R.id.iv_approve_mask, true);
                holder.setText(R.id.iv_approve_mask, item.getStatusDescription());
                holder.setTextColor(R.id.iv_approve_mask, R.color.color_green_78c06e);
                holder.setImageResource(R.id.iv_approve_status,
                        R.drawable.ic_svg_check_green_78c06e_52dp);
                break;
            case ApplyConstant.INT_STATUS_REJECT:
                holder.setVisible(R.id.iv_approve_status, true);
                holder.setVisible(R.id.iv_approve_mask, true);
                holder.setText(R.id.iv_approve_mask, item.getStatusDescription());
                holder.setTextColor(R.id.iv_approve_mask, R.color.color_red_f46464);
                holder.setImageResource(R.id.iv_approve_status,
                        R.drawable.ic_svg_reject_red_f46464);
                break;
            default:
                holder.setVisible(R.id.iv_approve_status, false);
                break;
        }
    }
}
