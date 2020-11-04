package com.guyuan.dear.utils.Graph.setting;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/4 10:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class LineChartSetting {

    private static LineChartSetting lineChartSetting;
    private LineChart lineChart;
    private Context context = DearApplication.getInstance();

    public static LineChartSetting getInstance() {
        if (lineChartSetting == null) {
            lineChartSetting = new LineChartSetting();
        }

        return lineChartSetting;
    }

    public LineChartSetting initLineChart(@Nullable LineChart lineChart) {
        this.lineChart = lineChart;
        // no description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(false);

        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        // set an alternative background color
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.setNoDataText("暂无数据");
        lineChart.setNoDataTextColor(context.getResources().getColor(R.color.color_blue_004fbb));
        return lineChartSetting;
    }

    //设置图例
    public LineChartSetting setLegend() {
        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.SQUARE);
        l.setXEntrySpace(10);
        //l.setTypeface(tfLight);
        l.setTextSize(12f);
        l.setTextColor(context.getResources().getColor(R.color.color_grey_999999));
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        return lineChartSetting;
    }

    //设置X轴
    public LineChartSetting setXAxis(IAxisValueFormatter xAxisFormatter, int labelCount) {
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.setTypeface(tfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(context.getResources().getColor(R.color.color_grey_999999));
        if (xAxisFormatter != null) {
            xAxis.setValueFormatter(xAxisFormatter);
        }
        xAxis.setLabelCount(labelCount, true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        return lineChartSetting;
    }

    //设置左边Y轴
    public LineChartSetting setYAxisLeft(IAxisValueFormatter xAxisFormatter) {
        YAxis yAxisLeft = lineChart.getAxisLeft();
        lineChart.getAxisRight().setEnabled(false);//默认不显示右边Y轴
        // yAxisLeft.setTypeface(tfLight);
        yAxisLeft.setTextColor(ColorTemplate.getHoloBlue());
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setGranularityEnabled(true);
        if (xAxisFormatter != null) {
            yAxisLeft.setValueFormatter(xAxisFormatter);
        }
        return lineChartSetting;
    }

    //设置右边Y轴
    public LineChartSetting setYAxisRight(IAxisValueFormatter xAxisFormatter) {
        YAxis yAxisRight = lineChart.getAxisRight();
        //    yAxisRight.setTypeface(tfLight);
        yAxisRight.setTextColor(Color.RED);
        yAxisRight.setEnabled(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawZeroLine(false);
        yAxisRight.setGranularityEnabled(false);
        if (xAxisFormatter != null) {
            yAxisRight.setValueFormatter(xAxisFormatter);
        }
        return lineChartSetting;
    }

    //设置数据
    public void setData(List<List<Entry>> dataSets, List<String> legendList, List<Integer> colorList) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            lineChart.getData().getDataSets();
            for (int i = 0; i < lineChart.getData().getDataSetCount(); i++) {
                LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSets().get(i);

                lineDataSet.setValues(dataSets.get(i));
            }
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            List<ILineDataSet> lineDataSetList = new ArrayList<>();
            for (int i = 0; i < dataSets.size(); i++) {
                List<Entry> entries = dataSets.get(i);
                String legend = legendList.get(i);
                int color = colorList.get(i);
                lineDataSetList.add(getLineDataSet(entries, legend, color));
            }

            //根据数据源设置Y轴的最大最小值
            YAxis yAxisLeft = lineChart.getAxisLeft();
            yAxisLeft.setAxisMaximum(getMaxNumber(dataSets));
            yAxisLeft.setAxisMinimum(0f);

            LineData data = new LineData(lineDataSetList);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);
            lineChart.setData(data);
        }
    }

    //设置线的参数
    private LineDataSet getLineDataSet(List<Entry> entries, String legend, int color) {
        LineDataSet lineDataSet = new LineDataSet(entries, legend);
        //设置数据绘制根据左边Y轴还是右边Y轴
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(color);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(color);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        //节点
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawCircleHole(false);
        return lineDataSet;
    }


    private float getMaxNumber(List<List<Entry>> dataSets) {
        float maxNumber = 0;
        for (int i = 0; i < dataSets.size(); i++) {
            List<Entry> entryList = dataSets.get(i);
            for (int j = 0; j < entryList.size(); j++) {
                if (maxNumber < entryList.get(j).getY()) {
                    maxNumber = entryList.get(j).getY();
                }
            }
        }

        return (float) Math.floor(maxNumber) > 0 ? (float) Math.floor(maxNumber) : 50;
    }
}