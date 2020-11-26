package com.guyuan.dear.office.clockIn.repo;

import android.text.TextUtils;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.utils.ConstantValue;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 14:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInRepo {
    private AMapLocationClient locationClient;

    public ClockInRepo() {
        initMapApi();
    }

    private void initMapApi() {
        //初始化高德地图
        locationClient = new AMapLocationClient(DearApplication.getInstance());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        option.setNeedAddress(true);
        //允许在模拟器上生成模拟位置
        option.setMockEnable(true);
        locationClient.setLocationOption(option);
    }

    public LoginBean getMyInfo() {
        String jString = (String) DearApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        if (!TextUtils.isEmpty(jString)) {
            return new Gson().fromJson(jString, LoginBean.class);
        }
        return null;
    }

    public Disposable getClockInConfig(DearNetHelper.NetCallback<NetClockInConfig> callback){
        return DearNetHelper.getInstance().getClockInConfig(callback);
    }

    /**
     * 启动高德地图位置更新
     *
     * @param listener
     */
    public void startPositioning(AMapLocationListener listener) {
        if (locationClient == null) {
            initMapApi();
        }
        locationClient.setLocationListener(listener);
        locationClient.startLocation();
    }

    /**
     * 关闭高德地图位置更新
     */
    public void stopPositioning() {
        locationClient.stopLocation();
        locationClient.onDestroy();
        locationClient = null;
    }

    public Disposable showCurrentTime(TimerInterface callback) {
        return Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callback.onTimeUpdate(System.currentTimeMillis());
                    }
                });
    }

    public String getMapError(int errorCode) {
        String error = "未知原因";
        switch (errorCode) {
            case 0:
                error = "定位成功。";
                break;
            case 1:
                error = "一些重要参数为空，如context";
                break;
            case 2:
                error = "定位失败，由于仅扫描到单个wifi，且没有基站信息。";
                break;
            case 3:
                error = "获取到的请求参数为空，可能获取过程中出现异常。";
                break;
            case 4:
                error = "请求服务器过程中的异常，多为网络情况差，链路不通导致";
                break;
            case 5:
                error = "请求被恶意劫持，定位结果解析失败。";
                break;
            case 6:
                error = "定位服务返回定位失败。";
                break;
            case 7:
                error = "KEY鉴权失败。";
                break;
            case 8:
                error = "Android exception常规错误";
                break;
            case 9:
                error = "定位初始化时出现异常。";
                break;
            case 10:
                error = "定位客户端启动失败。";
                break;
            case 11:
                error = "定位时的基站信息错误。";
                break;
            case 12:
                error = "缺少定位权限。";
                break;
            case 13:
                error = "定位失败，由于未获得WIFI列表和基站信息，且GPS当前不可用。";
                break;
            case 14:
                error = "GPS 定位失败，由于设备当前 GPS 状态差。";
                break;
            case 15:
                error = "定位结果被模拟导致定位失败";
                break;
            case 16:
                error = "当前POI检索条件、行政区划检索条件下，无可用地理围栏";
                break;
            case 18:
                error = "定位失败，由于手机WIFI功能被关闭同时设置为飞行模式";
                break;
            case 19:
                error = "定位失败，由于手机没插sim卡且WIFI功能被关闭";
                break;
            default:
                break;
        }
        return error;
    }

    public interface TimerInterface {
        void onTimeUpdate(long currentMills);
    }
}
