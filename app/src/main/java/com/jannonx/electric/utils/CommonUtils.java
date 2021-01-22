package com.jannonx.electric.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.mvvmlibrary.util.MediaFileUtils;
import com.jannonx.electric.BuildConfig;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.RequestBody;

/**
 * Created by admin
 * on 2019/11/25
 */
public class CommonUtils {

    private static long lastClickTime;

    /**
     * 获取屏幕高度宽度
     */
    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }


    public static RequestBody getCommonRequestBody(Object object) {
        String str = GsonUtil.objectToString(object);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }


    public static void showLocalPic(@NonNull Context context, @NonNull String path,
                                    @NonNull ImageView iv) {
        Glide.with(context).load(path).into(iv);
    }

    public static Bitmap compressPic(String fileAddress, int width, int height) {
        return Bitmap.createScaledBitmap(BitmapFactory.decodeFile(fileAddress), width, height, true);
    }


    //int秒转化为小时分秒
    public static String getHour(int time) {
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = time % 60;
        time -= second;
        if (time > 0) {
            time /= 60;
            minute = time % 60;
            time -= minute;
            if (time > 0) {
                hour = time / 60;
            }
        }
        return new String(hour + "小时" + minute + "分" + second + "秒");
    }

    //判断字符串是否是数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //判断是否是连点
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    // 判断一个字符串是否含有数字
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    //保留两位小数
    public static double getNumberPointOne(String str) {
        double num = 0;
        DecimalFormat myformat = new DecimalFormat("#0.0");
        try {
            num = Double.parseDouble(str) / 10000;//装换为double类型

            num = Double.parseDouble(myformat.format(num));//保留2为小数
        } catch (Exception e) {
            return num;
        }
        return num;
    }

    //判断字符串是否含有字母
    public static boolean judgeContainsStr(String str) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }


    private static HashMap<Integer, Integer> selectMap = new HashMap<>();

    public static void putSelectResult(int index, int position) {
        selectMap.put(index, position);
        for (Map.Entry<Integer, Integer> entry : selectMap.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }

    public static HashMap<Integer, Integer> getSelectMap() {
        return selectMap;
    }

    public static void clearSelectMap() {
        for (Iterator<Map.Entry<Integer, Integer>> it = selectMap.entrySet().iterator(); it.hasNext(); ) {
//            Map.Entry<Integer, Integer> item = it.next();
            it.remove();
        }
    }

    //判断路径文件是否为图片和视频
    public static boolean isPictureOrVideo(String url) {

        if (MediaFileUtils.isImageFileType(url)) {
            return true;
        }

        if (MediaFileUtils.isVideoFileType(url)) {
            return true;
        }

        return false;
    }

    //设置消息间隔
    public static int getMessageUpdateTime() {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            return 1000;
        } else {
            return 1;
        }
    }


    //获取合同状态
//    public static void getContractStatus(String contractParameterType, String contractParameter) {
//        Bundle bundle = new Bundle();
//        bundle.putString(BackService.CONTRACT_PARAMETER_TYPE, contractParameterType);
//        bundle.putString(BackService.CONTRACT_PARAMETER, contractParameter);
//        DearApplication.getInstance().startBackService(BackService.CONTRACT_STATUS, bundle);
//    }


}
