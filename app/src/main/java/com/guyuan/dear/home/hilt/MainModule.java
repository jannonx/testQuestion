package com.guyuan.dear.home.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.home.api.MainApiService;
import com.guyuan.dear.home.data.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:39
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class MainModule extends BaseModule {

    @Provides
    public MainApiService providesMainApiService() {
        return retrofit.create(MainApiService.class);
    }

    @Provides
    public MainRepository providesMainRepository(MainApiService apiService) {
        return new MainRepository(apiService);
    };
}