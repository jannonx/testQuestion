package com.guyuan.dear.office.clockIn.repo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.utils.ConstantValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
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
    private LocationClient bdLocClient;

    public ClockInRepo() {
        initBdMapApi();
    }

    private void initBdMapApi() {

        bdLocClient = new LocationClient(DearApplication.getInstance());
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        option.setEnableSimulateGps(true);
        bdLocClient.setLocOption(option);
    }

    public void startBdPositioning(BDAbstractLocationListener listener) {
        if (bdLocClient != null) {
            bdLocClient.registerLocationListener(listener);
            bdLocClient.start();
        }
    }

    public void stopBdPositioning(BDAbstractLocationListener listener) {
        if (bdLocClient != null) {
            bdLocClient.unRegisterLocationListener(listener);
            bdLocClient.stop();
        }
    }

    public LoginBean getMyInfo() {
        String jString = (String) DearApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        if (!TextUtils.isEmpty(jString)) {
            return new Gson().fromJson(jString, LoginBean.class);
        }
        return null;
    }

    public Disposable getClockInConfig(DearNetHelper.NetCallback<NetClockInConfig> callback) {
        return DearNetHelper.getInstance().getClockInConfig(callback);
    }


    public Disposable startTimer(TimerInterface callback) {
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

    public interface TimerInterface {
        void onTimeUpdate(long currentMills);
    }

    /**
     * 显示当前APP的SHA1码，检验定位SDK时用
     *
     * @param context
     * @return
     */
    public String showAppSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Disposable clockIn(int type, double lat, double lng, DearNetHelper.NetCallback<Boolean> callback) {
        return DearNetHelper.getInstance().clockIn(type, lat, lng, callback);
    }
}
