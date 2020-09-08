package com.example.httplibrary.interceptor;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 参数拦截器(添加共有参数)
 */
public class ParamsInterceptor implements Interceptor {

  private TreeMap<String, String> mMap;

  public ParamsInterceptor() {
    mMap = new TreeMap<>();
  }

  public ParamsInterceptor(String key, String value) {
    this();
    mMap.put(key, value);
  }

  @SuppressWarnings("unused")
  public ParamsInterceptor(TreeMap<String, String> hashMap) {
    this();
    mMap.putAll(hashMap);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    HttpUrl originalHttpUrl = originalRequest.url();


    Map<String, String> content = new TreeMap<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {//比较器不区分大小写,不然会导致安全码校验不通过
        String key1 = o1.toLowerCase();
        String key2 = o2.toLowerCase();
        return key1.compareTo(key2);
      }
    });

    StringBuilder contentBuilder = new StringBuilder("");   //构建加密体

    if ("GET".equals(originalRequest.method())) {
      int size = originalHttpUrl.querySize();
      for (int i = 0; i < size; i++) {
        content.put(originalHttpUrl.queryParameterName(i), originalHttpUrl.queryParameterValue(i));
      }

      for (String key : content.keySet()) {
        contentBuilder.append(key).append(content.get(key));
      }

    } else if ("POST".equals(originalRequest.method())) {
      RequestBody body = originalRequest.body();
      if (body instanceof FormBody) {
        FormBody formBody = (FormBody) body;
        if (mMap != null && mMap.size() > 0) {
          content.putAll(mMap);
        }
        for (int i = 0; i < formBody.size(); i++) {
          String key = formBody.encodedName(i);
          String value = formBody.encodedValue(i);
          content.put(key, value);
        }
        for (String key : content.keySet()) {
          contentBuilder.append(key).append(content.get(key));
        }
      }else if(body instanceof MultipartBody){
       // content = application.getTreeMapData(ConstantsValue.MultipartBody);
        for (String key : content.keySet()) {
          String encodeKey = URLEncoder.encode(key,"UTF-8");
          String encodeValue = URLEncoder.encode(content.get(key),"UTF-8");
          contentBuilder.append(encodeKey).append(encodeValue);
        }
      }
    }

    return chain.proceed(originalRequest);
  }
}
