package com.guyuan.dear.focus.quality.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.quality.api.FocusQualityApiService;
import com.guyuan.dear.focus.quality.data.FocusQualityRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusQualityModule extends BaseModule {

    @Provides
    public FocusQualityApiService providesFocusQualityApiService() {
        return retrofit.create(FocusQualityApiService.class);
    }

    @Provides
    public FocusQualityRepository providesFocusQualityRepository(FocusQualityApiService focusQualityApiService) {
        return new FocusQualityRepository(focusQualityApiService);
    }
}
