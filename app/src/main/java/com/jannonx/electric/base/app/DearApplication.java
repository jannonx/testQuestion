package com.jannonx.electric.base.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.httplibrary.BaseApiServiceModule;
import com.example.mvvmlibrary.app.BaseApplication;
import com.jannonx.electric.BuildConfig;
import com.jannonx.electric.service.BackService;
import com.jannonx.electric.service.BackTaskType;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.HiltAndroidApp;
import retrofit2.Retrofit;

/**
 * @author : Jannonx
 * @description : 项目application维护一些全局变量或者方法
 * @since: 2020/9/7
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

//        BuglyUtil.initBugly(this, BuildConfig.BUILD_TYPE, BuildConfig.VERSION_NAME,
//                BuildConfig.APPLICATION_ID, BuildConfig.BUGLY_APP_ID, R.mipmap.updata_bg,
//                R.layout.dialog_version_update, MainActivity.class);


        //初始化友盟推送
//        initUmengPush();
        //初始化百度地图
//        SDKInitializer.initialize(this);
    }

    private void initUmengPush() {

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
     * @param type   (类型)
     * @param bundle (绑定数据)
     */
    public void startBackService(BackTaskType type, Bundle bundle) {
        Intent backService = new Intent(this, BackService.class);
        backService.setAction(type.getDes());
        if (bundle != null) {
            backService.putExtras(bundle);
        }
        startService(backService);
    }

}
