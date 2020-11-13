package com.guyuan.dear.focus.purchase.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.purchase.api.FocusPurchaseApiService;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: 唐力
 * @since: 2020/9/17 11:03
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusPurchaseModule extends BaseModule {

    @Provides
    public FocusPurchaseApiService  providesFocusPurchaseApiService() {
        return retrofit.create(FocusPurchaseApiService.class);
    }

    @Provides
    public FocusPurchaseRepository providesFocusPurchaseRepository(FocusPurchaseApiService focusPurchaseApiService) {
        return new FocusPurchaseRepository(focusPurchaseApiService);
    }
}
