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
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientViewModel extends BaseViewModel {

    private FocusClientRepository repository;

    private MutableLiveData<RefreshBean<ClientCompanyBean>> clientListByNameEvent = new MutableLiveData<>();//模糊查询客户列表
    private MutableLiveData<RefreshBean<ClientCompanyBean>> clientListEvent = new MutableLiveData<>();//客户列表
    private MutableLiveData<ClientCompanyBean> clientBasicEvent = new MutableLiveData<>();//基础信息
    private MutableLiveData<RefreshBean<CommentsBean>> followListEvent = new MutableLiveData<>();//跟进评论

    private MutableLiveData<Integer> followUserEvent = new MutableLiveData<>();//填写用户跟进评价

    @ViewModelInject
    public FocusClientViewModel(FocusClientRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    public MutableLiveData<RefreshBean<ClientCompanyBean>> getClientListByNameEvent() {
        return clientListByNameEvent;

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


    public MutableLiveData<Integer> getFollowUserEvent() {
        return followUserEvent;

    }

    /**
     * 根据名称模糊查询客户列表
     *
     * @param name 客户名称
     */
    public void getClientListByName(String name) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientListByName(name))
                .getHelper().flow(clientListByNameEvent);
        addSubscription(disposable);
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


    /**
     * 填写用户跟进评价
     *
     * @param content  评价内容
     * @param followId 跟进id
     */
    public void postCommentFollowUp(long followId, String content) {

        Disposable disposable = RxJavaHelper.build(this, repository.postCommentFollowUp(followId, content))
               .getHelper().flow(followUserEvent);
        addSubscription(disposable);
    }
}
