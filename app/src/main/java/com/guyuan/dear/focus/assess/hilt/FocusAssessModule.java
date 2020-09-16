package com.guyuan.dear.focus.assess.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.assess.api.FocusAssessApiService;
import com.guyuan.dear.focus.assess.data.FocusAssessRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:27
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class FocusAssessModule extends BaseModule {

    @Provides
    public FocusAssessApiService providesFocusAssessApiService() {
        return retrofit.create(FocusAssessApiService.class);
    }

    @Provides
    public FocusAssessRepository providesFocusAssessRepository(FocusAssessApiService focusAssessApiService) {
        return new FocusAssessRepository(focusAssessApiService);
    }
}
