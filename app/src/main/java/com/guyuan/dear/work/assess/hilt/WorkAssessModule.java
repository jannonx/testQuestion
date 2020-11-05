package com.guyuan.dear.work.assess.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.assess.api.WorkAssessApiService;
import com.guyuan.dear.work.assess.data.WorkAssessRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:42
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class WorkAssessModule extends BaseModule {

    @Provides
    public WorkAssessApiService providesWorkAssessApiService() {
        return retrofit.create(WorkAssessApiService.class);
    }

    @Provides
    public WorkAssessRepository providesWorkAssessRepository(WorkAssessApiService assessApiService) {
        return new WorkAssessRepository(assessApiService);
    }
}