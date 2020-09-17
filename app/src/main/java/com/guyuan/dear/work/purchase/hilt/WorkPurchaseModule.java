package com.guyuan.dear.work.purchase.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.purchase.api.FocusPurchaseApiService;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseRepository;
import com.guyuan.dear.work.purchase.api.WorkPurchaseApiService;
import com.guyuan.dear.work.purchase.data.WorkPurchaseRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:03
 * @company: 固远（深圳）信息技术有限公司
 */
@Module
@InstallIn(ActivityComponent.class)
public class WorkPurchaseModule extends BaseModule {

    @Provides
    public WorkPurchaseApiService providesWorkPurchaseApiService() {
        return retrofit.create(WorkPurchaseApiService.class);
    }

    @Provides
    public WorkPurchaseRepository providesWorkPurchaseRepository(WorkPurchaseApiService workPurchaseApiService) {
        return new WorkPurchaseRepository(workPurchaseApiService);
    }
}
