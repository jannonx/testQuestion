package com.guyuan.dear.focus.qc.views.qcSum;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.qc.beans.QcSummaryBean;
import com.guyuan.dear.focus.qc.repo.QcSummaryRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/11 15:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSummaryViewModel extends BaseViewModel {
    /**
     * 点击事件：选择时间段更新QC概况
     */
    private MutableLiveData<View.OnClickListener> onClickSetTimePeriod = new MutableLiveData<>();
    /**
     * 点击事件：点击查看产品已经pass的QC报告清单
     */
    private MutableLiveData<View.OnClickListener> onClickShowProductPassReport = new MutableLiveData<>();
    /**
     * 点击事件：点击查看产品reject了的QC报告清单
     */
    private MutableLiveData<View.OnClickListener> onClickShowProductRejectReport = new MutableLiveData<>();
    /**
     * 点击事件：点击查看原材料pass了的QC报告清单
     */
    private MutableLiveData<View.OnClickListener> onClickShowMaterialPassReport = new MutableLiveData<>();
    /**
     * 点击事件：点击查看原材料reject了的QC报告清单
     */
    private MutableLiveData<View.OnClickListener> onClickShowMaterialRejectReport = new MutableLiveData<>();
    /**
     * 基础数据源
     */
    private MutableLiveData<QcSummaryBean> qcSummaryBean = new MutableLiveData<>();
    /**
     * 数据源实现类
     */
    private QcSummaryRepo repo = new QcSummaryRepo();


    public MutableLiveData<View.OnClickListener> getOnClickSetTimePeriod() {
        return onClickSetTimePeriod;
    }

    public void setOnClickSetTimePeriod(View.OnClickListener onClickSetTimePeriod) {
        this.onClickSetTimePeriod.postValue(onClickSetTimePeriod);
    }

    public MutableLiveData<QcSummaryBean> getQcSummaryBean() {
        return qcSummaryBean;
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowProductPassReport() {
        return onClickShowProductPassReport;
    }

    public void setOnClickShowProductPassReport(View.OnClickListener onClickShowProductPassReport) {
        this.onClickShowProductPassReport.postValue(onClickShowProductPassReport);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowProductRejectReport() {
        return onClickShowProductRejectReport;
    }

    public void setOnClickShowProductRejectReport(View.OnClickListener onClickShowProductRejectReport) {
        this.onClickShowProductRejectReport.postValue(onClickShowProductRejectReport);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowMaterialPassReport() {
        return onClickShowMaterialPassReport;
    }

    public void setOnClickShowMaterialPassReport(View.OnClickListener onClickShowMaterialPassReport) {
        this.onClickShowMaterialPassReport.postValue(onClickShowMaterialPassReport);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowMaterialRejectReport() {
        return onClickShowMaterialRejectReport;
    }

    public void setOnClickShowMaterialRejectReport(View.OnClickListener onClickShowMaterialRejectReport) {
        this.onClickShowMaterialRejectReport.postValue(onClickShowMaterialRejectReport);
    }

    /**
     * 根据起始时间和终止时间获取最新的QC概况
     *
     * @param from 起始时间
     * @param to   终止时间
     */
    public Disposable updateQcSummaryByTimePeriod(long from, long to) {
        return repo.getQcSummaryByDate(from, to, new DearNetHelper.NetCallback<QcSummaryBean>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);

            }

            @Override
            public void onGetResult(QcSummaryBean result) {
                isShowLoading.postValue(false);
                result.setStartPeriod(from);
                result.setEndPeriod(to);
                qcSummaryBean.postValue(result);
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
            }
        });
    }


}
