package com.guyuan.dear.focus.qc.views.qcReportList;

import android.view.View;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
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
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 11:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcReportListViewModel extends BaseViewModel {
    /**
     * 成品QC报告
     */
    private MutableLiveData<List<GenericQcReport>> productReports = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllProductReportLoaded = new MutableLiveData<>(false);
    private int productReportPageIndex = 1;
    /**
     * 原材料QC报告
     */
    private MutableLiveData<List<GenericQcReport>> materialReports = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllMaterialReportLoaded = new MutableLiveData<>(false);
    private int materialReportPageIndex = 1;
    /**
     * 异常QC报告
     */
    private MutableLiveData<List<GenericQcReport>> rejectedReportList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllRejectedReportLoaded = new MutableLiveData<>(false);
    private int rejectedReportPageIndex = 1;
    /**
     * 当前用户提交的报告
     */
    private MutableLiveData<List<GenericQcReport>> myQcReports = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllMyQcReportLoaded = new MutableLiveData<>(false);
    private int myQcReportPageIndex = 1;
    /**
     * 所有QC报告
     */
    private MutableLiveData<List<GenericQcReport>> allReportList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllQcReportLoaded = new MutableLiveData<>(false);
    private int allQcReportPageIndex = 1;

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

    public MutableLiveData<Boolean> getIsAllProductReportLoaded() {
        return isAllProductReportLoaded;
    }

    public MutableLiveData<Boolean> getIsAllMaterialReportLoaded() {
        return isAllMaterialReportLoaded;
    }

    public MutableLiveData<Boolean> getIsAllRejectedReportLoaded() {
        return isAllRejectedReportLoaded;
    }

    public MutableLiveData<Boolean> getIsAllMyQcReportLoaded() {
        return isAllMyQcReportLoaded;
    }

    public MutableLiveData<Boolean> getIsAllQcReportLoaded() {
        return isAllQcReportLoaded;
    }

    public MutableLiveData<View.OnClickListener> getOnClickSelectTimePeriod() {
        return onClickSelectTimePeriod;
    }

    public MutableLiveData<List<GenericQcReport>> getRejectedReportList() {
        return rejectedReportList;
    }

    public MutableLiveData<List<GenericQcReport>> getAllReportList() {
        return allReportList;
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

    public MutableLiveData<List<GenericQcReport>> getMyQcReports() {
        return myQcReports;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom.postValue(dateFrom);
    }

    public void setDateTo(long dateTo) {
        this.dateTo.postValue(dateTo);
    }

    public MutableLiveData<List<GenericQcReport>> getProductReports() {
        return productReports;
    }

    public MutableLiveData<List<GenericQcReport>> getMaterialReports() {
        return materialReports;
    }

    public void reset() {
        materialReports.postValue(new ArrayList<>());
        isAllMaterialReportLoaded.postValue(false);
        materialReportPageIndex = 1;

        myQcReports.postValue(new ArrayList<>());
        isAllMyQcReportLoaded.postValue(false);
        myQcReportPageIndex = 1;

        productReports.postValue(new ArrayList<>());
        isAllProductReportLoaded.postValue(false);
        productReportPageIndex = 1;

        allReportList.postValue(new ArrayList<>());
        isAllQcReportLoaded.postValue(false);
        allQcReportPageIndex = 1;

        rejectedReportList.postValue(new ArrayList<>());
        isAllRejectedReportLoaded.postValue(false);
        rejectedReportPageIndex = 1;

        shouldShowNoData.postValue(true);
    }


    /**
     * 获取规定时间段的产品的正常报告清单
     *
     * @param dateFrom 开始日期
     * @param dateTo   结束日期
     */
    public Disposable upDateProductPassList(long dateFrom, long dateTo) {
        return repo.getProductPassList(dateFrom, dateTo, productReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllProductReportLoaded.postValue(true);
                } else {
                    productReports.getValue().addAll(result);
                    productReports.postValue(productReports.getValue());
                    shouldShowNoData.postValue(false);
                }


            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

    /**
     * 获取规定时间段的产品的异常报告清单
     *
     * @param dateFrom 开始日期
     * @param dateTo   结束日期
     */
    public Disposable upDateProductRejectList(long dateFrom, long dateTo) {
        return repo.getProductRejectList(dateFrom, dateTo, productReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);

            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllProductReportLoaded.postValue(true);
                } else {
                    productReports.getValue().addAll(result);
                    productReports.postValue(productReports.getValue());
                    shouldShowNoData.postValue(false);
                }
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

            }
        });
    }

    public Disposable upDateMaterialPassList(long dateFrom, long dateTo) {
        return repo.getMaterialPassList(dateFrom, dateTo, materialReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllMaterialReportLoaded.postValue(true);
                } else {
                    materialReports.getValue().addAll(result);
                    materialReports.postValue(materialReports.getValue());
                    shouldShowNoData.postValue(false);
                }

            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

    public Disposable upDateMaterialRejectList(long dateFrom, long dateTo) {
        return repo.getMaterialRejectList(dateFrom, dateTo, materialReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllMaterialReportLoaded.postValue(true);
                } else {
                    materialReports.getValue().addAll(result);
                    materialReports.postValue(materialReports.getValue());
                    shouldShowNoData.postValue(false);
                }

            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

    public Disposable updateAllRejectedReports(long dateFrom, long dateTo) {
        return repo.getAllRejectedQcList(dateFrom, dateTo, rejectedReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                if (result.isEmpty()) {
                    isAllRejectedReportLoaded.postValue(true);
                } else {
                    rejectedReportList.getValue().addAll(result);
                    rejectedReportList.postValue(rejectedReportList.getValue());
                    shouldShowNoData.postValue(false);
                }
                isShowLoading.postValue(false);
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

    public Disposable updateAllReports(long dateFrom, long dateTo) {
        return repo.getAllQcList(dateFrom, dateTo, allQcReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllQcReportLoaded.postValue(true);
                } else {
                    allReportList.getValue().addAll(result);
                    allReportList.postValue(allReportList.getValue());
                    shouldShowNoData.postValue(false);
                }
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

    public void clearAllMyQcReport() {
        myQcReports.postValue(new ArrayList<>());
        isAllMyQcReportLoaded.postValue(false);
        myQcReportPageIndex = 1;
        shouldShowNoData.postValue(true);
    }

    public Disposable updateMyQcReports(long dateFrom, long dateTo) {
        return repo.getMyQcReports(dateFrom, dateTo, myQcReportPageIndex++, PAGE_SIZE, new DearNetHelper.NetCallback<List<GenericQcReport>>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(List<GenericQcReport> result) {
                isShowLoading.postValue(false);
                if (result.isEmpty()) {
                    isAllMyQcReportLoaded.postValue(true);
                } else {
                    myQcReports.getValue().addAll(result);
                    myQcReports.postValue(myQcReports.getValue());
                    shouldShowNoData.postValue(false);
                }
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }

}
