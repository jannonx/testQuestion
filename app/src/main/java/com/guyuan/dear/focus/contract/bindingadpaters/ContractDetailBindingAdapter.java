package com.guyuan.dear.focus.contract.bindingadpaters;

import android.view.View;

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
        if(date<=0){
            view.setVisibility(View.GONE);
            return;
        }else {
            view.setVisibility(View.VISIBLE);
        }
        view.setText(
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(date)
        );
    }
}
