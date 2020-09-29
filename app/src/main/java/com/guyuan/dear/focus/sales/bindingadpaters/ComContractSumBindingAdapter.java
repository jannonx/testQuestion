package com.guyuan.dear.focus.sales.bindingadpaters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.guyuan.dear.focus.sales.bean.ComContractsBean;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 18:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractSumBindingAdapter {
    @BindingAdapter("setContractsYear")
    public static void setContractsYear(AppCompatTextView view, long timeMills){
        String year = CalenderUtils.getInstance().getYearByDate(timeMills);
        view.setText(year);
    }

    @BindingAdapter("setContractSumBarData")
    public static void setContractSumBarData(BarChart view, ComContractsBean data){
        //todo
    }
}
