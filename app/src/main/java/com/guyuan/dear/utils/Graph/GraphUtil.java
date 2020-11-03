package com.guyuan.dear.utils.Graph;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.guyuan.dear.utils.Graph.entity.BarEntity;
import com.guyuan.dear.utils.Graph.formatter.CustomXAxisValueFormatter;
import com.guyuan.dear.utils.Graph.setting.BarChartSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TL
 * on 2020/1/11
 * 图表工具类
 */
public class GraphUtil {

  /**
   * 显示柱形图
   */
  public static void showBarChart(BarChart barChart, List<BarEntity> barEntityList, String name) {
    BarChartSetting.initBarChart(barChart);
    XAxis xAxis = barChart.getXAxis();
    xAxis.setLabelCount(barEntityList.size());
    ArrayList<BarEntry> entries = new ArrayList<>();
    for (int i = 0; i < barEntityList.size(); i++) {
      BarEntry barEntry = new BarEntry(i, (float) barEntityList.get(i).getNumber());
      entries.add(barEntry);
    }

    //    X轴自定义值
    xAxis.setValueFormatter(new CustomXAxisValueFormatter(barEntityList));
    //右侧Y轴自定义值
    //    rightAxis.setValueFormatter();

    // 每一个BarDataSet代表一类柱状图
    BarDataSet barDataSet;

    if (barChart.getData() != null &&
        barChart.getData().getDataSetCount() > 0) {
      barDataSet = (BarDataSet) barChart.getData().getDataSetByIndex(0);
      barDataSet.setValues(entries);
      barChart.getData().notifyDataChanged();
      barChart.notifyDataSetChanged();
    } else {
      barDataSet = new BarDataSet(entries, name);
      barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
      barDataSet.setDrawValues(false);

      ArrayList<IBarDataSet> dataSets = new ArrayList<>();
      dataSets.add(barDataSet);

      BarData data = new BarData(dataSets);
      barChart.setData(data);
      barChart.setFitBars(true);
    }

    barChart.invalidate();
  }



}
