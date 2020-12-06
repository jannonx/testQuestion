package com.guyuan.dear.focus.qc.views.abNormalList;

import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentAbnormalQcReportListBinding;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.views.materialQcDetail.MaterialQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private BaseRecyclerViewAdapter wrapper;
    private List<GenericQcReport> list;

    public static AbnormalQcListFragment getInstance(){
        return new AbnormalQcListFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_abnormal_qc_report_list_vm;
    }

    @Override
    protected void initData() {
        dateTo = System.currentTimeMillis();
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
        getViewModel().getRejectedReportList().observe(getViewLifecycleOwner(), observerUpdateItem);
        getViewModel().getIsAllRejectedReportLoaded().observe(getViewLifecycleOwner(), observerEnableRefresh);
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
                addDisposable(getViewModel().updateAllRejectedReports(dateFrom, dateTo));
                recyclerView.refreshComplete(0);
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
        return R.layout.fragment_abnormal__qc_report_list;
    }
}
