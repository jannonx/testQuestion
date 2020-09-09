package com.guyuan.dear.approve.data;

import android.os.Bundle;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.data.LoginBody;
import com.guyuan.dear.login.data.LoginRepository;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApproveViewModel extends BaseViewModel {

    public ApproveRepository approveRepository;
    private LoginBean loginBean;
    private String name;
    private String pwd;

    @ViewModelInject
    public ApproveViewModel(ApproveRepository approveRepository) {
        super(DearApplication.getInstance());
        this.approveRepository = approveRepository;
    }


}
