package com.guyuan.dear.login.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.HttpUtil;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginApiService extends BaseApiService {

    @POST(HttpUtil.LOGIN)
    Observable<ResultBean<LoginBean>> getLoginInfo(@Body RequestBody loginBody);

}