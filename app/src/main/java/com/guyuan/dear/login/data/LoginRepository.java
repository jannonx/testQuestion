package com.guyuan.dear.login.data;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.login.api.LoginApiService;


import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * created by tl
 * created at 2020/8/24
 */
public class LoginRepository  {
    private LoginApiService loginApiService ;

    public LoginRepository(LoginApiService loginApiService) {
        this.loginApiService = loginApiService;
    }

    public Observable<ResultBean<LoginBean>> getLoginData(RequestBody body) {
        return loginApiService.getLoginInfo(body);
    }

}
