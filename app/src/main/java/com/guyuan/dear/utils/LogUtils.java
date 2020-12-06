package com.guyuan.dear.utils;

import android.util.Log;

import com.guyuan.dear.BuildConfig;

/**
 * @author 廖华凯
 * @since 2019/11/19 15:07
 **/
public class LogUtils {
    public static void showLog(String msg) {
        //debug模式下显示
        if (BuildConfig.DEBUG) {
            StringBuilder sb = new StringBuilder();
            Throwable throwable = new Throwable();
            StackTraceElement element = throwable.getStackTrace()[1];
            String fullClassName = element.getClassName();
            String[] split = fullClassName.split("\\.");
            String className = split.length > 0 ? split[split.length - 1] : fullClassName;
            sb.append(className).append("->");
            sb.append(element.getMethodName()).append("->Line ");
            sb.append(element.getLineNumber()).append(":");
            sb.append(msg);
            Log.e("GuYuanLog", sb.toString());
        }

    }
}
