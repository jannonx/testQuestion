package com.guyuan.dear.focus.qc.views.qcReportList;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
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
import com.guyuan.dear.work.qc.views.home.QcHomeActivity;
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
public class ReportListFragment extends BaseMvvmFragment<FragmentAllQcReportListBinding, QcReportListViewModel> {

    /**
     * 只显示异常报告
     */
    public static final int REPORT_TYPE_ONLY_REJECTED_REPORTS = 1;
    /**
     * 显示所有类型的报告
     */
    public static final int REPORT_TYPE_SHOW_ALL_REPORTS = 0;
    /**
     * 只显示当前用户提交的所有类型的报告
     */
    public static final int REPORT_TYPE_ONLY_MY_REPORTS = 2;


    private long dateFrom;
    private long dateTo;
    private BaseRecyclerView recyclerView;
    private int reportType;
    private BaseRecyclerViewAdapter wrapper;
    private List<GenericQcReport> list;


    /**
     * @param reportType 参考 {@link ReportListFragment#REPORT_TYPE_ONLY_REJECTED_REPORTS},
     *                   {@link ReportListFragment#REPORT_TYPE_SHOW_ALL_REPORTS},
     *                   {@link ReportListFragment#REPORT_TYPE_ONLY_MY_REPORTS}
     * @return
     */
    public static ReportListFragment getInstance(int reportType) {
        ReportListFragment fragment = new ReportListFragment();
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
        resetAndSearchQcReportsByTimePeriod(dateFrom, dateTo);
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
                selectStartTime();
            }
        });

        //当收到我的工作-质量报告中刷新我的报告清单的信号时，进行刷新操作。
        FragmentActivity activity = getActivity();
        if (activity instanceof QcHomeActivity) {
            ((QcHomeActivity) activity).getViewModel().refreshMyApplyList.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        getViewModel().clearAllMyQcReport();
                        addDisposable(getViewModel().updateMyQcReports(dateFrom, dateTo));
                    }
                }
            });
        }
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
                            resetAndSearchQcReportsByTimePeriod(dateFrom, dateTo);
                        }

                    }
                });
    }

    private void resetAndSearchQcReportsByTimePeriod(long dateFrom, long dateTo) {
        if (recyclerView != null) {
            recyclerView.setLoadMoreEnabled(true);
        }
        getViewModel().reset();
        getViewModel().setDateFrom(dateFrom);
        getViewModel().setDateTo(dateTo);
        if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
            addDisposable(getViewModel().updateAllRejectedReports(dateFrom, dateTo));
        } else if (reportType == REPORT_TYPE_SHOW_ALL_REPORTS) {
            addDisposable(getViewModel().updateAllReports(dateFrom, dateTo));
        } else if (reportType == REPORT_TYPE_ONLY_MY_REPORTS) {
            addDisposable(getViewModel().updateMyQcReports(dateFrom, dateTo));
        }
    }

    private void initRecyclerView() {
        recyclerView = getViewDataBinding().fragmentAllQcReportListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
            list = getViewModel().getRejectedReportList().getValue();
            getViewModel().getRejectedReportList().observe(getViewLifecycleOwner(), observerUpdateItem);
            getViewModel().getIsAllRejectedReportLoaded().observe(getViewLifecycleOwner(), observerEnableRefresh);
        } else if (reportType == REPORT_TYPE_SHOW_ALL_REPORTS) {
            list = getViewModel().getAllReportList().getValue();
            getViewModel().getAllReportList().observe(getViewLifecycleOwner(), observerUpdateItem);
            getViewModel().getIsAllQcReportLoaded().observe(getViewLifecycleOwner(), observerEnableRefresh);
        } else if (reportType == REPORT_TYPE_ONLY_MY_REPORTS) {
            list = getViewModel().getMyQcReports().getValue();
            getViewModel().getMyQcReports().observe(getViewLifecycleOwner(), observerUpdateItem);
            getViewModel().getIsAllMyQcReportLoaded().observe(getViewLifecycleOwner(), observerEnableRefresh);
        }
        if (list == null) {
            return;
        }
        AllQcListAdapter adapter = new AllQcListAdapter(list, getContext());
        wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12, 16));
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
                    addDisposable(getViewModel().updateAllRejectedReports(dateFrom, dateTo));
                } else if (reportType == REPORT_TYPE_SHOW_ALL_REPORTS) {
                    addDisposable(getViewModel().updateAllReports(dateFrom, dateTo));
                } else if (reportType == REPORT_TYPE_ONLY_MY_REPORTS) {
                    addDisposable(getViewModel().updateMyQcReports(dateFrom, dateTo));
                }
                recyclerView.refreshComplete(0);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GenericQcReport genericQcReport = null;
                //点击进入报告详情
                if (reportType == REPORT_TYPE_ONLY_REJECTED_REPORTS) {
                    genericQcReport = getViewModel().getRejectedReportList().getValue().get(i);
                } else if (reportType == REPORT_TYPE_SHOW_ALL_REPORTS) {
                    genericQcReport = getViewModel().getAllReportList().getValue().get(i);
                } else if (reportType == REPORT_TYPE_ONLY_MY_REPORTS) {
                    genericQcReport = getViewModel().getMyQcReports().getValue().get(i);
                }
                if (genericQcReport == null) {
                    return;
                }
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

    private Observer<List<GenericQcReport>> observerUpdateItem = new Observer<List<GenericQcReport>>() {
        @Override
        public void onChanged(List<GenericQcReport> genericQcReports) {
            if (list != null) {
                list.clear();
                list.addAll(genericQcReports);
                wrapper.notifyDataSetChanged();
            }
        }
    };
    private Observer<Boolean> observerEnableRefresh = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            recyclerView.setLoadMoreEnabled(!aBoolean);
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_all_qc_report_list;
    }
}
