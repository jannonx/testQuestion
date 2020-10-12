package com.guyuan.dear.focus.stock.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.stock.api.FocusStockApiService;
import com.guyuan.dear.focus.stock.data.FocusStockRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:40
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class FocusStockModule extends BaseModule {

    @Provides
    public FocusStockApiService providesFocusStockApiService() {
        return retrofit.create(FocusStockApiService.class);
    }

    @Provides
    public FocusStockRepository providesFocusStockRepository(FocusStockApiService apiService) {
        return new FocusStockRepository(apiService);
    }
}
