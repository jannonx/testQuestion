package com.guyuan.dear.message.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:25
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageBindingAdapter {

    @BindingAdapter("readStatusTitle")
    public static void setReadStatusTitle(TextView tv, int status) {
        if (status == 1) {//已读
            tv.setTextColor(tv.getResources().getColor(R.color.color_grey_cccccc));
        } else {
            tv.setTextColor(tv.getResources().getColor(R.color.color_blue_1890ff));
        }
    }

    @BindingAdapter("readStatusContent")
    public static void setReadStatusContent(TextView tv, int status) {
        if (status == 1) {//已读
            tv.setTextColor(tv.getResources().getColor(R.color.color_grey_cccccc));
        } else {
            tv.setTextColor(tv.getResources().getColor(R.color.color_black_333333));
        }
    }
}