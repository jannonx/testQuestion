package com.guyuan.dear.focus.projectsite.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.projectsite.api.FocusProjectSiteApiService;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusProjectSiteModule extends BaseModule {

    @Provides
    public FocusProjectSiteApiService providesFocusProjectSiteApiService() {
        return retrofit.create(FocusProjectSiteApiService.class);
    }

    @Provides
    public FocusProjectSiteRepository providesFocusProjectSiteRepository(FocusProjectSiteApiService focusProjectSiteApiService) {
        return new FocusProjectSiteRepository(focusProjectSiteApiService);
    }
}
