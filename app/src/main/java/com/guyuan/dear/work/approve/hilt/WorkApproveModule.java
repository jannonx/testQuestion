package com.guyuan.dear.work.approve.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.approve.api.WorkApproveApiService;
import com.guyuan.dear.work.approve.data.WorkApproveRepository;

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
public class WorkApproveModule extends BaseModule {

    @Provides
    public WorkApproveApiService providesWorkApproveApiService() {
        return retrofit.create(WorkApproveApiService.class);
    }

    @Provides
    public WorkApproveRepository providesWorkApproveRepository(WorkApproveApiService workApproveApiService) {
        return new WorkApproveRepository(workApproveApiService);
    }
}
