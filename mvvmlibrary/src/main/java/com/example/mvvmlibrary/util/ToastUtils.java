package com.example.mvvmlibrary.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/7 15:19
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setText(msg);
        toast.show();
    }
}
