package com.guyuan.dear.focus.qc.views.abNormalList;

import android.view.View;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.repo.QcListRepo;
import com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

import static com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity.SEARCH_TYPE_ALL;

/**
 * Author: 廖华凯
 * Date: 2020/12/6
 * Project: Dear
 * Description:
 */
public class AbnormalQcListViewModel extends BaseDearViewModel {
    /**
     * 异常QC报告
     */
    private MutableLiveData<List<GenericQcReport>> rejectedReportList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllRejectedReportLoaded = new MutableLiveData<>(false);
    private int rejectedReportPageIndex = 1;
    public MutableLiveData<Boolean> shouldNotifyDataSetChange = new MutableLiveData<>(false);



    /**
     * 点击事件：设置时间段
     */
    private MutableLiveData<View.OnClickListener> onClickSelectTimePeriod = new MutableLiveData<>();
    /**
     * 起始时间
     */
    private MutableLiveData<Long> dateFrom = new MutableLiveData<>(0L);
    /**
     * 截至时间
     */
    private MutableLiveData<Long> dateTo = new MutableLiveData<>(0L);
    private QcListRepo repo = new QcListRepo();
    private static final int PAGE_SIZE = 50;
    /**
     * 搜索qc报告
     *
     * @param searchType {@link QcSearchListActivity#SEARCH_TYPE_ALL}
     * {@link QcSearchListActivity#SEARCH_TYPE_ALL_PASS_REPORTS}
     * {@link QcSearchListActivity#SEARCH_TYPE_ALL_REJECTED_REPORTS}
     * {@link QcSearchListActivity#SEARCH_TYPE_ALL_MY_REPORTS}
     */
    public MutableLiveData<Integer> searchType = new MutableLiveData<>(SEARCH_TYPE_ALL);

    public MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);


    public MutableLiveData<Boolean> getIsAllRejectedReportLoaded() {
        return isAllRejectedReportLoaded;
    }


    public MutableLiveData<View.OnClickListener> getOnClickSelectTimePeriod() {
        return onClickSelectTimePeriod;
    }

    public MutableLiveData<List<GenericQcReport>> getRejectedReportList() {
        return rejectedReportList;
    }


    public void setOnClickSelectTimePeriod(View.OnClickListener onClickSelectTimePeriod) {
        this.onClickSelectTimePeriod.postValue(onClickSelectTimePeriod);
    }

    public MutableLiveData<Long> getDateFrom() {
        return dateFrom;
    }

    public MutableLiveData<Long> getDateTo() {
        return dateTo;
    }


    public void setDateFrom(long dateFrom) {
        this.dateFrom.postValue(dateFrom);
    }

    public void setDateTo(long dateTo) {
        this.dateTo.postValue(dateTo);
    }


    public void reset() {
        rejectedReportList.getValue().clear();
        isAllRejectedReportLoaded.postValue(false);
        rejectedReportPageIndex = 1;
        shouldShowNoData.postValue(true);
        shouldNotifyDataSetChange.postValue(false);
    }


    public Disposable updateAllRejectedReports(long dateFrom, long dateTo) {
        return repo.getAllRejectedQcList(dateFrom, dateTo, rejectedReportPageIndex++, PAGE_SIZE, new BaseNetCallback<List<GenericQcReport>>() {
            @Override
            protected void handleResult(List<GenericQcReport> result) {
                if (result.isEmpty()) {
                    isAllRejectedReportLoaded.postValue(true);
                } else {
                    rejectedReportList.getValue().addAll(result);
                    shouldNotifyDataSetChange.postValue(true);
                    shouldShowNoData.postValue(false);
                }
            }
        });
    }


}
