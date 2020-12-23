package com.guyuan.dear.utils;

import android.content.Context;

import com.example.mvvmlibrary.BuildConfig;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/21 15:46
 * @company : 固远（深圳）信息技术有限公司
 **/

public class BuglyUtil {
    public static boolean hasNewVersion = false;

    //初始化bugly
    public static void initBugly(Context context, String buildType, String buildName,
                                 String appID,String buglyAppID ,int bannerResID, int layoutID, Class activity) {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppChannel(BuildConfig.BUILD_TYPE);  //设置渠道
        strategy.setAppVersion(BuildConfig.VERSION_NAME);      //App的版本
        strategy.setAppPackageName(appID);  //App的包名
        //设置只在哪个activity弹窗
        Beta.canShowUpgradeActs.add(activity);
        //    Beta.largeIconId = R.mipmap.ic_launcher;
        //    Beta.smallIconId = R.mipmap.ic_launcher;
        //    Beta.enableNotification = true;
        //    Beta.canShowApkInfo = true;//设置是否显示apk信息
        Beta.defaultBannerId = bannerResID;
        Beta.upgradeDialogLayoutId = layoutID;
        Bugly.init(context, buglyAppID, BuildConfig.DEBUG, strategy);
        checkNewVersion();
    }

    //检查是否有新版本信息
    private static void checkNewVersion() {
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        hasNewVersion = upgradeInfo != null;
    }
}