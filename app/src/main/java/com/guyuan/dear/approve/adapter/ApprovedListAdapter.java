package com.guyuan.dear.approve.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;


import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApplyType;
import com.guyuan.dear.approve.bean.ApproveUserBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.R;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;


/**
 * @description: 掌上办公--我发起的--请假列表
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApprovedListAdapter extends BaseRecyclerAdapter<ApplyBean> {

    private AppCompatTextView tvName;
    private AppCompatTextView tvStartDate;
    private AppCompatTextView tvTerminateDate;
    private AppCompatTextView labelStartDate;
    private AppCompatTextView labelEndDate;

    private ApproveUserBean mData;

    public ApprovedListAdapter(Context context, @NonNull List<ApplyBean> listData,
                               int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ApplyBean bean, int position) {
        mData = bean.getUser();
        String name = mData == null ? "" : mData.getNickName() == null ? "我" : mData.getNickName();

        tvName = holder.getView(R.id.tv_title);
        tvStartDate = holder.getView(R.id.tv_start_date);
        tvTerminateDate = holder.getView(R.id.tv_end_date);
        labelStartDate = holder.getView(R.id.label_start_date);
        labelEndDate = holder.getView(R.id.label_end_date);
        AppCompatImageView ivStatusMask = holder.getView(R.id.iv_approve_status);
        AppCompatTextView tvStatusMask = holder.getView(R.id.iv_approve_mask);


        TextView tvType = holder.getView(R.id.tv_type);
        //请假显示具体类型
        if (bean.getArType() == 1) {
            tvType.setVisibility(View.VISIBLE);
            tvType.setText(bean.getVacationType());
        } else {
            tvType.setVisibility(View.GONE);
        }


        switch (ApplyType.toType(bean.getArType())) {
            case TYPE_LEAVE:
                setFullTime(name + "提交的请假", bean.getSleaveTime(), bean.getEleaveTime());
                break;
            case TYPE_BUSINESS:
                setFullTime(name + "提交的出差", bean.getSleaveTime(), bean.getEleaveTime());
                break;
            case TYPE_OUTING:
                setFullTime(name + "提交的外出", bean.getSleaveTime(), bean.getEleaveTime());
                break;
            case TYPE_OVERTIME:
                setFullTime(name + "提交的加班", bean.getSleaveTime(), bean.getEleaveTime());
                break;
            case TYPE_EMPLOY:
                setOtherTime(name + "提交的招聘", "需求岗位", bean.getDemandPost(), "希望到岗日期", bean.getEleaveTime());
                break;
            case TYPE_LEAVE_OFFICE:
                setLabel(name + "提交的离职", "入职日期", bean.getSleaveTime(), "最后工作日", bean.getEleaveTime());
                break;
            case TYPE_EXPENSE_REIMBURSEMENT:
                setLabelTime(name + "提交的报销", "报销金额", bean.getMoney() + "", "报销类型", bean.getAttribute1());
                break;
            case TYPE_PAY:
                setOtherTime(name + "提交的付款", "付款方式", bean.getPayType(), "支付日期", bean.getSleaveTime());
                break;
            case TYPE_PRETtY_CASH:
                setLabel(name + "提交的备用金", "使用日期", bean.getSleaveTime(), "归还日期", bean.getEleaveTime());
                break;
            case TYPE_SEAL:
                setOtherTime(name + "提交的用印", "用印文件名称", bean.getFile(), "日期", bean.getSleaveTime());
                break;
            case TYPE_COMMON:
                setLabelTime(name + "提交的通用审批", "申请内容", bean.getAttribute5(), "审批详情", bean.getDescription());
                break;
            case TYPE_PROCUREMENT:
                setOtherTime(name + "提交的采购", "名称", bean.getAttribute1(), "期望交付日期", bean.getSleaveTime());
                break;
            default:
        }

        tvStatusMask.setText(bean.getStatusDescription());
        switch (bean.getStatus()) {
            case ApplyConstant.INT_STATUS_ING:
                ivStatusMask.setImageResource(R.drawable.ic_svg_pending_for_approval);
                tvStatusMask.setTextColor(context.getResources().getColor(R.color.color_yellow_f7b500));
                break;
            case ApplyConstant.INT_STATUS_PASS:
                ivStatusMask.setImageResource(R.drawable.ic_svg_check_green_78c06e_52dp);
                tvStatusMask.setTextColor(context.getResources().getColor(R.color.color_green_78c06e));
                break;
            case ApplyConstant.INT_STATUS_REJECT:
                ivStatusMask.setImageResource(R.drawable.ic_svg_reject_red_f46464);
                tvStatusMask.setTextColor(context.getResources().getColor(R.color.color_red_f46464));
                break;
            default:
                break;
        }


    }

    public void setFullTime(String title, String startTime, String endTime) {
        setData(title, "开始日期", null, startTime, "结束日期", null, endTime);
    }

    public void setLabelTime(String title, String labelStart, String startText, String labelEnd, String endText) {
        setData(title, labelStart, startText, "", labelEnd, endText, "");
    }

    public void setOtherTime(String title, String labelStart, String startText, String labelEnd, String endTime) {
        setData(title, labelStart, startText, "", labelEnd, null, endTime);
    }

    public void setLabel(String title, String labelStart, String startTime, String labelEnd, String endTime) {
        setData(title, labelStart, null, startTime, labelEnd, null, endTime);
    }

    public void setData(String title, String labelStart, String startText, String startTime, String labelEnd, String endText, String endTime) {
        labelStartDate.setText(labelStart);
        labelEndDate.setText(labelEnd);
        tvStartDate.setText(startText == null ? startTime : startText);
        tvTerminateDate.setText(endText == null ? endTime : endText);
        tvName.setText(title);
    }


}
