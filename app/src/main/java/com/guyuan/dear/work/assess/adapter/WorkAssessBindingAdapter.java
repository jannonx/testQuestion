package com.guyuan.dear.work.assess.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.focus.assess.ui.FocusAssessListFragment;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 17:31
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessBindingAdapter {

    //设置列表倒计时
    @BindingAdapter(value = {"countDown", "startTime"})
    public static void setCountDown(TextView tv, int status, String startTime) {
        if (status == FocusAssessListFragment.NOT_START_ASSESS && !TextUtils.isEmpty(startTime)) {  //待评审
            setCountDownByText(tv, startTime);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    private static void setCountDownByText(TextView tv, String startTime) {
        long requestTime = CalenderUtils.getInstance().getTimeToLong(startTime);
        long currentTime = System.currentTimeMillis();
        if (currentTime >= requestTime) {
            tv.setVisibility(View.VISIBLE);
            tv.setText("已延期");
        } else if (requestTime - currentTime <= 5 * 60 * 1000) {//开始时间离当前时间5分钟内
            tv.setVisibility(View.VISIBLE);
            tv.setText((requestTime - currentTime) / (1000 * 60) + "分钟后开始会议");
        } else {
            tv.setVisibility(View.GONE);
        }
    }

}