package com.jannonx.electric.base.api.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * @description: 请求头拦截器
 * @author: Jannonx
 * @since: 2021/1/21 20:57
 */
public class HeadInterceptor implements Interceptor {

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
//    LoginBean loginBean = CommonUtils.getLoginInfo();
//    String token = "";
//    if (loginBean != null) {
//      token = loginBean.getToken();
//    }
//    Request request = chain.request()
//        .newBuilder()
//        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
//        .addHeader("Accept-Encoding", "gzip, deflate")
//        .addHeader("Accept", "*/*")
//        .addHeader("token", token)
//        .addHeader("clientId", String.valueOf(2))
//        .build();
//    return chain.proceed(request);

    return null;
  }

}
