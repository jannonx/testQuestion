package com.guyuan.dear.focus.produce.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.data.FocusClientRepository;
import com.guyuan.dear.focus.produce.api.FocusProduceApiService;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceOverViewBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;

import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceViewModel extends BaseViewModel {
    private FocusProduceRepository repository;
    private MutableLiveData<ProduceOverViewBean> overViewEvent = new MutableLiveData<>();
    private MutableLiveData<RefreshBean<FocusProduceBean>> statusEvent = new MutableLiveData<>();
    private MutableLiveData<RefreshBean<FocusProduceBean>> exceptionListEvent = new MutableLiveData<>();
    private MutableLiveData<RefreshBean<FocusProduceBean>> produceListEvent = new MutableLiveData<>();
    private MutableLiveData<List<ProduceStateBean>> statusListEvent = new MutableLiveData<>();
    private MutableLiveData<FocusProduceBean> basicInfoEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> executeEvent = new MutableLiveData<>();


    @ViewModelInject
    public FocusProduceViewModel(FocusProduceRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    public MutableLiveData<ProduceOverViewBean> getOverViewEvent() {
        return overViewEvent;
    }

    public MutableLiveData<RefreshBean<FocusProduceBean>> getStatusEvent() {
        return statusEvent;
    }

    public MutableLiveData<RefreshBean<FocusProduceBean>> getExceptionListEvent() {
        return exceptionListEvent;
    }

    public MutableLiveData<RefreshBean<FocusProduceBean>> getProduceListEvent() {
        return produceListEvent;
    }

    public MutableLiveData<List<ProduceStateBean>> getStatusListEvent() {
        return statusListEvent;
    }

    public MutableLiveData<FocusProduceBean> getBasicInfoEvent() {
        return basicInfoEvent;
    }

    public MutableLiveData<Integer> getExecuteEvent() {
        return executeEvent;
    }

    /**
     * 根据客户id查询跟进
     *
     * @param body 列表参数
     */
    public void getProduceOverView(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceOverView(body))
                .getHelper().flow(overViewEvent);
        addSubscription(disposable);
    }

    /**
     * 根据不同的生产状态查询生产报告列表
     *
     * @param body 请求参数
     */
    public void getProduceListByStatus(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceListByStatus(body))
                .getHelper().flow(statusEvent);
        addSubscription(disposable);
    }

    /**
     * 查询异常生产状态的生产报告列表
     *
     * @param body 请求参数
     */
    public void getProduceExceptionList(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceExceptionList(body))
                .getHelper().flow(exceptionListEvent);
        addSubscription(disposable);
    }

    /**
     * 查询生产详情列表
     *
     * @param body 请求参数
     */
    public void getProduceList(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceList(body))
                .getHelper().flow(produceListEvent);
        addSubscription(disposable);
    }

    /**
     * 生产详情--基本信息
     *
     * @param equipmentId 配套设备id
     */
    public void getBasicInfoById(long equipmentId) {
        Disposable disposable = RxJavaHelper.build(this, repository.getBasicInfoById(equipmentId))
                .getHelper().flow(basicInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 生产详情--生产动态
     *
     * @param planId 主生产计划id
     * @return
     */
    public void getProduceStateList(long planId) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceStateList(planId))
                .getHelper().flow(statusListEvent);
        addSubscription(disposable);
    }

    /**
     * 根据设备id和操作类型执行操作
     *
     * @param body 提交信息
     * @return
     */
    public void postExecuteProduceInfo(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.postExecuteProduceInfo(body))
                .getHelper().flow(executeEvent);
        addSubscription(disposable);
    }
}
