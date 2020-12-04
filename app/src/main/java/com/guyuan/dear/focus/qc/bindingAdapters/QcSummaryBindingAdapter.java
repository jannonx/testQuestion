package com.guyuan.dear.focus.qc.bindingAdapters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.utils.CalenderUtils;

import java.util.Locale;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/11 17:40
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSummaryBindingAdapter {

    @BindingAdapter("showYearMonthByDate")
    public static void showYearMonthByDate(AppCompatTextView view, long date) {
        String yearAndMonth = CalenderUtils.getInstance().toLongYearAndMonth(date);
        view.setText(yearAndMonth);
    }

    @BindingAdapter("showYearMonthDayByDate")
    public static void showYearMonthDayByDate(AppCompatTextView view, long date) {
        String yearAndMonth = CalenderUtils.getInstance().toSmartFactoryDateFormatByDay(date);
        view.setText(yearAndMonth);
    }

    @BindingAdapter(value = {"setQcPassCount", "setQcSampleSize"}, requireAll = true)
    public static void calculateQcSummaryPassRate(AppCompatTextView view, int passCount, int sampleSize) {
        if (sampleSize == 0) {
            view.setText("0%");
            return;
        }
        float rate = passCount * 1.f / sampleSize;
        view.setText(String.format(Locale.CHINA, "%.2f%%", rate * 100));
    }
}
