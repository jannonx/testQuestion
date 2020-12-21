package com.guyuan.dear.base.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.httplibrary.BaseApiServiceModule;
import com.example.mvvmlibrary.app.BaseApplication;
import com.example.mvvmlibrary.util.BuglyUtil;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.R;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.service.BackService;
import com.guyuan.dear.utils.ConstantValue;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.crashreport.CrashReport;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.HiltAndroidApp;
import retrofit2.Retrofit;

/**
 * @author : tl
 * @description : 项目application维护一些全局变量或者方法
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
@HiltAndroidApp
public class DearApplication extends BaseApplication {

    @Inject
    Retrofit retrofit;
    @Inject
    @Named(BaseApiServiceModule.WITHOUT_CERTIFICATE)
    Retrofit debugRetrofit;

    private static DearApplication application;
    //标识测试环境
    public static final String DEBUG = "debug";
    //标识正式环境
    public static final String RELEASE = "release";

    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public static DearApplication getInstance() {
        return application;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        WindowManager winManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        winManager.getDefaultDisplay().getMetrics(displayMetrics);

        BuglyUtil.initBugly(this,BuildConfig.BUILD_TYPE,BuildConfig.VERSION_NAME,
                BuildConfig.APPLICATION_ID,BuildConfig.BUGLY_APP_ID,R.mipmap.updata_bg,
                R.layout.dialog_version_update,MainActivity.class);

//        //初始化萤石云平台
//        EZOpenSDK.showSDKLog(true);
//        EZOpenSDK.enableP2P(false);
//        EZOpenSDK.initLib(this, EzApiService.APP_KEY);
//        //保证萤石云口令有效可用。
//        EzNetManager.getInstance().makeSureAccessTokenValid();
    }

    /**
     * @return Retrofit
     * @author : tl
     * @description :  根据开发环境获取retrofit
     */
    public Retrofit getRetrofit() {
        if (DEBUG.equals(BuildConfig.BUILD_TYPE)) {
            return debugRetrofit;
        } else {
            return retrofit;
        }
    }

    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    /**
     * 开启后台服务器 (采用IntentService服务形式加载)
     *
     * @param action (动作)
     * @param bundle (绑定数据)
     */
    public void startBackService(String action, Bundle bundle) {
        Intent backService = new Intent(this, BackService.class);
        backService.setAction(action);
        if (bundle != null) {
            backService.putExtras(bundle);
        }
        startService(backService);
    }

}
