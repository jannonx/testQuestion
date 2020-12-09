package com.guyuan.dear.focus.qc.views.abNormalList;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator2P0;
import com.guyuan.dear.databinding.FragmentAbnormalQcReportListBinding;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.views.materialQcDetail.MaterialQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Calendar;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * Author: 廖华凯
 * Date: 2020/12/6
 * Project: Dear
 * Description:
 */
public class AbnormalQcListFragment extends BaseMvvmFragment<FragmentAbnormalQcReportListBinding, AbnormalQcListViewModel> {

    private long dateFrom;
    private long dateTo;
    private BaseRecyclerView recyclerView;
    private List<GenericQcReport> list;
    private AllQcListAdapter adapter;

    public static AbnormalQcListFragment getInstance() {
        return new AbnormalQcListFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_abnormal_qc_report_list_vm;
    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dateTo = calendar.getTimeInMillis();
        dateFrom = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(dateTo, 3);
        getViewModel().searchType.postValue(QcSearchListActivity.SEARCH_TYPE_ALL_REJECTED_REPORTS);
        resetAndSearchQcReportsByTimePeriod(dateFrom, dateTo);

    }

    private void resetAndSearchQcReportsByTimePeriod(long dateFrom, long dateTo) {
        if (recyclerView != null) {
            recyclerView.setLoadMoreEnabled(true);
        }
        getViewModel().reset();
        getViewModel().setDateFrom(dateFrom);
        getViewModel().setDateTo(dateTo);
        addDisposable(getViewModel().updateAllRejectedReports(dateFrom, dateTo));
    }

    @Override
    protected void initViews() {
        recyclerView = getViewDataBinding().fragmentAbnormalQcReportListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        list = getViewModel().getRejectedReportList().getValue();
        if (list == null) {
            return;
        }
        adapter = new AllQcListAdapter(list, getContext());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator2P0(12, 0, 12, 12, 16));
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                addDisposable(getViewModel().updateAllRejectedReports(dateFrom, dateTo));
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GenericQcReport genericQcReport = null;
                //点击进入报告详情
                genericQcReport = getViewModel().getRejectedReportList().getValue().get(i);
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

    @Override
    protected void initListeners() {
        getViewModel().setOnClickSelectTimePeriod(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });

        getViewModel().getIsAllRejectedReportLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    recyclerView.refreshComplete(0);
                    recyclerView.setLoadMoreEnabled(false);
                }
            }
        });

        getViewModel().shouldNotifyDataSetChange.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    recyclerView.refreshComplete(0);
                    LogUtils.showLog("size of list is "+list.size());
                    adapter.notifyDataSetChanged();
                }
            }
        });

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
                        if (dateFrom > millseconds) {
                            showToastTip("结束时间不能小于开始时间。");
                        } else {
                            dateTo = millseconds;
                            resetAndSearchQcReportsByTimePeriod(dateFrom, dateTo);
                        }

                    }
                });
    }



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_abnormal_qc_report_list;
    }
}
