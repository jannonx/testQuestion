package com.guyuan.dear.office.approval.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

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
}