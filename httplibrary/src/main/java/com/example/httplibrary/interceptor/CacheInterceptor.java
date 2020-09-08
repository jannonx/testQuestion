package com.example.httplibrary.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tl on 2018-8-9
 * 缓存拦截器
 */
public class CacheInterceptor implements Interceptor {

  private int cacheTime;

  public CacheInterceptor(int cacheTime) {
    this.cacheTime = cacheTime;
  }

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();

    request = request.newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build();

    Response response = chain.proceed(request);
    // 有网络时 缓存时间以缓存头的为主
    String cacheControl = response.cacheControl().toString();
    if (!cacheControl.contains("max-age")) { //如果服务器不支持缓存需要在响应头设置Cache-Control来让okhttp缓存
      cacheControl = request.cacheControl().toString();//这里取请求头的cacheControl,也可以自己设置,格式："public,
      // max-age=60"
      response = response.newBuilder()
          .header("Cache-Control", cacheControl)//注意这里是服务器返回的头
          .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
          .build();
      System.out.println();
    }

    return response;
  }


}
