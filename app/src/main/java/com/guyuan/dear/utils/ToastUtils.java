package com.guyuan.dear.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @description: 吐司工具类
 * @author:Jannonx
 * @date: 2020/5/19 11:42
 */
public class ToastUtils {
  public static void showShort(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }

  public static void showLong(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
  }
}
