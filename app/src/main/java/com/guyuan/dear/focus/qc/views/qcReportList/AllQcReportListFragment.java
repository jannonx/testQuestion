package com.guyuan.dear.focus.qc.views.qcReportList;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentAllQcReportListBinding;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.views.materialQcDetail.MaterialQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 16:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllQcReportListFragment extends BaseMvvmFragment<FragmentAllQcReportListBinding, QcReportListViewModel> {

    /**
     * 只显示异常报告
     */
    public static final int REPORT_TYPE_ONLY_REJECTED_REPORTS = 1;
    /**
     * 显示所有类型的报告
     */
    public static final int REPORT_TYPE_SHOW_ALL_REPORTS = 0;


    private int reportType = 0;
    private int pageIndex = 1;
    private int pageSize = 10;
    private long dateFrom;
    private long dateTo;
    private List<GenericQcReport> list;

    /**
     * @param reportType 参考 {@link AllQcReportListFragment#REPORT_TYPE_ONLY_REJECTED_REPORTS},
     *                   {@link AllQcReportListFragment#REPORT_TYPE_SHOW_ALL_REPORTS}
     * @return
     */
    public static AllQcReportListFragment getInstance(int reportType) {
        AllQcReportListFragment fragment = new AllQcReportListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_REPORT_TYPE, reportType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_all_qc_report_list_vm;
    }

    @Override
    protected void initData() {
        dateTo = System.currentTimeMillis();
        dateFrom = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(dateTo, 3);
        Bundle arguments = getArguments();
        reportType = arguments.getInt(ConstantValue.KEY_REPORT_TYPE, REPORT_TYPE_SHOW_ALL_REPORTS);
        resetQcReportsByTimePeriod(dateFrom, dateTo);
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    @Override
    protected void initListeners() {
        getViewModel().setOnClickSelectTimePeriod(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginToSelectDate();
            }
        });
    }

    private void beginToSelectDate() {
        selectStartTime();
    }

    private void selectStartTime() {
        AlertDialogUtils.pickTime(getParentFragmentManager(), "请选择起始时间", 0,
                System.currentTimeMillis(), dateFrom, Type.YEAR_MONTH, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        dateFrom = millseconds;
                        selectEndTime();

                    }
                });
    }

    private void selectEndTime() {
        AlertDialogUtils.pickTime(getParentFragmentManager(), "请选择结束时间", dateFrom,
                System.currentTimeMillis(), dateTo, Type.YEAR_MONTH, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        if (dateFrom > dateTo) {
                            showToastTip("结束时间不能小于开始时间。");
                        } else {
                            dateTo = millseconds;
                            resetQcReportsByTimePeriod(dateFrom, dateTo);
                        }

                    }
                });
    }

    private void resetQcReportsByTimePeriod(long dateFrom, long dateTo) {
        pageIndex = 1;
        if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
            getViewModel().getRejectedReportList().getValue().clear();
            getViewModel().updateAllRejectedReports(dateFrom, dateTo, pageIndex++, pageSize);
        } else if (reportType == REPORT_TYPE_SHOW_ALL_REPORTS) {
            getViewModel().getAllReportList().getValue().clear();
            getViewModel().updateAllReports(dateFrom, dateTo, pageIndex++, pageSize);
        }
        getViewModel().setDateFrom(dateFrom);
        getViewModel().setDateTo(dateTo);
    }

    private void initRecyclerView() {
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentAllQcReportListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
            list = getViewModel().getRejectedReportList().getValue();
        } else {
            list = getViewModel().getAllReportList().getValue();
        }
        AllQcListAdapter adapter = new AllQcListAdapter(list, getContext());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12,16));
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //假数据最多加载3页
                if (pageIndex > 3) {
                    showToastTip("已经全部加载了。");
                    recyclerView.setLoadMoreEnabled(false);
                    return;
                }
                if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
                    getViewModel().updateAllRejectedReports(dateFrom, dateTo, pageIndex++, pageSize);
                } else {
                    getViewModel().updateAllReports(dateFrom, dateTo, pageIndex++, pageSize);
                }
                recyclerView.refreshComplete(pageSize);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                //点击进入报告详情
                GenericQcReport genericQcReport = list.get(i);
                int type = genericQcReport.getType();
                if (type == GenericQcReport.REPORT_TYPE_PRODUCT) {
                    ProductQcReportDetailActivity.start(getContext(), "产品质量报告",
                            new Gson().fromJson(genericQcReport.getJson(), BaseProductQcReport.class));
                } else if (type == GenericQcReport.REPORT_TYPE_MATERIAL) {
                    MaterialQcReportDetailActivity.start(getContext(), "原材料质量报告",
                            new Gson().fromJson(genericQcReport.getJson(), BaseMaterialQcReport.class));
                }
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_all_qc_report_list;
    }
}
