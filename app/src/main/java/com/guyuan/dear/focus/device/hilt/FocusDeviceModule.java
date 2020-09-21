package com.guyuan.dear.focus.device.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.device.api.FocusDeviceApiService;
import com.guyuan.dear.focus.device.data.FocusDeviceRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:26
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class FocusDeviceModule extends BaseModule {

    @Provides
    public FocusDeviceApiService providesFocusDeviceApiService() {
        return retrofit.create(FocusDeviceApiService.class);
    }

    @Provides
    public FocusDeviceRepository providesFocusDeviceRepository(FocusDeviceApiService focusDeviceApiService) {
        return new FocusDeviceRepository(focusDeviceApiService);
    }
}
