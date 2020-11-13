package com.guyuan.dear.focus.qc.views.qcReportList;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.repo.QcListRepo;

import java.util.ArrayList;
import java.util.List;

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
    private MutableLiveData<List<BaseProductQcReport>> productReports = new MutableLiveData<>(new ArrayList<>());
    /**
     * 原材料QC报告
     */
    private MutableLiveData<List<BaseMaterialQcReport>> materialReports = new MutableLiveData<>(new ArrayList<>());
    /**
     * 异常QC报告
     */
    private MutableLiveData<List<GenericQcReport>> rejectedReportList = new MutableLiveData<>(new ArrayList<>());
    /**
     * 所有QC报告
     */
    private MutableLiveData<List<GenericQcReport>> allReportList = new MutableLiveData<>(new ArrayList<>());

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
    private QcListRepo qcListRepo = new QcListRepo();

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

    public void setDateFrom(long dateFrom) {
        this.dateFrom.postValue(dateFrom);
    }

    public void setDateTo(long dateTo) {
        this.dateTo.postValue(dateTo);
    }

    public MutableLiveData<List<BaseProductQcReport>> getProductReports() {
        return productReports;
    }

    public MutableLiveData<List<BaseMaterialQcReport>> getMaterialReports() {
        return materialReports;
    }

    /**
     * 获取规定时间段的产品的正常报告清单
     * @param dateFrom 开始日期
     * @param dateTo 结束日期
     * @param pageIndex 页码，从1开始
     * @param pageSize 每页的容量
     */
    public void upDateProductPassList(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.productReports.getValue().addAll(qcListRepo.getProductPassList(dateFrom,dateTo,pageIndex,pageSize));
        this.productReports.postValue(this.productReports.getValue());
    }

    /**
     * 获取规定时间段的产品的异常报告清单
     * @param dateFrom 开始日期
     * @param dateTo 结束日期
     * @param pageIndex 页码，从1开始
     * @param pageSize 每页的容量
     */
    public void upDateProductRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.productReports.getValue().addAll(qcListRepo.getProductRejectList(dateFrom,dateTo,pageIndex,pageSize));
        this.productReports.postValue(this.productReports.getValue());
    }

    public void upDateMaterialPassList(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.materialReports.getValue().addAll(qcListRepo.getMaterialPassList(dateFrom,dateTo,pageIndex,pageSize));
        this.materialReports.postValue(this.materialReports.getValue());
    }

    public void upDateMaterialRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.materialReports.getValue().addAll(qcListRepo.getMaterialRejectList(dateFrom,dateTo,pageIndex,pageSize));
        this.materialReports.postValue(this.materialReports.getValue());
    }

    public void updateAllRejectedReports(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.rejectedReportList.getValue().addAll(qcListRepo.getAllRejectedQcList(dateFrom,dateTo,pageIndex,pageSize));
        this.rejectedReportList.postValue(this.rejectedReportList.getValue());
    }

    public void updateAllReports(long dateFrom, long dateTo, int pageIndex, int pageSize){
        this.allReportList.getValue().addAll(qcListRepo.getAllQcList(dateFrom,dateTo,pageIndex,pageSize));
        this.allReportList.postValue(this.allReportList.getValue());
    }
}
