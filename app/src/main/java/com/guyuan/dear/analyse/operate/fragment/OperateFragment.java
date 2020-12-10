package com.guyuan.dear.analyse.operate.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.github.mikephil.charting.data.Entry;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.activity.OperateListActivity;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.bean.OperateStatisticsBean;
import com.guyuan.dear.analyse.operate.bean.OperateType;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.databinding.FragmentAnalyseOperateBinding;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.Graph.GraphUtil;
import com.guyuan.dear.utils.Graph.formatter.MonthXAxisValueFormatter;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.StringUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

/**
 * @description: 我的关注--工程现场
 * @author: 许建宁
 * @since: 2020/11/17 11:54
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateFragment extends BaseDataBindingFragment<FragmentAnalyseOperateBinding, OperateViewModel> implements
        View.OnClickListener {

    public static final String TAG = OperateFragment.class.getSimpleName();
    private CalenderUtils calenderUtils;
    private Date dates;
    private OperateOverViewBean overViewData;

    public static OperateFragment newInstance() {
        Bundle args = new Bundle();
        OperateFragment fragment = new OperateFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_analyse_operate;
    }

    @Override
    protected void initialization() {
        dates = new Date();
        calenderUtils = CalenderUtils.getInstance();
        binding.tvSelectEndTime.setText(calenderUtils.getYearByDate(dates.getTime()));
        viewModel.getOperateOverViewData(calenderUtils.getDateByDate(dates.getTime()));
        viewModel.getOverViewEvent().observe(getActivity(), new Observer<OperateOverViewBean>() {
            @Override

            public void onChanged(OperateOverViewBean data) {
                setData(overViewData = data);
            }
        });

        viewModel.getOperateStatistics(calenderUtils.getDateByDate(dates.getTime()));
        viewModel.getStatisticsEvent().observe(getActivity(), new Observer<OperateStatisticsBean>() {
            @Override

            public void onChanged(OperateStatisticsBean data) {
                setStatisticsData(data);
            }
        });
        binding.llContent.setOnClickListener(this);
        binding.clActual.setOnClickListener(this);
        binding.clTotal.setOnClickListener(this);


    }

    private void setStatisticsData(OperateStatisticsBean data) {
        /**
         *    "cost": 3450309,
         *     "month": "2020-11"
         * 1171595852
         */

        Resources resources = DearApplication.getInstance().getResources();
        List<Entry> payList = new ArrayList<>();
        List<Entry> costList = new ArrayList<>();
        List<OperateStatisticsBean.PaymentCollectionVOSBean> paymentCollectionVOS = data.getPaymentCollectionVOS();
//        for (OperateStatisticsBean.PaymentCollectionVOSBean bean : paymentCollectionVOS) {
//            LogUtils.showLog(".getMonth=" + bean.getMonth() + "...Payment=" + bean.getPaymentCollection());
//        }
//        for (int i = 0; i < 12; i++) {
//            Entry passEntry = new Entry();
//            passEntry.setX(i);
//            Random random = new Random();
//            float floatNumber = random.nextFloat() * 10000;
//            passEntry.setY(floatNumber);
//            payList.add(passEntry);
//        }
//
//        for (int i = 0; i < 12; i++) {
//            Entry passEntry = new Entry();
//            passEntry.setX(i);
//            Random random = new Random();
//            float floatNumber = random.nextFloat() * 10000;
//            passEntry.setY(floatNumber);
//            costList.add(passEntry);
//        }

        for (int i = 0; i < 12; i++) {
            Entry passEntry = new Entry();
            passEntry.setX(i);
            for (int ij = 0; ij < paymentCollectionVOS.size(); ij++) {
                OperateStatisticsBean.PaymentCollectionVOSBean collectionBean = paymentCollectionVOS.get(ij);
                if (!TextUtils.isEmpty(collectionBean.getMonth())) {
                    String[] split = collectionBean.getMonth().split("-");
                    String monthStr = null;
                    if (split[1].startsWith("0")) {
                        monthStr = split[1].substring(1);
                    } else {
                        monthStr = split[1];
                    }
                    if ((i + 1) == Integer.parseInt(monthStr)) {
                        passEntry.setY(collectionBean.getPaymentCollection());
                    }
                }
            }
            payList.add(passEntry);
        }
        List<OperateStatisticsBean.CostVOSBean> costVOS = data.getCostVOS();
