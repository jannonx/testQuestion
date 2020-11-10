package com.guyuan.dear.focus.produce.util;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.guyuan.dear.customizeview.PieChartView;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * @description: PieChartView工具类
 * @author: Jannonx
 * @since: 2020/11/3 11:15
 * @company: 固远（深圳）信息技术有限公司
 */
public class ChartViewUtils {


    public static void setFocusChartView(PieChartView view, ComContractsBean data) {
        if (data == null) {
            return;
        }
        LinkedHashMap<String, Float> pieData = new LinkedHashMap<>();
        pieData.put("待生产", data.getExecutingContracts() * 1.f);
        pieData.put("生产完成", data.getExceptionContracts() * 1.f);
        pieData.put("生产中", data.getFinishedContracts() * 1.f);
        pieData.put("生产异常", data.getFinishedContracts() * 1.f);
        view.setData(pieData, "");
        view.getLegend().setEnabled(false);
        PieData dateSet = view.getData();
        PieDataSet pieDataSet = (PieDataSet) dateSet.getDataSet();
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10);
        pieDataSet.setColors(new ArrayList<Integer>() {
            {
                add(Color.parseColor("#FA8C16"));
                add(Color.parseColor("#2FC25B"));
                add(Color.parseColor("#1890FF"));
                add(Color.parseColor("#F04864"));
            }
        });
        dateSet.setDrawValues(true);
        dateSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format(Locale.CHINA, "%d", (int) entry.getY());
            }
        });
        view.setExtraOffsets(10, 10, 10, 10);
        view.setHoleRadius(65f);
        view.setRotationAngle(185);
        view.setTransparentCircleRadius(31f);
        view.setDrawHoleEnabled(true);
        view.animate();
    }

}