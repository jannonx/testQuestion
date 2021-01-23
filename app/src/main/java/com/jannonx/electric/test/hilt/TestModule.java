package com.jannonx.electric.test.hilt;

import com.jannonx.electric.base.api.BaseModule;
import com.jannonx.electric.test.api.TestApiService;
import com.jannonx.electric.test.data.TestRepository;

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
public class TestModule extends BaseModule {

    @Provides
    public TestApiService providesMainApiService() {
        return retrofit.create(TestApiService.class);
    }

    @Provides
    public TestRepository providesMainRepository(TestApiService apiService) {
        return new TestRepository(apiService);
    };
}