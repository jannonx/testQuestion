package com.guyuan.dear.focus.client.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.data.SingleLiveEvent;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.approve.data.ApproveRepository;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.client.api.FocusClientApiService;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.security.api.FocusSecurityApiService;
import com.guyuan.dear.focus.security.data.FocusSecurityRepository;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientViewModel extends BaseViewModel {

    private FocusClientRepository repository;

    private SingleLiveEvent<ResultBean<ClientCompanyBean>> clientListEvent;//客户列表
    @ViewModelInject
    public FocusClientViewModel(FocusClientRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }
    public SingleLiveEvent<ResultBean<ClientCompanyBean>> getClientListEvent() {
        clientListEvent = createLiveData(clientListEvent);
        return clientListEvent;

    }

    /**
     * 获取用户列表
     *
     * @param body 列表参数
     */
    public void getClientList(RequestBody body) {
//        Disposable disposable = RxJavaHelper.build(this,
//                apiService.getClientList(body)).getHelper().flow();
//        addSubscription(disposable);

        Disposable disposable = RxJavaHelper.build(this, repository.getClientList(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<ClientCompanyBean> bean = (ResultBean<ClientCompanyBean>) o;
                        clientListEvent.postValue(bean);
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
