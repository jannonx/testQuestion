package com.guyuan.dear.login.data;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;

import com.bumptech.glide.Glide;
import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.rx.SchedulersCompat;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * created by tl
 * created at 2020/8/25
 */

public class LoginViewModel extends BaseViewModel {

    public LoginRepository loginRepository;
    private LoginBean loginBean;
    private String name;
    private String pwd;

    @ViewModelInject
    public LoginViewModel(LoginRepository loginRepository) {
        super(DearApplication.getInstance());
        this.loginRepository = loginRepository;
    }


    public void checkLogin(String name, String pwd) {
        if (!name.equals("") && !pwd.equals("")) {
            this.name = name;
            this.pwd = pwd;
            LoginBody loginBody = new LoginBody();
            loginBody.setAccount(name);
            loginBody.setPassword(pwd);
            loginBody.setDeviceId("android");
            String str = new Gson().toJson(loginBody);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                    "charset=utf-8"), str);
            login(body);
        } else {
            getTip().setValue("请输入完整信息");
        }
    }


    public void login(RequestBody requestBody) {
        Disposable disposable = RxJavaHelper.build(this, loginRepository.getLoginData(requestBody))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        loginBean = (LoginBean) o;
                        getTip().setValue("登录成功");
                        saveLoginData(loginBean);
                        getCallBack().setValue(loginBean);
                    }
                })
                .fail(new ErrorResultBean() {
                    @Override
                    protected void onError(ErrorBean errorBean) {
                        getTip().setValue(errorBean.getErrorResult());
                        getCallBack().setValue(errorBean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    private void saveLoginData(LoginBean newUser) {
        List<LoginBean.AppMenusBean> menus = newUser.getAppMenus();
        if (menus != null && menus.size() > 0) {
            DearApplication.getInstance().saveCacheData(ConstantValue.KEY_USER_NAME, name);
            DearApplication.getInstance().saveCacheData(ConstantValue.KEY_USER_PW, pwd);
            DearApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING, new Gson().toJson(newUser));
            Bundle bundle=new Bundle();
            bundle.putInt(MainActivity.OPEN_TYPE,MainActivity.LOGIN);
            startActivity(MainActivity.class,bundle);
        } else {
            SharedPreferencesUtils.removeData(getApplication(), ConstantValue.KEY_USER_NAME);
            SharedPreferencesUtils.removeData(getApplication(), ConstantValue.KEY_USER_PW);
            SharedPreferencesUtils.removeData(getApplication(), ConstantValue.USER_JSON_STRING);
            getTip().setValue("用户角色未设置任何权限，请联系系统管理员！");
        }
    }
}