//        for (OperateStatisticsBean.CostVOSBean bean : costVOS) {
//            LogUtils.showLog(".getMonth=" + bean.getMonth() + "...getCost=" + bean.getCost());
//        }
        for (int i = 0; i < 12; i++) {
            Entry costEntry = new Entry();
            costEntry.setX(i);
            for (int ij = 0; ij < costVOS.size(); ij++) {
                OperateStatisticsBean.CostVOSBean costBean = costVOS.get(ij);
                if (!TextUtils.isEmpty(costBean.getMonth())) {
                    String[] split = costBean.getMonth().split("-");
                    String monthStr = null;
                    if (split[1].startsWith("0")) {
                        monthStr = split[1].substring(1);
                    } else {
                        monthStr = split[1];
                    }
                    if ((i + 1) == Integer.parseInt(monthStr)) {
                        costEntry.setY(costBean.getCost());
                    }
                }
            }
            costList.add(costEntry);
        }

//        for (int i = 0; i < 12; i++) {
//            LogUtils.showLog("payList.getX=" + payList.get(i).getX() + "...getY=" + payList.get(i).getY());
//        }
//        for (int i = 0; i < 12; i++) {
//            LogUtils.showLog("costList.getX=" + costList.get(i).getX() + "...getY=" + costList.get(i).getY());
//        }
        //准备通过和不通过的数据集合
        List<List<Entry>> dataList = new ArrayList<>();
        dataList.add(payList);
        dataList.add(costList);

        //准备线的颜色集合
        List<Integer> colorList = new ArrayList<>();
        colorList.add(resources.getColor(R.color.color_pass));
        colorList.add(resources.getColor(R.color.color_not_pass));

        //准备图标提示文字
        List<String> legendList = new ArrayList<>();
        legendList.add("回款");
        legendList.add("成本");

        MonthXAxisValueFormatter monthXAxisValueFormatter = new MonthXAxisValueFormatter();
        GraphUtil.showLineChart(binding.lineChart, dataList, legendList, colorList, 12,
                monthXAxisValueFormatter, null);
    }


    /**
     * 设置数据
     *
     * @param data
     */
    private void setData(OperateOverViewBean data) {
        binding.tvSum.setText(data.getTotalSales());
        setDownAndUpText(data.getLastTotalSales(), binding.ivSumArrow, binding.tvSumPercent);
        binding.tvActual.setText(data.getTotalCollection());
        setDownAndUpText(data.getLastTotalCollection(), binding.ivActualArrow, binding.tvActualPercent);
        binding.tvTotal.setText(data.getTotalCost());
        setDownAndUpText(data.getLastTotalCost(), binding.ivTotalArrow, binding.tvTotalPercent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_content:
                selectStartDate();
                break;
            case R.id.cl_actual:
                overViewData.setOperateType(OperateType.TYPE_ACTUAL);
                overViewData.setSearchTime(calenderUtils.getDateByDate(dates.getTime()));
                OperateListActivity.start(getContext(), overViewData);
                break;
            case R.id.cl_total:
                overViewData.setSearchTime(calenderUtils.getDateByDate(dates.getTime()));
                overViewData.setOperateType(OperateType.TYPE_TOTAL);
                OperateListActivity.start(getContext(), overViewData);
                break;
        }
    }

    private void selectStartDate() {
        AlertDialogUtils.pickDearTime(getFragmentManager(), "请选择查询年份", dates.getTime(),
                Type.YEAR, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
                        dates = new Date(millSeconds);
                        binding.tvSelectEndTime.setText(calenderUtils.getYearByDate(dates.getTime()));
                        viewModel.getOperateOverViewData(calenderUtils.getDateByDate(dates.getTime()));
                        viewModel.getOperateStatistics(calenderUtils.getDateByDate(dates.getTime()));

                    }
                });
    }

    public void setDownAndUpText(String number, AppCompatImageView imageView, AppCompatTextView textView) {
        imageView.setImageResource(isDown(number) ? R.mipmap.ic_arrow_green_down : R.mipmap.ic_arrow_red_up);

        textView.setText(number);
        textView.setTextColor(getActivity().getResources().getColor(
                isDown(number) ? R.color.color_green_2fc25b : R.color.color_red_F04864));
    }


    public boolean isDown(String number) {
        if (StringUtils.isTextEmpty(number)) {
            return false;
        }

        return number.startsWith("-");
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
