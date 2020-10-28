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
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusClientModule extends BaseModule {

    @Provides
    public FocusClientApiService providesFocusClientApiService() {
        return retrofit.create(FocusClientApiService.class);
    }

    @Provides
    public FocusClientRepository providesFocusClientRepository(FocusClientApiService focusAfterSaleApiService) {
        return new FocusClientRepository(focusAfterSaleApiService);
    }
}
