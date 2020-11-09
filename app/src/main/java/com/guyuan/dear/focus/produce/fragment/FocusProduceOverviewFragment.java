package com.guyuan.dear.focus.produce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProduceOverviewBinding;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.focus.produce.bean.ProduceOverViewBean;
import com.guyuan.dear.focus.produce.bean.ProduceRequestBody;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.ui.FocusProduceActivity;
import com.guyuan.dear.focus.produce.ui.FocusProduceClassifyActivity;
import com.guyuan.dear.focus.produce.util.ChartViewUtils;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Date;

import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceOverviewFragment extends BaseDataBindingFragment<FragmentFocusProduceOverviewBinding, FocusProduceViewModel>
        implements View.OnClickListener {
    private CalenderUtils calenderUtils;
    private RelativeLayout mRlDateSelect;
    private AppCompatEditText etSearch;
    private AppCompatCheckedTextView tvSearchBtn;
    private TextView mTvSelectStartTime, mTvSelectEndTime;
    private Date[] dates = new Date[2];
    private String[] dataArr = new String[2];
    public static final String TAG = FocusProduceOverviewFragment.class.getSimpleName();

    public static FocusProduceOverviewFragment newInstance() {
        Bundle args = new Bundle();
        FocusProduceOverviewFragment fragment = new FocusProduceOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_overview;
    }

    @Override
    protected void initialization() {
        initView();
        initDate();
        getOverViewData();
    }


    /**
     * 概览
     */
    private void getOverViewData() {
        viewModel.getProduceOverView(getRequestBody());
        viewModel.getOverViewEvent().observe(getActivity(), new Observer<ProduceOverViewBean>() {
            @Override
            public void onChanged(ProduceOverViewBean data) {
                LogUtils.showLog("data" + data.getCompletionRate());
                setOverViewData(data);
            }
        });
    }


    private void initView() {
        View root = binding.getRoot();
        etSearch = root.findViewById(R.id.et_search);
        mRlDateSelect = root.findViewById(R.id.rl_date_select);
        mTvSelectStartTime = root.findViewById(R.id.tv_select_start_time);
        mTvSelectEndTime = root.findViewById(R.id.tv_select_end_time);
        tvSearchBtn = root.findViewById(R.id.tv_search_btn);
        etSearch.setHint("输入产品名称、产品代号");
        mTvSelectStartTime.setOnClickListener(this);
        mTvSelectEndTime.setOnClickListener(this);
        tvSearchBtn.setOnClickListener(this);
        binding.clProduceWait.setOnClickListener(this);
        binding.clProduceComplete.setOnClickListener(this);
        binding.clProduceIng.setOnClickListener(this);
        binding.clProduceException.setOnClickListener(this);
        binding.clProduceDelay.setOnClickListener(this);


        calenderUtils = CalenderUtils.getInstance();
        mRlDateSelect.setVisibility(View.VISIBLE);
    }

    private void initDate() {
        dates[0] = calenderUtils.getSettingDate(CalenderUtils.getPassOneYear(), 0, 0, 0);
        dates[1] = calenderUtils.getSettingDate(new Date(), 23, 59, 59);

        dataArr[0] = calenderUtils.toSmartFactoryDateFormatByDay(dates[0].getTime());
        dataArr[1] = calenderUtils.toSmartFactoryDateFormatByDay(dates[1].getTime());
        mTvSelectStartTime.setText(dataArr[0]);
        mTvSelectEndTime.setText(dataArr[1]);
    }

    private void setOverViewData(ProduceOverViewBean data) {

        binding.tvPlanTotal.setText(String.valueOf(data.getTotalNum()));
        binding.tvCompleteRate.setText(data.getCompletionRate());
        binding.tvProduceWait.setText(String.valueOf(data.getPrepareNum()));
        binding.tvProduceComplete.setText(String.valueOf(data.getCompleteNum()));
        binding.tvProduceIng.setText(String.valueOf(data.getProcessingNum()));
        binding.tvProduceException.setText(String.valueOf(data.getAbnormalNum()));
        binding.tvProduceDelay.setText(String.valueOf(data.getTardinessNum()));

        ChartViewUtils.setFocusChartView(binding.chartView, data);
    }

    /**
     * 请求参数配置
     *
     * @return
     */
    private RequestBody getRequestBody() {
        ProduceRequestBody body = new ProduceRequestBody();
        body.setStartTime(calenderUtils.toSmartFactoryDateStringFormat(dates[0].getTime()));
        body.setEndTime(calenderUtils.toSmartFactoryDateStringFormat(dates[1].getTime()));
        body.setName(etSearch.getText().toString());
        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_produce_wait:
//                FocusProduceClassifyActivity.start(
//                        getContext(), ProductStatusType.TYPE_PRODUCE_WAIT);
                ProduceApplyDialog.show(getActivity(), null);
                break;
            case R.id.cl_produce_complete:
                FocusProduceClassifyActivity.start(
                        getContext(), ProductStatusType.TYPE_PRODUCE_COMPLETE);

                break;
            case R.id.cl_produce_ing:

                FocusProduceClassifyActivity.start(
                        getContext(), ProductStatusType.TYPE_PRODUCE_ING);
                break;
            case R.id.cl_produce_exception:
                FocusProduceClassifyActivity.start(
                        getContext(), ProductStatusType.TYPE_PRODUCE_EXCEPTION);

                break;
            case R.id.cl_produce_delay:
                FocusProduceClassifyActivity.start(
                        getContext(), ProductStatusType.TYPE_PRODUCE_DELAY);

                break;
            case R.id.tv_select_start_time:
                selectStartDate();
                break;
            case R.id.tv_select_end_time:
                selectEndDate();
                break;
            case R.id.tv_search_btn:
                getOverViewData();
                break;
        }
    }


    private void selectStartDate() {
        AlertDialogUtils.pickTime(getFragmentManager(), "请选择起始日期", dates[0].getTime(),
                Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
                        if (dates[1].getTime() <= new Date(millSeconds).getTime()) {
                            showToastTip("起始日期不能晚于起始日期！");
                        } else {
                            dates[0] = new Date(millSeconds);
                            String yearMonthDay = calenderUtils.getDateByDate(millSeconds);
                            dataArr[0] = yearMonthDay;
                            mTvSelectStartTime.setText(yearMonthDay);
                            getOverViewData();
                        }
                    }
                });
    }

    private void selectEndDate() {
        AlertDialogUtils.pickTime(getFragmentManager(), "请选择截止日期", dates[1].getTime(),
                Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {

                        if (new Date(millSeconds + 86399000).getTime() <= dates[0].getTime()) {
                            showToastTip("截至日期不能早于起始日期！");
                        } else {
                            dates[1] = new Date(millSeconds + 86399000);
                            String yearMonthDay = calenderUtils.getDateByDate(millSeconds + 86399000);
                            dataArr[1] = yearMonthDay;
                            mTvSelectEndTime.setText(yearMonthDay);
                            getOverViewData();
                        }
                    }
                });
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
