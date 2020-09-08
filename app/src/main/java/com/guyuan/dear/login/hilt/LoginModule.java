package com.guyuan.dear.login.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.login.api.LoginApiService;
import com.guyuan.dear.login.data.LoginRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import retrofit2.Retrofit;

/**
 * created by tl
 * created at 2020/8/24
 */
@Module
@InstallIn(ActivityComponent.class)
public class LoginModule extends BaseModule {

    @Provides
    public LoginApiService providesLoginApiService() {
        return retrofit.create(LoginApiService.class);
    }

    @Provides
    public LoginRepository providesLoginRepository(LoginApiService loginApiService) {
        return new LoginRepository(loginApiService);
    }
}
