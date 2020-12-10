package com.guyuan.dear.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.rx.BaseSchedulersCompat;
import com.google.gson.Gson;
import com.guyuan.dear.base.api.SchedulersCompat;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.busbean.LoginBusBean;
import com.guyuan.dear.login.api.LoginApiService;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.data.LoginBody;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * .
 * 开启后台下载服务
 */
public class BackService extends IntentService {

    public static final String LOGIN = "service_action_login";
    public static final String NOT_HANDLE_CONTROL_MESSAGE = "service_not_handle_message";
    private DearApplication mApplication;

    public BackService() {
        super("BackService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = DearApplication.getInstance();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            final Bundle bundle = intent.getExtras();
            if (LOGIN.equals(action)) {
                LoginApiService loginApiService =
                        mApplication.getRetrofit().create(LoginApiService.class);
                String name = (String) mApplication.getCacheData(ConstantValue.KEY_USER_NAME, "");
                String pwd = (String) mApplication.getCacheData(ConstantValue.KEY_USER_PW, "");
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    LoginBody loginBody = new LoginBody();
                    loginBody.setAccount(name);
                    loginBody.setPassword(pwd);
                    loginBody.setDeviceId("android");
                    String str = new Gson().toJson(loginBody);
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                            "charset=utf-8"), str);
                    Disposable disposable = loginApiService.getLoginInfo(body)
                            .compose(SchedulersCompat.getInstance().applyIoSchedulers())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    LoginBean loginBean = (LoginBean) o;
                                    LogUtils.showLog("loginBean="+loginBean.getUserInfo().getName());
                                    mApplication.saveCacheData(ConstantValue.USER_JSON_STRING,
                                            new Gson().toJson(loginBean));
                                    LoginBusBean busBean = new LoginBusBean();
                                    busBean.setSuccess(true);
                                    EventBus.getDefault().post(busBean);
                                }
                            }, new ErrorResultBean() {
                                @Override
                                protected void onError(ErrorResultBean.ErrorBean errorBean) {
                                    LoginBusBean busBean = new LoginBusBean();
                                    busBean.setSuccess(false);
                                    EventBus.getDefault().post(busBean);
                                }
                            });
                }
            }
        }
    }
}
