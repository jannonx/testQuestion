package com.guyuan.dear.login.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.login.data.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginApiService extends BaseApiService {

    String LOGIN = BASE + "login";

    //登录
    @POST(LOGIN)
    Observable<ResultBean<LoginBean>> getLoginInfo(@Body RequestBody loginBody);

}