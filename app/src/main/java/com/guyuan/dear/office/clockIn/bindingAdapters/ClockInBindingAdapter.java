package com.guyuan.dear.office.clockIn.bindingAdapters;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.CalenderUtils;

import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA;
import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA;
import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA;
import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA;
import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA;
import static com.guyuan.dear.office.clockIn.view.ClockInViewModel.ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 12:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInBindingAdapter {
    @BindingAdapter("showHourAndMinute")
    public static void showHourAndMinute(AppCompatTextView view, long time) {
        String hourAndMinute = CalenderUtils.getInstance().toHourAndMinute(time);
        view.setText(hourAndMinute);
    }

    @BindingAdapter("showIsPunched")
    public static void showIsPunched(AppCompatImageView iv, boolean isPunched) {
        if (isPunched) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(R.drawable.ic_svg_checked_blue_2c87ff);
        } else {
            iv.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("showMyClockInDescription")
    public static void showMyClockInDescription(AppCompatTextView view, long myPunchTime) {
        if (myPunchTime <= 0) {
            view.setText("未打卡");
        } else {
            String hourAndMinute = CalenderUtils.getInstance().toChineseHourAndMinute(myPunchTime);
            view.setText(hourAndMinute + "已打卡");
        }
    }

    @BindingAdapter("showMyCheckOutDescription")
    public static void showMyCheckOutDescription(AppCompatTextView view, long myPunchTime) {
        if (myPunchTime <= 0) {
            view.setText("未打卡");
        } else {
            String hourAndMinute = CalenderUtils.getInstance().toChineseHourAndMinute(myPunchTime);
            view.setText(hourAndMinute + "已签退");
        }
    }

    @BindingAdapter("showTime")
    public static void showTime(AppCompatTextView view,long time){
        String format = CalenderUtils.getInstance().toStandardHourMinSecFormat(time);
        view.setText(format);
    }

    /**
     * @param view
     * @param state 参考 {@link com.guyuan.dear.office.clockIn.view.ClockInViewModel#ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA}
     *              以及相关其他状态
     */
    @BindingAdapter("showPunchButtonLabelByState")
    public static void showPunchButtonLabelByState(AppCompatTextView view, int state) {
        switch (state) {
            case ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA:
                view.setText("上班打卡");
                break;
            case ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA:
                view.setText("下班打卡");
                break;
            case ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA:
                view.setText("外勤签退");
                break;
            case ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA:
            case ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA:
                view.setText("下班打卡");
                break;
            case ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA:
            default:
                view.setText("外勤打卡");
                break;
        }
    }

    @BindingAdapter(value = {"isInClockInArea","location"})
    public static void showLocationDescription(AppCompatTextView textView,boolean isInClockInArea,String location){
        StringBuilder sb =new StringBuilder();
        if(isInClockInArea){
            sb.append("已进入考勤范围:");
        }else {
            sb.append("当前考勤位置:");
        }
        sb.append(location);
        textView.setText(sb.toString());
    }

    @BindingAdapter("changePunchButtonColor")
    public static void changePunchButtonColor(View button,boolean isInsideClockInArea){
        if(isInsideClockInArea){
            button.setBackgroundResource(R.drawable.shape_big_sphere_blue_1890ff);
        }else {
            button.setBackgroundResource(R.drawable.shape_big_sphere_green_2fc25b);
        }
    }
}
