package com.guyuan.dear.focus.device.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceViewModel extends BaseViewModel {

    private FocusDeviceRepository focusDeviceRepository;

    @ViewModelInject
    public FocusDeviceViewModel( FocusDeviceRepository focusDeviceRepository) {
        super(DearApplication.getInstance());
        this.focusDeviceRepository = focusDeviceRepository;
    }


}
