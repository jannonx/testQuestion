package com.guyuan.dear.focus.client.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.client.api.FocusClientApiService;
import com.guyuan.dear.focus.client.data.FocusClientRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusClientModule extends BaseModule {

    @Provides
    public FocusClientApiService provideFocusClientApiService() {
        return retrofit.create(FocusClientApiService.class);
    }

    @Provides
    public FocusClientRepository providesFocusClientRepository(FocusClientApiService clientApiService) {
        return new FocusClientRepository(clientApiService);
    }
}
