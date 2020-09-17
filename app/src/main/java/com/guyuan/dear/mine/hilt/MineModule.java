package com.guyuan.dear.mine.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleRepository;
import com.guyuan.dear.mine.api.MineApiService;
import com.guyuan.dear.mine.data.MineRepository;

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
public class MineModule extends BaseModule {

    @Provides
    public MineApiService providesMineApiService() {
        return retrofit.create(MineApiService.class);
    }

    @Provides
    public MineRepository providesMineRepository(MineApiService mineApiService) {
        return new MineRepository(mineApiService);
    }
}
