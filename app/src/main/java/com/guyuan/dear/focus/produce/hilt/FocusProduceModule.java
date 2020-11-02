package com.guyuan.dear.focus.produce.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.produce.api.FocusProduceApiService;
import com.guyuan.dear.focus.produce.data.FocusProduceRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusProduceModule extends BaseModule {

    @Provides
    public FocusProduceApiService providesFocusProduceApiService() {
        return retrofit.create(FocusProduceApiService.class);
    }

    @Provides
    public FocusProduceRepository providesFocusProduceRepository(FocusProduceApiService apiService) {
        return new FocusProduceRepository(apiService);
    }
}
