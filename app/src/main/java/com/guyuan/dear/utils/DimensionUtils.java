package com.guyuan.dear.utils;

import android.util.DisplayMetrics;

import com.guyuan.dear.base.app.DearApplication;

/**
 * @author: Jannonx
 * @description:
 * @since: 2020/10/13 16:18
 **/
public class DimensionUtils {
    public static float dp2px(float dp) {
        DisplayMetrics metrics = DearApplication.getInstance().getDisplayMetrics();
        float density = metrics.density;
        return dp * density;
    }
}
