package com.guyuan.dear.work.device.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.device.api.WorkDeviceApiService;
import com.guyuan.dear.work.device.data.WorkDeviceRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/30 11:06
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class WorkDeviceModule extends BaseModule {

    @Provides
    public WorkDeviceApiService providesWorkDeviceApiService() {
        return retrofit.create(WorkDeviceApiService.class);
    }

    @Provides
    public WorkDeviceRepository providesWorkDeviceRepository(WorkDeviceApiService apiService) {
        return new WorkDeviceRepository(apiService);
    }
}