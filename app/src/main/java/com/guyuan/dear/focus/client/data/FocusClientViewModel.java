package com.guyuan.dear.focus.client.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.data.SingleLiveEvent;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientViewModel extends BaseViewModel {

    private FocusClientRepository repository;
    /**
     * 客户列表
     */
    private MutableLiveData<RefreshBean<ClientCompanyBean>> clientListEvent = new MutableLiveData<>();
    /**
     * 基础信息
     */
    private MutableLiveData<ClientCompanyBean> clientBasicEvent = new MutableLiveData<>();
    /**
     * 基础信息
     */
    private MutableLiveData<RefreshBean<CommentsBean>> followListEvent = new MutableLiveData<>();


    @ViewModelInject
    public FocusClientViewModel(FocusClientRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }



    public MutableLiveData<RefreshBean<ClientCompanyBean>> getClientListEvent() {
        return clientListEvent;

    }


    public MutableLiveData<ClientCompanyBean> getClientBasicEvent() {
        return clientBasicEvent;

    }

    public MutableLiveData<RefreshBean<CommentsBean>> getFollowListEvent() {
        return followListEvent;

    }


    /**
     * 获取用户列表
     *
     * @param body 列表参数
     */
    public void getClientList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientList(body))
                .getHelper().flow(clientListEvent);
        addSubscription(disposable);
    }

    /**
     * 根据客户id查看详情
     *
     * @param id 客户id
     */
    public void getClientBasicInfo(int id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientBasicInfo(id)).
                getHelper().flow(clientBasicEvent);
        addSubscription(disposable);
    }

    /**
     * 根据客户id查询跟进
     *
     * @param body 列表参数
     */
    public void getFollowCommentList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getFollowCommentList(body))
                .getHelper().flow(followListEvent);
        addSubscription(disposable);
    }


}
