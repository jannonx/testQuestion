package com.guyuan.dear.focus.assess.adapter;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;
import com.guyuan.dear.utils.Graph.GraphUtil;
import com.guyuan.dear.utils.Graph.formatter.MonthXAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :我的关注布局绑定方法
 * @since: 2020/10/29 15:39
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessBindingAdapter {
    //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)
    private static final int SAVED = 0;
    private static final int PREPARED = 10;
    private static final int ASSESSING = 20;
    private static final int PASSED = 30;
    private static final int NOT_PASSED = 40;

    /**
     * @param tv     目标view
     * @param status 评审状态
     */
    @BindingAdapter("assessStatus")
    public static void setAssessStatus(TextView tv, int status) {
        switch (status) {
            case SAVED:
                tv.setTextAppearance(R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                tv.setText("保存草稿");
                break;

            case PREPARED:
                tv.setTextAppearance(R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                tv.setText("待评审");
                break;

            case ASSESSING:
                tv.setTextAppearance(R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                tv.setText("评审中");
                break;

            case PASSED:
                tv.setTextAppearance(R.style.TextTagGreen);
                tv.setBackgroundResource(R.drawable.bg_pass);
                tv.setText("通过");
                break;

            case NOT_PASSED:
                tv.setTextAppearance(R.style.TextNotPass);
                tv.setBackgroundResource(R.drawable.bg_not_pass);
                tv.setText("不通过");
                break;
        }
    }

    /**
     * @param iv
     * @param result 评审结果
     */
    @BindingAdapter("assessImgStatus")
    public static void setAssessStatus(ImageView iv, int result) {
        switch (result) {
            case 0://未评审
                iv.setImageResource(R.mipmap.wrong);
                break;

            case 1://通过
                iv.setImageResource(R.mipmap.right);
                break;

            case 2://不通过
                iv.setImageResource(R.mipmap.wrong);
                break;
        }

    }

    @BindingAdapter("assessResultStatus")
    public static void setAssessResultStatus(TextView tv, int result) {
        switch (result) {
            case 0://未评审
                tv.setText("未评审");
                break;

            case 1://通过
                tv.setText("通过评审");
                break;

            case 2://不通过
                tv.setText("未通过评审");
                break;
        }

    }


    /**
     * @param lineChart
     * @param list      线形图数据源
     */
    @BindingAdapter("assessLineChartData")
    public static void setAssessLineChartData(LineChart lineChart,
                                              List<AssessOverviewBean.AuditYearInfoVOListBean> list) {
        if (list != null) {
            Resources resources = DearApplication.getInstance().getResources();
            List<Entry> passList = new ArrayList<>();
            List<Entry> notPassList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                AssessOverviewBean.AuditYearInfoVOListBean bean = list.get(i);
                Entry passEntry = new Entry(i, bean.getPass());
                Entry notPassEntry = new Entry(i, bean.getNoPass());
                passList.add(passEntry);
                notPassList.add(notPassEntry);
            }

            //准备通过和不通过的数据集合
            List<List<Entry>> dataList = new ArrayList<>();
            dataList.add(passList);
            dataList.add(notPassList);

            //准备线的颜色集合
            List<Integer> colorList = new ArrayList<>();
            colorList.add(resources.getColor(R.color.color_pass));
            colorList.add(resources.getColor(R.color.color_not_pass));

            //准备图标提示文字
            List<String> legendList = new ArrayList<>();
            legendList.add("通过");
            legendList.add("不通过");

            MonthXAxisValueFormatter monthXAxisValueFormatter = new MonthXAxisValueFormatter();
            GraphUtil.showLineChart(lineChart, dataList, legendList, colorList, 12,
                    monthXAxisValueFormatter, null);
        }
    }

    @BindingAdapter(value = {"assessStartTime", "assessEndTime"})
    public static void setAssessTime(TextView tv, String startTime, String endTime) {
        StringBuilder time = new StringBuilder();
        if (!TextUtils.isEmpty(startTime)) {
            time.append(startTime);
        }

        if (!TextUtils.isEmpty(endTime)) {
            time.append(" ~ " + endTime);
        }

        tv.setText(time);
    }
}