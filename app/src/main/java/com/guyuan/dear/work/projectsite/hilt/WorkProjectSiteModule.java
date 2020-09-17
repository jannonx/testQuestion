package com.guyuan.dear.work.projectsite.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.projectsite.api.FocusProjectSiteApiService;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteRepository;
import com.guyuan.dear.work.projectsite.api.WorkProjectSiteApiService;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class WorkProjectSiteModule extends BaseModule {

    @Provides
    public WorkProjectSiteApiService providesWorkProjectSiteApiService() {
        return retrofit.create(WorkProjectSiteApiService.class);
    }

    @Provides
    public WorkProjectSiteRepository providesWorkProjectSiteRepository(WorkProjectSiteApiService workProjectSiteApiService) {
        return new WorkProjectSiteRepository(workProjectSiteApiService);
    }
}
