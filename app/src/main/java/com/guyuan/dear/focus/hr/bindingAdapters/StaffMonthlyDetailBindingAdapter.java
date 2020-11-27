package com.guyuan.dear.focus.hr.bindingAdapters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 17:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailBindingAdapter {

    @BindingAdapter("showAttendState")
    public static void showAttendState(AppCompatImageView v,int state){
        //todo
    }

    @BindingAdapter("showChineseYearMonth")
    public static void showChineseYearMonth(AppCompatTextView view,long date){
        String yearMonth = CalenderUtils.getInstance().toChineseYearMonth(date);
        view.setText(yearMonth);
    }

}
