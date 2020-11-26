package com.guyuan.dear.focus.aftersale.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class FocusAfterSaleModule extends BaseModule {

    @Provides
    public FocusAfterSaleApiService providesFocusAfterSaleApiService() {
        return retrofit.create(FocusAfterSaleApiService.class);
    }

    @Provides
    public FocusAfterSaleRepository providesFocusAfterSaleRepository(FocusAfterSaleApiService focusAfterSaleApiService) {
        return new FocusAfterSaleRepository(focusAfterSaleApiService);
    }
}
