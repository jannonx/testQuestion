package com.guyuan.dear.work.client.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.ErrorResultBean;
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
public class WorkClientViewModel extends BaseViewModel {
    private WorkClientRepository repository;

    private MutableLiveData<RefreshBean<ClientCompanyBean>> clientListEvent=new MutableLiveData<>();//客户列表
    private MutableLiveData<RefreshBean<ClientCompanyBean>> myClientListEvent=new MutableLiveData<>() ;//我的客户列表
    private MutableLiveData<ClientCompanyBean> clientBasicEvent=new MutableLiveData<>();//基础信息
    private MutableLiveData<RefreshBean<CommentsBean>> followListEvent=new MutableLiveData<>();//跟进评论列表
    private MutableLiveData<Integer> followClientEvent=new MutableLiveData<>();//填写客户跟进评论
    private MutableLiveData<Integer> followUserEvent=new MutableLiveData<>();//填写用户跟进评论

    @ViewModelInject
    public WorkClientViewModel(WorkClientRepository focusAfterSaleRepository) {
        this.repository = focusAfterSaleRepository;
    }



    public MutableLiveData<RefreshBean<ClientCompanyBean>> getClientListEvent() {
        return clientListEvent;

    }
    public MutableLiveData<RefreshBean<ClientCompanyBean>> getMyClientListEvent() {
        return myClientListEvent;

    }

    public MutableLiveData<ClientCompanyBean> getClientBasicEvent() {
        return clientBasicEvent;

    }

    public MutableLiveData<RefreshBean<CommentsBean>> getFollowListEvent() {
        return followListEvent;

    }

    public MutableLiveData<Integer> getFollowClientEvent() {
        return followClientEvent;

    }
    public MutableLiveData<Integer> getFollowUserEvent() {
        return followUserEvent;

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
     * 获取我的客户列表
     *
     * @param body 列表参数
     */
    public void getMyClientList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getMyClientList(body))
                .getHelper().flow(myClientListEvent);
        addSubscription(disposable);
    }
    /**
     * 根据客户id查看详情
     *
     * @param id 客户id
     */
    public void getClientBasicInfo(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientBasicInfo(id))
                .getHelper().flow(clientBasicEvent);
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

    /**
     * 填写客户跟进
     *
     * @param content    评价内容
     * @param customerId 客户id
     */
    public void postClientFollowUp(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postClientFollowUp(body))
                .getHelper().flow(followClientEvent);
        addSubscription(disposable);
    }


    /**
     * 填写用户跟进
     *
     * @param content    评价内容
     * @param followId 客户id
     */
    public void postUserFollowUp(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postUserFollowUp(body))
                .getHelper().flow(followUserEvent);
        addSubscription(disposable);
    }


}
