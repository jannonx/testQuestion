package com.jannonx.electric.utils;

import android.util.DisplayMetrics;

import com.jannonx.electric.base.app.DearApplication;

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
