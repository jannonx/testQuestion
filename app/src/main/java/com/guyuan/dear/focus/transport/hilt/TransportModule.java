package com.guyuan.dear.focus.transport.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.transport.api.TransportApiService;
import com.guyuan.dear.focus.transport.data.TransportRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:34
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class TransportModule extends BaseModule {

    @Provides
    public TransportApiService providesTransportApiService() {
        return retrofit.create(TransportApiService.class);
    }

    @Provides
    public TransportRepository providesTransportRepository(TransportApiService apiService){
        return new TransportRepository(apiService);
    }
}