package com.guyuan.dear.focus.sales.bindingadpaters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 11:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailBindingAdapter {
    @BindingAdapter("setContractDate")
    public static void setContractDate(AppCompatTextView view,long date){
        view.setText(
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(date)
        );
    }
}
