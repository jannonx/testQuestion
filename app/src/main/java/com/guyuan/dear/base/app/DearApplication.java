package com.guyuan.dear.base.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.mvvmlibrary.app.BaseApplication;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.ezCloud.net.EzApiService;
import com.guyuan.dear.ezCloud.utils.EzNetManager;
import com.guyuan.dear.service.BackService;
import com.guyuan.dear.utils.SharedPreferencesUtils;
import com.videogo.openapi.EZOpenSDK;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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
    Retrofit debugRetrofit;

    private static DearApplication application;
    //标识测试环境
    public static final String DEUBG = "debug";
    //标识正式环境
    public static final String RELEASE = "release";
    private SharedPreferencesUtils sharedPreferencesUtils;
    private DisplayMetrics displayMetrics=new DisplayMetrics();

    public static DearApplication getInstance() {
        return application;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
        WindowManager winManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        winManager.getDefaultDisplay().getMetrics(displayMetrics);

        //初始化萤石云平台
        EZOpenSDK.showSDKLog(true);
        EZOpenSDK.enableP2P(false);
        EZOpenSDK.initLib(this, EzApiService.APP_KEY);
        //保证萤石云口令有效可用。
        EzNetManager.getInstance().makeSureAccessTokenValid();
    }

    /**
     * @return Retrofit
     * @author : tl
     * @description :  根据开发环境获取retrofit
     */
    public Retrofit getRetrofit() {
        if (DEUBG.equals(BuildConfig.BUILD_TYPE)) {
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


    /**
     * 采用andriod本身数据格式缓存数据
     * 后期可以更改成其他缓存
     */
    public void saveCacheData(String key, Object data) {
        saveCacheData(SharedPreferencesUtils.SP_NAME, key, data);
    }

    private void saveCacheData(final String fileName, final String key, final Object defaultObject) {
        sharedPreferencesUtils.saveData(fileName, key, defaultObject);
    }


    public Map<String, Integer> getBuriedPointData(String userId) {
        return sharedPreferencesUtils.getBuriedPointData(SharedPreferencesUtils.SP_NAME, userId);
    }

    public void clearBuriedPointData(String userId) {
        sharedPreferencesUtils.clearBuriedPointData(SharedPreferencesUtils.SP_NAME, userId);
    }

    public Object getCacheData(String key, Object defaultObject) {
        return getCacheData(SharedPreferencesUtils.SP_NAME, key, defaultObject);
    }

    private Object getCacheData(final String fileName, final String key, final Object defaultObject) {
        return sharedPreferencesUtils.getData(fileName, key, defaultObject);
    }

    public void saveCacheListData(String key, final List<Map<String, String>> dataList) {
        saveCacheListData(SharedPreferencesUtils.SP_NAME, key, dataList);
    }

    private void saveCacheListData(final String fileName, final String key, final List<Map<String,
            String>> dataList) {
        sharedPreferencesUtils.saveListData(fileName, key, dataList);
    }

    public List<Map<String, String>> getCacheListData(final String key) {
        return getCacheListData(SharedPreferencesUtils.SP_NAME, key);
    }

    private List<Map<String, String>> getCacheListData(final String fileName, final String key) {
        return sharedPreferencesUtils.getListData(fileName, key);
    }

    public void removeListData(final String key) {
        sharedPreferencesUtils.removeListData(SharedPreferencesUtils.SP_NAME, key);
    }

    public void saveCacheStringListData(String key, final List<String> dataList) {
        sharedPreferencesUtils.saveStringListData(SharedPreferencesUtils.SP_NAME, key, dataList);
    }

    public List<String> getCacheStringListData(final String key) {
        return sharedPreferencesUtils.getStringListData(SharedPreferencesUtils.SP_NAME, key);
    }

    public void saveMapData(String key, Map<String, String> mapData) {
        sharedPreferencesUtils.saveMapData(SharedPreferencesUtils.SP_NAME, key, mapData);
    }

    public Map<String, String> getMapData(String key) {
        return sharedPreferencesUtils.getMapData(SharedPreferencesUtils.SP_NAME, key);
    }

    public void saveTreeMapData(String key, Map<String, String> mapData) {
        sharedPreferencesUtils.saveTreeMapData(SharedPreferencesUtils.SP_NAME, key, mapData);
    }

    public Map<String, String> getTreeMapData(String key) {
        return sharedPreferencesUtils.getTreeMapData(SharedPreferencesUtils.SP_NAME, key);
    }

}
