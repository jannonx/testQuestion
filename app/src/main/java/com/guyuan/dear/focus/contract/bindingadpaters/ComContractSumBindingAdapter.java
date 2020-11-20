package com.guyuan.dear.focus.contract.bindingadpaters;

import android.graphics.Color;
import android.service.autofill.Dataset;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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

    @BindingAdapter("setContractSumPieData")
    public static void setContractSumPieData(PieChartView view,ComContractsBean data){
        if(data==null){
            return;
        }
        LinkedHashMap<String,Float> pieData=new LinkedHashMap<>();
        pieData.put("正在执行",data.getExecutingContracts()*1.f);
        pieData.put("执行异常",data.getExceptionContracts()*1.f);
        pieData.put("已完成",data.getFinishedContracts()*1.f);
        view.setData(pieData,"");
        view.getLegend().setEnabled(false);
        PieData dateSet = view.getData();
        PieDataSet pieDataSet = (PieDataSet) dateSet.getDataSet();
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20);
        pieDataSet.setColors(new ArrayList<Integer>(){
            {
                add(Color.parseColor("#1890FF"));
                add(Color.parseColor("#F04864"));
                add(Color.parseColor("#2FC25B"));
            }
        });
        dateSet.setDrawValues(true);
        dateSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int y = (int) entry.getY();
                if(y>0){
                    return String.format(Locale.CHINA,"%d", y);
                }else {
                    return "";
                }
            }
        });
        view.setExtraOffsets(10, 10, 10, 10);
        view.setHoleRadius(30f);
        view.setTransparentCircleRadius(31f);
        view.setDrawHoleEnabled(true);
        view.animate();
    }

    @BindingAdapter("setContractSumBarData")
    public static void setContractSumBarData(BarChart view, ComContractsBean data){
        if(data==null){
            return;
        }
        BarData barData = new BarData();
        List<BarEntry> barEntries = new ArrayList<BarEntry>();
        int total = data.getContractsTotal();
        BarEntry entryTotal = new BarEntry(0,total);
        barEntries.add(entryTotal);
        int delivers = data.getPreAnnualDelivers();
        BarEntry entryDelivers = new BarEntry(1,delivers);
        barEntries.add(entryDelivers);
        int newCntrct = data.getNewContracts();
        BarEntry entryNewContr=new BarEntry(2,newCntrct);
        barEntries.add(entryNewContr);
        int finished = data.getFinishedContracts();
        BarEntry entryFinished = new BarEntry(3,finished);
        barEntries.add(entryFinished);
        int unfinished = data.getUnfinishedContracts();
        BarEntry entryUnfinished = new BarEntry(4,unfinished);
        barEntries.add(entryUnfinished);
        int executing = data.getExecutingContracts();
        BarEntry entryExecuting = new BarEntry(5,executing);
        barEntries.add(entryExecuting);
        int excpt = data.getExceptionContracts();
        BarEntry entryExcpt = new BarEntry(6,excpt);
        barEntries.add(entryExcpt);

        IBarDataSet dataSet = new BarDataSet(barEntries,"");
        barData.addDataSet(dataSet);

        YAxis left = view.getAxisLeft();
        left.setAxisMinimum(0);
        left.setEnabled(false);
        YAxis right = view.getAxisRight();
        right.setEnabled(false);
        XAxis bottom = view.getXAxis();
        bottom.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottom.setAxisMaximum(7);
        bottom.setAxisMinimum(-1);
        bottom.setDrawGridLines(false);
        bottom.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String result ="";
                switch ((int) value){
                    case 0:
                        result="总数";
                        break;
                    case 1:
                        result="交付";
                        break;
                    case 2:
                        result="新签";
                        break;
                    case 3:
                        result="完成";
                        break;
                    case 4:
                        result="未完成";
                        break;
                    case 5:
                        result="执行";
                        break;
                    case 6:
                        result="异常";
                        break;
                    default:
                        break;
                }
                return result;
            }
        });

        barData.setBarWidth(0.75f);
        view.getLegend().setEnabled(false);
        view.getDescription().setEnabled(false);
        view.setDragEnabled(false);
        view.setScaleEnabled(false);
        view.setBorderColor(Color.TRANSPARENT);
        view.setTouchEnabled(false);
        view.setData(barData);
        view.invalidate();
        view.animate();
    }
}
