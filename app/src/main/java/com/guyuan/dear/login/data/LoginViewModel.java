package com.guyuan.dear.login.data;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.login.data.bean.LoginBody;
import com.guyuan.dear.utils.ConstantValue;

import androidx.hilt.lifecycle.ViewModelInject;
import io.reactivex.disposables.Disposable;
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
        this.loginRepository = loginRepository;
    }


    public void checkLogin(String name, String pwd,Consumer success) {
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
            login(body,success);
        } else {
            getTip().setValue("请输入完整信息");
        }
    }

    public String getUserName() {
        String name = (String) DearApplication.getInstance().getCacheData(ConstantValue.KEY_USER_NAME, "");
        return name;
    }

    public void login(RequestBody requestBody,Consumer success) {
        Disposable disposable = RxJavaHelper.build(this, loginRepository.getLoginData(requestBody))
                .success(success).getHelper().flow();
        addSubscription(disposable);
    }

}
