package com.guyuan.dear.work.goodssign.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.goodssign.api.GoodsSignApiService;
import com.guyuan.dear.work.goodssign.data.GoodsSignRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:35
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class GoodsSignModule extends BaseModule {
    @Provides
    public GoodsSignApiService providesGoodsSignApiService() {
        return retrofit.create(GoodsSignApiService.class);
    }

    @Provides
    public GoodsSignRepository providesGoodsSignRepository(GoodsSignApiService apiService) {
        return new GoodsSignRepository(apiService);
    }
}