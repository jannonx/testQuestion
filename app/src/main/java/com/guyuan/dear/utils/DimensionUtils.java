package com.guyuan.dear.utils;

import android.util.DisplayMetrics;

import com.guyuan.dear.base.app.DearApplication;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/13 16:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DimensionUtils {
    public static float dp2px(float dp) {
        DisplayMetrics metrics = DearApplication.getInstance().getDisplayMetrics();
        float density = metrics.density;
        return dp * density;
    }
}
