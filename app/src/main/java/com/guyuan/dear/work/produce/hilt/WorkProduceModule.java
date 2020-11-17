package com.guyuan.dear.work.produce.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.produce.api.WorkProduceApiService;
import com.guyuan.dear.work.produce.data.WorkProduceRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class WorkProduceModule extends BaseModule {

    @Provides
    public WorkProduceApiService providesWorkProduceApiService() {
        return retrofit.create(WorkProduceApiService.class);
    }

    @Provides
    public WorkProduceRepository providesWorkProduceRepository(WorkProduceApiService apiService) {
        return new WorkProduceRepository(apiService);
    }
}
