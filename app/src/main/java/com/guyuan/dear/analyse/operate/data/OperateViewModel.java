package com.guyuan.dear.analyse.operate.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.bean.OperateStatisticsBean;
import com.guyuan.dear.base.api.RxJavaHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateViewModel extends BaseViewModel {
    private OperateRepository repository;

    @ViewModelInject
    public OperateViewModel(OperateRepository operateRepository) {
        this.repository = operateRepository;
    }


    private MutableLiveData<OperateOverViewBean> overViewEvent = new MutableLiveData<>();
    private MutableLiveData<OperateStatisticsBean> statisticsEvent = new MutableLiveData<>();
    private MutableLiveData<List<OperateAnalyseBean>> actualEvent = new MutableLiveData<>();
    private MutableLiveData<List<OperateAnalyseBean>> totalEvent = new MutableLiveData<>();
    private MutableLiveData<List<OperateAnalyseBean>> detailEvent = new MutableLiveData<>();

    /**
     * 工程现场-意见回复-异步查询意见回复集
     */
    public void getOperateOverViewData(String time) {

        Disposable disposable = RxJavaHelper.build(this, repository.getOperateOverViewData(time))
                .getHelper().flow(overViewEvent);
        addSubscription(disposable);
    }

    public void getOperateStatistics(String time) {

        Disposable disposable = RxJavaHelper.build(this, repository.getOperateStatistics(time))
                .getHelper().flow(statisticsEvent);
        addSubscription(disposable);
    }

    public void getPaymentList(String time) {

        Disposable disposable = RxJavaHelper.build(this, repository.getPaymentList(time))
                .getHelper().flow(actualEvent);
        addSubscription(disposable);
    }

    public void getCostList(String time) {

        Disposable disposable = RxJavaHelper.build(this, repository.getCostList(time))
                .getHelper().flow(totalEvent);
        addSubscription(disposable);
    }

    public void getOperateDetailData(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getOperateDetailData(id))
                .getHelper().flow(detailEvent);
        addSubscription(disposable);
    }

    public MutableLiveData<OperateOverViewBean> getOverViewEvent() {
        return overViewEvent;
    }

    public void setOverViewEvent(MutableLiveData<OperateOverViewBean> overViewEvent) {
        this.overViewEvent = overViewEvent;
    }

    public MutableLiveData<OperateStatisticsBean> getStatisticsEvent() {
        return statisticsEvent;
    }

    public void setStatisticsEvent(MutableLiveData<OperateStatisticsBean> statisticsEvent) {
        this.statisticsEvent = statisticsEvent;
    }

    public MutableLiveData<List<OperateAnalyseBean>> getActualEvent() {
        return actualEvent;
    }

    public void setActualEvent(MutableLiveData<List<OperateAnalyseBean>> actualEvent) {
        this.actualEvent = actualEvent;
    }

    public MutableLiveData<List<OperateAnalyseBean>> getTotalEvent() {
        return totalEvent;
    }

    public void setTotalEvent(MutableLiveData<List<OperateAnalyseBean>> totalEvent) {
        this.totalEvent = totalEvent;
    }

    public MutableLiveData<List<OperateAnalyseBean>> getDetailEvent() {
        return detailEvent;
    }

    public void setDetailEvent(MutableLiveData<List<OperateAnalyseBean>> detailEvent) {
        this.detailEvent = detailEvent;
    }

    public OperateRepository getRepository() {
        return repository;
    }

    public void setRepository(OperateRepository repository) {
        this.repository = repository;
    }
}
