package com.example.mvvmlibrary.util;

import android.util.Log;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public class LogUtils {
    public static void showLog(String msg) {
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
        Log.e("DearLog", sb.toString());
    }
}
