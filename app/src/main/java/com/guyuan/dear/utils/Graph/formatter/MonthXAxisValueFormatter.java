package com.guyuan.dear.utils.Graph.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * @author : tl
 * @description :X轴显示月份
 * @since: 2020/11/4 15:41
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MonthXAxisValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String x=((int)value + 1) + "月";
        return x;
    }
}