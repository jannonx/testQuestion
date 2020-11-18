package com.guyuan.dear.ezCloud.net.rxjava;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 廖华凯
 * @since 2020/2/28 11:51
 **/
public class EzRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
        return chain.proceed(request);
    }
}
