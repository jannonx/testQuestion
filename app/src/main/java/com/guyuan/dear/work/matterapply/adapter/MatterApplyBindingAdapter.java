package com.guyuan.dear.work.matterapply.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/24 10:29
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyBindingAdapter {
    @BindingAdapter("matterStatus")
    public static void setMatterStatus(TextView tv, int status) {
        switch (status) {
            case 1:
                tv.setText("审批中");
                tv.setTextAppearance(R.style.TextTagBlue);
                break;

            case 2:
                tv.setText("审批通过");
                tv.setTextAppearance(R.style.TextTagGreen);
                break;

            case 3:
                tv.setText("审批被驳回");
                tv.setTextAppearance(R.style.TextTagOrange);
                break;
        }
    }
}