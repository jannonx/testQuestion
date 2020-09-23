package com.guyuan.dear.customizeview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.teaanddogdog.mpandroidchartutil.PieChartFixCover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author 廖华凯
 * @since 2020/1/10 17:51
 **/
public class PieChartView extends PieChartFixCover {
    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setUsePercentValues(true);
        getDescription().setEnabled(false);
        setExtraOffsets(5, 10, 5, 5);

        setDragDecelerationFrictionCoef(0.95f);


        setCenterText("标题");

        setDrawHoleEnabled(false);


        setTransparentCircleColor(Color.WHITE);
        setTransparentCircleAlpha(110);

        setHoleRadius(58f);
        setTransparentCircleRadius(61f);

        setDrawEntryLabels(false);

        setDrawCenterText(false);

        setRotationAngle(0);
        // enable rotation of the chart by touch
        setRotationEnabled(false);
        setHighlightPerTapEnabled(false);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);


        animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setXOffset(0f);
        l.setTextColor(Color.parseColor("#8C8C8C"));
        l.setTextSize(10f);
        l.setForm(Legend.LegendForm.CIRCLE);

        // entry label styling
        setEntryLabelColor(Color.BLACK);
        setEntryLabelTextSize(12f);
    }

    public void setData(LinkedHashMap<String, Float> data, String title) {
        ArrayList<PieEntry> entries = new ArrayList<>();
//        ArrayList<String> labels = new ArrayList<>();

        Iterator<Map.Entry<String, Float>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Float> next = iterator.next();
//            labels.add(next.getKey());
            PieEntry entry = new PieEntry(next.getValue(), next.getKey());
            entries.add(entry);
        }

        PieDataSet dataSet = new PieDataSet(entries, title);

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(0.5f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(12f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#FA8C16"));
        colors.add(Color.parseColor("#1890FF"));
        colors.add(Color.parseColor("#F04864"));
        colors.add(Color.parseColor("#3BC25B"));
        colors.add(Color.parseColor("#3FC2C2"));
        colors.add(Color.parseColor("#FACC14"));

        dataSet.setColors(colors);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format(Locale.CHINA, "%.2f%%", value);
            }
        });
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.BLACK);
        pieData.setDrawValues(false);
        setData(pieData);

        // undo all highlights
        highlightValues(null);
        invalidate();
    }


//    public void showHrOverviewData(List<StaffsWrapper> data) {
//        ArrayList<PieEntry> entries = new ArrayList<>();
//
//        StaffsWrapper onDuty = null;
//        StaffsWrapper absent = null;
//        StaffsWrapper onLeave = null;
//        StaffsWrapper late = null;
//        StaffsWrapper leaveEarly = null;
//        for (StaffsWrapper wrapper : data) {
//            switch (wrapper.getType()) {
//                case StaffsWrapper.TYPE_ABSENT:
//                    absent = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_LATE:
//                    late = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_LEAVE_EARLY:
//                    leaveEarly = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_ON_DUTY:
//                    onDuty = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_ON_LEAVE:
//                    onLeave = wrapper;
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        int onDutyCount = onDuty != null ? onDuty.getList().size() : 0;
//        int absentCount = absent != null ? absent.getList().size() : 0;
//        int onLeaveCount = onLeave != null ? onLeave.getList().size() : 0;
//        int lateCount = late != null ? late.getList().size() : 0;
//        int leaveEarlyCount = leaveEarly != null ? leaveEarly.getList().size() : 0;
//        int staffTotal = onDutyCount + absentCount + onLeaveCount + lateCount + leaveEarlyCount;
//
//        float onDutyPercent = onDutyCount * 1.f / staffTotal;
//        float absentPercent = absentCount * 1.f / staffTotal;
//        float onLeavePercent = onLeaveCount * 1.f / staffTotal;
//        float latePercent = lateCount * 1.f / staffTotal;
//        float leaveEarlyPercent = leaveEarlyCount * 1.f / staffTotal;
//
//        ArrayList<Integer> colors = new ArrayList<>();
//        for (StaffsWrapper wrapper : data) {
//            String label = null;
//            PieEntry pieEntry = null;
//            switch (wrapper.getType()) {
//                case StaffsWrapper.TYPE_ABSENT:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), absentPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(absentPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_LATE:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), latePercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(latePercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_LEAVE_EARLY:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), leaveEarlyPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(leaveEarlyPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_ON_DUTY:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), onDutyPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(onDutyPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_ON_LEAVE:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), onLeavePercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(onLeavePercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                default:
//                    break;
//            }
//            if (pieEntry != null) {
//                entries.add(pieEntry);
//            }
//        }
//
//        PieDataSet dataSet = new PieDataSet(entries, "");
//
//        dataSet.setDrawIcons(false);
//
//        dataSet.setSliceSpace(0.5f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(12f);
//        dataSet.setColors(colors);
//
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.5f);
//        dataSet.setValueLinePart2Length(0.5f);
//
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//
//        PieData pieData = new PieData(dataSet);
//        pieData.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return String.format(Locale.CHINA, "%.2f%%", value);
//            }
//        });
//        pieData.setValueTextSize(11f);
//        pieData.setValueTextColor(Color.BLACK);
//        pieData.setDrawValues(false);
//        setData(pieData);
//        // undo all highlights
//        highlightValues(null);
//        invalidate();
//
//    }

