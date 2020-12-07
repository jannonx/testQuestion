package com.guyuan.dear.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.mvvmlibrary.util.LogUtils;
import com.example.mvvmlibrary.util.MediaFileUtils;
import com.google.gson.Gson;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.dialog.TipDialogFragment;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.ui.LoginActivity;
import com.guyuan.dear.work.contractPause.beans.StaffBean;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import droidninja.filepicker.utils.ContentUriUtils;
import okhttp3.RequestBody;
import retrofit2.http.PUT;

import static java.security.AccessController.getContext;

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

    //获取本地缓存登录信息
    public static LoginBean getLoginInfo() {
        String loginStr =
                (String) DearApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        return new Gson().fromJson(loginStr, LoginBean.class);
    }

    public static long getCurrentUserId() {
        return getLoginInfo().getUserInfo().getId();
    }

    public static FactoryBean getFactoryListFromCache() {
        FactoryBean bean = null;
        String factoryCache =
                (String) DearApplication.getInstance().getCacheData(ConstantValue.FACTORY_LIST, "");
        if (!"".equals(factoryCache)) {
            bean = GsonUtil.stringToBean(factoryCache, FactoryBean.class);
        }
        return bean;
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


    //登出
    public static void logout(Context context) {
        LoginBean loginInfo = CommonUtils.getLoginInfo();
        if (loginInfo != null) {
            //    UmengUtils.unregisterAlias(loginInfo.getUserId());
        }
        SharedPreferencesUtils.removeData(context, ConstantValue.KEY_USER_PW);
        SharedPreferencesUtils.removeData(context, ConstantValue.USER_JSON_STRING);
        ActivityUtils.removeAllActivity();
        String loginStr =
                (String) DearApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        LogUtils.showLog(loginStr);
        LoginActivity.start(context);
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

    //检查我的关注跳转协议
    public static boolean checkFocusAction(String action) {
        return Arrays.asList(ConstantValue.FOCUS_ACTIONS).contains(action);
    }

    //跳转打电话界面
    public static void makePhoneCall(FragmentActivity activity, String phoneNumber) {
        TipDialogFragment tipDialogFragment = TipDialogFragment.newInstance("确定要拨打电话吗?", "");
        tipDialogFragment.setOnCancelListener(new TipDialogFragment.OnCancel() {
            @Override
            public void cancel() {
                tipDialogFragment.dismiss();
            }
        }).setOnSureListener(new TipDialogFragment.OnSure() {
            @Override
            public void sure() {
                tipDialogFragment.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNumber);
                intent.setData(data);
                activity.startActivity(intent);
            }
        }).show(activity.getSupportFragmentManager(), TipDialogFragment.TAG);
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

    //获取当前账号staffbean
    public static ArrayList<StaffBean> getCurrentStaffList() {
        ArrayList<StaffBean> staffBeanList = new ArrayList<>();
        StaffBean staffBean = new StaffBean();
        staffBean.setId(getLoginInfo().getUserInfo().getId());
        staffBeanList.add(staffBean);
        return staffBeanList;
    }

    //获取uri对应filepath
    public static List<String> getFilePath(List<Uri> uriList) {
        List<String> filePathList = new ArrayList<>();
        if (uriList != null && uriList.size() > 0) {
            for (Uri uri : uriList) {
                try {
                    filePathList.add(ContentUriUtils.INSTANCE.getFilePath(DearApplication.getInstance(), uri));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return filePathList;
    }
}
