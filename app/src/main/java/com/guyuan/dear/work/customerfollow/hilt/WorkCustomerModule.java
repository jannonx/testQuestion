package com.guyuan.dear.work.customerfollow.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.approve.api.WorkApproveApiService;
import com.guyuan.dear.work.approve.data.WorkApproveRepository;
import com.guyuan.dear.work.customerfollow.api.WorkCustomerApiService;
import com.guyuan.dear.work.customerfollow.data.WorkCustomerRepository;

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
public class WorkCustomerModule extends BaseModule {

    @Provides
    public WorkCustomerApiService providesWorkCustomerApiService() {
        return retrofit.create(WorkCustomerApiService.class);
    }

    @Provides
    public WorkCustomerRepository providesWorkCustomerRepository(WorkCustomerApiService workCustomerApiService) {
        return new WorkCustomerRepository(workCustomerApiService);
    }
}
