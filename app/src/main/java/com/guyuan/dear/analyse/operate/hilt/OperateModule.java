package com.guyuan.dear.analyse.operate.hilt;

import com.guyuan.dear.analyse.operate.api.OperateApiService;
import com.guyuan.dear.analyse.operate.data.OperateRepository;
import com.guyuan.dear.base.api.BaseModule;

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
public class OperateModule extends BaseModule {

    @Provides
    public OperateApiService providesOperateApiService() {
        return retrofit.create(OperateApiService.class);
    }

    @Provides
    public OperateRepository providesFocusAfterSaleRepository(OperateApiService operateApiService) {
        return new OperateRepository(operateApiService);
    }
}
