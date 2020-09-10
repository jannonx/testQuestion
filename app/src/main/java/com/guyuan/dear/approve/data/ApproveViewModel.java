package com.guyuan.dear.approve.data;

import android.os.Bundle;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Observer;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.data.SingleLiveEvent;
import com.google.gson.Gson;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.data.LoginBody;
import com.guyuan.dear.login.data.LoginRepository;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
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
    private SingleLiveEvent<RefreshBean<ApprovalData>> applyNotApproveEvent;//回调没有审批数据
    private SingleLiveEvent<RefreshBean<ApprovalData>> applyYetApproveEvent;//回调已经审批数据

    @ViewModelInject
    public ApproveViewModel(ApproveRepository approveRepository) {
        super(DearApplication.getInstance());
        this.approveRepository = approveRepository;
    }


    public SingleLiveEvent<RefreshBean<ApprovalData>> getApplyNotApproveEvent() {
        applyNotApproveEvent = createLiveData(applyNotApproveEvent);
        return applyNotApproveEvent;

    }

    public SingleLiveEvent<RefreshBean<ApprovalData>> getApplyYetApproveEvent() {
        applyYetApproveEvent = createLiveData(applyYetApproveEvent);
        return applyYetApproveEvent;
    }

    public void getApplyNotApproveList(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, approveRepository.getApplyNotApproveList(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        RefreshBean<ApprovalData> bean = (RefreshBean<ApprovalData>) o;
                        applyNotApproveEvent.postValue(bean);
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

    public void getApplyYetApproveList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, approveRepository.getApplyYetApproveList(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        RefreshBean<ApprovalData> bean = (RefreshBean<ApprovalData>) o;
                        applyYetApproveEvent.postValue(bean);
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
}
