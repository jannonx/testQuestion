package com.guyuan.dear.office.approval.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/26 10:34
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalBindingAdapter {

    @BindingAdapter("approvalType")
    public static void setApprovalType(TextView tv, int approvalType) {
        switch (approvalType) {
            case 1:
                tv.setText("制定主生产计划");
                break;

            case 2:
                tv.setText("子生产计划暂停");
                break;

            case 3:
                tv.setText("子生产计划激活");
                break;
        }
    }

    @BindingAdapter(value = {"approvalStatus", "isApproval"})
    public static void setApprovalStatus(TextView tv, int status, boolean isApproval) {

        if (isApproval) {
            switch (status) {
                case 0:
                    tv.setText("待审批");
                    tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
                    tv.setBackgroundResource(R.drawable.bg_assess);
                    break;

                case 1:
                    tv.setText("已同意");
                    tv.setTextAppearance(R.style.TextTagGreen);
                    tv.setBackgroundResource(R.drawable.bg_pass);
                    break;

                case 2:
                    tv.setText("已驳回");
                    tv.setTextAppearance(R.style.TextTagOrange);
                    tv.setBackgroundResource(R.drawable.bg_tag_orange);
                    break;
            }
        } else {//已审批列表0表示该审批未完全审批，当前人已同意
            switch (status) {
                case 0:
                case 1:
                    tv.setText("已同意");
                    tv.setTextAppearance(R.style.TextTagGreen);
                    tv.setBackgroundResource(R.drawable.bg_pass);
                    break;

                case 2:
                    tv.setText("已驳回");
                    tv.setTextAppearance(R.style.TextTagOrange);
                    tv.setBackgroundResource(R.drawable.bg_tag_orange);
                    break;
            }
        }
    }
}