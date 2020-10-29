package com.guyuan.dear.work.client.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.client.api.WorkClientApiService;
import com.guyuan.dear.work.client.data.WorkClientRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class WorkClientModule extends BaseModule {

    @Provides
    public WorkClientApiService providesWorkClientApiService() {
        return retrofit.create(WorkClientApiService.class);
    }

    @Provides
    public WorkClientRepository providesWorkClientRepository(WorkClientApiService focusAfterSaleApiService) {
        return new WorkClientRepository(focusAfterSaleApiService);
    }
}
