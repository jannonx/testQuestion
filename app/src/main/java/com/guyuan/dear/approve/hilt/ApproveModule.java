package com.guyuan.dear.approve.hilt;

import com.guyuan.dear.approve.api.ApproveApiService;
import com.guyuan.dear.approve.data.ApproveRepository;
import com.guyuan.dear.base.api.BaseModule;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class ApproveModule extends BaseModule {

    @Provides
    public ApproveApiService providesLoginApiService() {
        return retrofit.create(ApproveApiService.class);
    }

    @Provides
    public ApproveRepository providesLoginRepository(ApproveApiService approveApiService) {
        return new ApproveRepository(approveApiService);
    }
}
