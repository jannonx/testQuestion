package com.guyuan.dear.utils;

import com.google.gson.Gson;

/**
 * @author : tl
 * @description :gonson工具类
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
public class GsonUtil {

  private static Gson gson;

  public static Gson getGson() {
    if (gson == null) {
      gson = new Gson();
    }
    return gson;
  }

  public static String objectToString(Object object) {
    return getGson().toJson(object);
  }

  public static <T> T stringToBean(String str, Class<T> t) {
    return getGson().fromJson(str, t);
  }
}
