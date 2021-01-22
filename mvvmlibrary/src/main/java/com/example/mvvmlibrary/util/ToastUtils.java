package com.example.mvvmlibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
