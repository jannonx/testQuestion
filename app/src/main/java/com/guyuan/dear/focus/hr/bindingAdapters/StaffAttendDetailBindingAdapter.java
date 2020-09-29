package com.guyuan.dear.focus.hr.bindingAdapters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.utils.CalenderUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/24 18:11
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendDetailBindingAdapter {

    @BindingAdapter("setEnrollDate")
    public static void setEnrollDate(AppCompatTextView view, Date date) {
        String txt;
        if(date!=null){
             txt = CalenderUtils.getInstance().toChineseYearMonthDayFormat(date.getTime());
        }else {
            txt="";
        }

        view.setText(txt);
    }

    @BindingAdapter("setTableDate")
    public static void setTableDate(AppCompatTextView view, long date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月汇总", Locale.CHINA);
        String txt =format.format(new Date(date));
        view.setText(txt);
    }

    @BindingAdapter("setGender")
    public static void setGender(AppCompatTextView view,int gender){
        String txt = "";
        switch (gender){
            case 0:
                txt="女";
                break;
            case 1:
                txt="男";
                break;
            default:
                txt="未知";
                break;
        }
        view.setText(txt);
    }

    @BindingAdapter("setStaffStatus")
    public static void setStaffStatus(AppCompatTextView view, int status) {
        String txt;
        switch (status) {
            case HrStatusGroup.GRP_TYPE_ABSENT:
                txt = "缺席";
                break;
            case HrStatusGroup.GRP_TYPE_LATE:
                txt="迟到";
                break;
            case HrStatusGroup.GRP_TYPE_LEAVE_EARLY:
                txt="早退";
                break;
            case HrStatusGroup.GRP_TYPE_NORMAL:
                txt="正常";
                break;
            default:
                txt="";
                break;
        }
        view.setText(txt);
    }
}
