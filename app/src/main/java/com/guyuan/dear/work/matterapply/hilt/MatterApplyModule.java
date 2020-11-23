package com.guyuan.dear.work.matterapply.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.work.matterapply.api.MatterApplyApiService;
import com.guyuan.dear.work.matterapply.data.MatterApplyRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:57
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class MatterApplyModule extends BaseModule {
    @Provides
    public MatterApplyApiService providesMatterApplyApiService() {
        return retrofit.create(MatterApplyApiService.class);
    }

    @Provides
    public MatterApplyRepository providesMatterApplyRepository(MatterApplyApiService applyApiService) {
        return new MatterApplyRepository(applyApiService);
    }
}