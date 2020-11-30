package com.guyuan.dear.analyse.operate.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.databinding.FragmentAnalyseOperateBinding;
import com.guyuan.dear.databinding.FragmentFocusProjectSiteBinding;
import com.guyuan.dear.focus.projectsite.activity.ProjectReportClassifyActivity;
import com.guyuan.dear.focus.projectsite.bean.ProjectOverViewBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.LogUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Date;

/**
 * @description: 我的关注--工程现场
 * @author: 许建宁
 * @since: 2020/11/17 11:54
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateFragment extends BaseDataBindingFragment<FragmentAnalyseOperateBinding, OperateViewModel> {

    public static final String TAG = OperateFragment.class.getSimpleName();

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

        viewModel.getOperateOverViewData("2020-10-10");
        viewModel.getOverViewEvent().observe(getActivity(), new Observer<OperateOverViewBean>() {
            @Override

            public void onChanged(OperateOverViewBean data) {
                setData(data);
            }
        });
    }

    private void selectStartDate() {
//        AlertDialogUtils.pickTime(getFragmentManager(), "请选择起始日期", dates[0].getTime(),
//                Type.YEAR_MONTH_DAY, new OnDateSetListener() {
//                    @Override
//                    public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
//                        if (dates[1].getTime() <= new Date(millSeconds).getTime()) {
//                            showToastTip("起始日期不能晚于起始日期！");
//                        } else {
//                            dates[0] = new Date(millSeconds);
//                            String yearMonthDay = calenderUtils.getDateByDate(millSeconds);
//                            dataArr[0] = yearMonthDay;
//                            mTvSelectStartTime.setText(yearMonthDay);
//                            viewModel.getProduceOverView(getRequestBody());
//                        }
//                    }
//                });
    }
    /**
     * 设置数据
     *
     * @param data
     */
    private void setData(OperateOverViewBean data) {
        binding.tvSum.setText(data.getTotalSales());
        binding.tvSumPercent.setText(data.getLastTotalSales());
        binding.tvActual.setText(data.getTotalCollection());
        binding.tvActualPercent.setText(data.getLastTotalCollection());
        binding.tvTotal.setText(data.getTotalCost());
        binding.tvTotalPercent.setText(data.getLastTotalCost());
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

}
