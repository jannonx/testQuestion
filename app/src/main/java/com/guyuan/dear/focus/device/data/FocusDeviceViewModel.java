package com.guyuan.dear.focus.device.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.device.api.FocusDeviceApiService;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
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
    public MutableLiveData<DeviceNumberBean> deviceNumberBean;
    public MutableLiveData<DeviceExceptionBean> deviceExceptionBean;
    public MutableLiveData<FactoryRealTimeBean> factoryRealTimeBean;
    public MutableLiveData<FactoryBean> factoryBean;
    public MutableLiveData<List<EquipmentBean>> equipmentListBean;

    @ViewModelInject
    public FocusDeviceViewModel(FocusDeviceRepository focusDeviceRepository) {
        focusDeviceApiService = focusDeviceRepository.getFocusDeviceApiService();
    }


    //获取设备总数
    public void getDeviceTotalNumber() {
        Disposable disposable = RxJavaHelper.build(this, focusDeviceApiService.getDeviceNumber())
                .getHelper().flow(deviceNumberBean);
        addSubscription(disposable);
    }


    //获取工厂列表
    public void getFactoryList(int pageIndex) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getFactoryList(pageIndex, ConstantValue.PAGE_SIZE))
                .getHelper().flow(factoryBean);
        addSubscription(disposable);
    }

    //获取厂区各车间的设备
    public void getFactoryDevice(long id) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getFactoryRealTimeList(id)).getHelper().flow(factoryRealTimeBean);
        addSubscription(disposable);
    }

    //获取设备异常列表
    public void getExceptionDevice(int pageIndex) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getExceptionList(pageIndex)).getHelper().flow(deviceExceptionBean);
        addSubscription(disposable);
    }

    //获取设备概览分类设备
    public void getOverviewTypeDevice(long typeId) {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getOverViewTypeDevice(typeId)).getHelper().flow(equipmentListBean);
    }

    //获取设备概览所有设备
    public void getOverviewTotalDevice() {
        Disposable disposable = RxJavaHelper.build(this,
                focusDeviceApiService.getOverviewTotalDevice()).getHelper().flow(equipmentListBean);
    }
}
