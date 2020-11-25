package com.guyuan.dear.message.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.message.api.MessageApiService;
import com.guyuan.dear.message.data.MessageRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:02
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class MessageModule extends BaseModule {

    @Provides
    public MessageApiService providesMessageApiService() {
        return retrofit.create(MessageApiService.class);
    }

    @Provides
    public MessageRepository providesMessageRepository(MessageApiService apiService) {
        return new MessageRepository(apiService);
    }
}