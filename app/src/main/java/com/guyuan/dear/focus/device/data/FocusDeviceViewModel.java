package com.guyuan.dear.focus.device.data;

import android.app.Application;
import android.os.CpuUsageInfo;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.focus.device.api.FocusDeviceApiService;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceViewModel extends BaseViewModel {

    private FocusDeviceApiService focusDeviceApiService;
    private MutableLiveData<DeviceNumberBean> deviceNumberMLD = new MutableLiveData<>();
    private MutableLiveData<DeviceExceptionBean> deviceExceptionMLD = new MutableLiveData<>();
    private MutableLiveData<FactoryRealTimeBean> factoryRealTimeMLD = new MutableLiveData<>();
    private MutableLiveData<FactoryBean> factoryMLD = new MutableLiveData<>();
    private MutableLiveData<List<EquipmentBean>> equipmentListMLD = new MutableLiveData<>();

    @ViewModelInject
    public FocusDeviceViewModel(FocusDeviceRepository focusDeviceRepository) {
        focusDeviceApiService = focusDeviceRepository.getFocusDeviceApiService();
    }

    public MutableLiveData<DeviceNumberBean> getDeviceNumberMLD() {
        return deviceNumberMLD;
    }

    public MutableLiveData<DeviceExceptionBean> getDeviceExceptionMLD() {
        return deviceExceptionMLD;
    }

    public MutableLiveData<FactoryRealTimeBean> getFactoryRealTimeMLD() {
        return factoryRealTimeMLD;
    }

    public MutableLiveData<FactoryBean> getFactoryMLD() {
        return factoryMLD;
    }

    public MutableLiveData<List<EquipmentBean>> getEquipmentListMLD() {
        return equipmentListMLD;
    }

    //获取设备总数
    public void getDeviceTotalNumber() {
        Disposable disposable = RxJavaHelper.build(this, focusDeviceApiService.getDeviceNumber())
                .getHelper().flow(deviceNumberMLD);
        addSubscription(disposable);
    }


    //获取工厂列表
    public void getFactoryList() {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getFactoryList(CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(factoryMLD);
        addSubscription(disposable);
    }

    //获取厂区各车间的设备
    public void getFactoryDevice(long id) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getFactoryRealTimeList(id)).getHelper().flow(factoryRealTimeMLD);
        addSubscription(disposable);
    }

    //获取设备异常列表
    public void getExceptionDevice(int pageIndex) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getExceptionList(CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(deviceExceptionMLD);
        addSubscription(disposable);
    }

    //获取设备概览分类设备
    public void getOverviewTypeDevice(long typeId) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getOverViewTypeDevice(typeId)).getHelper().flow(equipmentListMLD);
    }

    //获取设备概览所有设备
    public void getOverviewTotalDevice() {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getOverviewTotalDevice()).getHelper().flow(equipmentListMLD);
    }
}
