package com.jannonx.electric.home.hilt;

import com.jannonx.electric.base.api.BaseModule;
import com.jannonx.electric.home.api.MainApiService;
import com.jannonx.electric.home.data.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:39
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