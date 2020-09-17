package com.guyuan.dear.work.aftersale.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleRepository;
import com.guyuan.dear.work.aftersale.api.WorkAfterSaleApiService;
import com.guyuan.dear.work.aftersale.data.WorkAfterSaleRepository;

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
public class WorkAfterSaleModule extends BaseModule {

    @Provides
    public WorkAfterSaleApiService providesWorkAfterSaleApiService() {
        return retrofit.create(WorkAfterSaleApiService.class);
    }

    @Provides
    public WorkAfterSaleRepository providesWorkAfterSaleRepository(WorkAfterSaleApiService workAfterSaleApiService) {
        return new WorkAfterSaleRepository(workAfterSaleApiService);
    }
}
