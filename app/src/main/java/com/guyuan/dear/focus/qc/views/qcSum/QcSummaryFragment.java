package com.guyuan.dear.focus.qc.views.qcSum;

import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentQcSummaryBinding;
import com.guyuan.dear.focus.qc.views.qcReportList.QcSumTypeListActivity;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/11 15:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSummaryFragment extends BaseMvvmFragment<FragmentQcSummaryBinding, QcSummaryViewModel> {

    private long dateTo;
    private long dateFrom;

    public static QcSummaryFragment getInstance() {
        return new QcSummaryFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_qc_summary_vm;
    }

    @Override
    protected void initData() {
        dateTo = System.currentTimeMillis();
        dateFrom = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(dateTo, 3);
        addDisposable(getViewModel().updateQcSummaryByTimePeriod(dateFrom, dateTo));

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().setOnClickSetTimePeriod(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginToSelectDate();
            }
        });
        getViewModel().setOnClickShowProductPassReport(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QcSumTypeListActivity.start(getActivity(),
                        "产品合格列表",
                        QcSumTypeListActivity.REPORT_TYPE_PRODUCT_PASS_REPORT,
                        getViewModel().getQcSummaryBean().getValue().getStartPeriod(),
                        getViewModel().getQcSummaryBean().getValue().getEndPeriod());

            }
        });
        getViewModel().setOnClickShowProductRejectReport(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QcSumTypeListActivity.start(getActivity(),
                        "产品异常列表",
                        QcSumTypeListActivity.REPORT_TYPE_PRODUCT_REJECT_REPORT,
                        getViewModel().getQcSummaryBean().getValue().getStartPeriod(),
                        getViewModel().getQcSummaryBean().getValue().getEndPeriod());

            }
        });
        getViewModel().setOnClickShowMaterialPassReport(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QcSumTypeListActivity.start(getActivity(),
                        "原材料合格列表",
                        QcSumTypeListActivity.REPORT_TYPE_MATERIAL_PASS_REPORT,
                        getViewModel().getQcSummaryBean().getValue().getStartPeriod(),
                        getViewModel().getQcSummaryBean().getValue().getEndPeriod());

            }
        });
        getViewModel().setOnClickShowMaterialRejectReport(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QcSumTypeListActivity.start(getActivity(),
                        "原材料异常列表",
                        QcSumTypeListActivity.REPORT_TYPE_MATERIAL_REJECT_REPORT,
                        getViewModel().getQcSummaryBean().getValue().getStartPeriod(),
                        getViewModel().getQcSummaryBean().getValue().getEndPeriod());

            }
        });

    }

    private void beginToSelectDate() {
        selectStartTime();
    }

    private void selectStartTime() {
        AlertDialogUtils.pickTime(getParentFragmentManager(), "请选择起始时间", 0,
                System.currentTimeMillis(), dateFrom, Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        dateFrom = millseconds;
                        selectEndTime();

                    }
                });
    }

    private void selectEndTime() {
        AlertDialogUtils.pickTime(getParentFragmentManager(), "请选择结束时间", dateFrom,
                System.currentTimeMillis(), dateTo, Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        if (dateFrom > dateTo) {
                            showToastTip("结束时间不能小于开始时间。");
                        } else {
                            dateTo = millseconds;
                            getViewModel().updateQcSummaryByTimePeriod(dateFrom, dateTo);
                        }

                    }
                });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_qc_summary;
    }
}
