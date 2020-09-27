package com.guyuan.dear.focus.security.data;

import androidx.hilt.lifecycle.ViewModelInject;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.security.api.FocusSecurityApiService;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 17:12
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityViewModel extends BaseViewModel {

    private FocusSecurityApiService apiService;

    @ViewModelInject
    public FocusSecurityViewModel(FocusSecurityRepository focusSecurityRepository) {
        apiService = focusSecurityRepository.getApiService();
    }

    //获取危险点数量
    public void getDangerPointNumber() {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getDangerPointNumber()).getHelper().flow();
        addSubscription(disposable);
    }

    //获取危险点分布
    public void getDangerPointProfile(Long id) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getDangerPointProfile(id)).getHelper().flow();
        addSubscription(disposable);
    }

    //获取危险点汇报列表
    public void getDangerPointReport(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getDangerPointReportList(body)).getHelper().flow();
        addSubscription(disposable);
    }

    //获取危险点报警记录
    public void getDangerPointHistory(int id) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getDangerPointHistory(id)).getHelper().flow();
        addSubscription(disposable);
    }

    //获取危险点类型列表
    public void getDangerPointTypeList(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this,
                apiService.getDangerPointTypeList(body)).getHelper().flow();
        addSubscription(disposable);
    }

}
