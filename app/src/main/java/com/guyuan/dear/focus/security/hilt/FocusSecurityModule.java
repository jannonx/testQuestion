package com.guyuan.dear.focus.security.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.security.api.FocusSecurityApiService;
import com.guyuan.dear.focus.security.data.FocusSecurityRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 17:20
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class FocusSecurityModule extends BaseModule {

    @Provides
    public FocusSecurityApiService providesFocusSecurityApiService() {
        return retrofit.create(FocusSecurityApiService.class);
    }

    @Provides
    public FocusSecurityRepository providesFocusSecurityRepository(FocusSecurityApiService apiService) {
        return new FocusSecurityRepository(apiService);
    }
}
