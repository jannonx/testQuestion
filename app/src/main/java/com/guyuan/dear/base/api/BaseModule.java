package com.guyuan.dear.base.api;

import com.guyuan.dear.base.app.DearApplication;

import retrofit2.Retrofit;

/**
 * @author : tl
 * @description : module基类，提供一些通用对象
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/

public class BaseModule {
    public Retrofit retrofit = DearApplication.getInstance().getRetrofit();
}