//    public void showHrGroups(List<HrAttendanceGrouping> data) {
//        ArrayList<PieEntry> entries = new ArrayList<>();
//
//        HrAttendanceGrouping onDuty = null;
//        HrAttendanceGrouping absent = null;
//        HrAttendanceGrouping onLeave = null;
//        HrAttendanceGrouping late = null;
//        HrAttendanceGrouping leaveEarly = null;
//        for (HrAttendanceGrouping wrapper : data) {
//            switch (wrapper.getType()) {
//                case StaffsWrapper.TYPE_ABSENT:
//                    absent = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_LATE:
//                    late = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_LEAVE_EARLY:
//                    leaveEarly = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_ON_DUTY:
//                    onDuty = wrapper;
//                    break;
//                case StaffsWrapper.TYPE_ON_LEAVE:
//                    onLeave = wrapper;
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        int onDutyCount = onDuty != null ? onDuty.getGroupSize() : 0;
//        int absentCount = absent != null ? absent.getGroupSize() : 0;
//        int onLeaveCount = onLeave != null ? onLeave.getGroupSize() : 0;
//        int lateCount = late != null ? late.getGroupSize() : 0;
//        int leaveEarlyCount = leaveEarly != null ? leaveEarly.getGroupSize() : 0;
//        int staffTotal = onDutyCount + absentCount + onLeaveCount + lateCount + leaveEarlyCount;
//
//        float onDutyPercent = onDutyCount * 1.f / staffTotal;
//        float absentPercent = absentCount * 1.f / staffTotal;
//        float onLeavePercent = onLeaveCount * 1.f / staffTotal;
//        float latePercent = lateCount * 1.f / staffTotal;
//        float leaveEarlyPercent = leaveEarlyCount * 1.f / staffTotal;
//
//        ArrayList<Integer> colors = new ArrayList<>();
//        for (HrAttendanceGrouping wrapper : data) {
//            String label = null;
//            PieEntry pieEntry = null;
//            switch (wrapper.getType()) {
//                case StaffsWrapper.TYPE_ABSENT:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), absentPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(absentPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_LATE:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), latePercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(latePercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_LEAVE_EARLY:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), leaveEarlyPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(leaveEarlyPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_ON_DUTY:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), onDutyPercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(onDutyPercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                case StaffsWrapper.TYPE_ON_LEAVE:
////          label = String.format(Locale.CHINA, "%s %.2f%%", wrapper.getLegend(), onLeavePercent * 100);
//                    label = String.format(Locale.CHINA, "%s", wrapper.getLegend());
//                    pieEntry = new PieEntry(onLeavePercent, label);
//                    colors.add(wrapper.getColorByType());
//                    break;
//                default:
//                    break;
//            }
//            if (pieEntry != null) {
//                entries.add(pieEntry);
//            }
//        }
//
//        PieDataSet dataSet = new PieDataSet(entries, "");
//
//        dataSet.setDrawIcons(false);
//
//        dataSet.setSliceSpace(0.5f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(12f);
//        dataSet.setColors(colors);
//
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.5f);
//        dataSet.setValueLinePart2Length(0.5f);
//
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//
//        PieData pieData = new PieData(dataSet);
//        pieData.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return String.format(Locale.CHINA, "%.2f%%", value);
//            }
//        });
//        pieData.setValueTextSize(11f);
//        pieData.setValueTextColor(Color.BLACK);
//        pieData.setDrawValues(false);
//        setData(pieData);
//        // undo all highlights
//        highlightValues(null);
//        invalidate();
//
//    }

}
