package com.guyuan.dear.focus.client.data;

import androidx.hilt.lifecycle.ViewModelInject;

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
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientViewModel extends BaseViewModel {

    private FocusClientRepository repository;

    private SingleLiveEvent<ResultBean<List<ClientCompanyBean>>> clientListByNameEvent;//模糊查询客户列表
    private SingleLiveEvent<ResultBean<List<ClientCompanyBean>>> clientListEvent;//客户列表
    private SingleLiveEvent<ResultBean<ClientCompanyBean>> clientBasicEvent;//基础信息
    private SingleLiveEvent<ResultBean<RefreshBean<CommentsBean>>> followListEvent;//跟进评论

    private SingleLiveEvent<ResultBean<Integer>> followUserEvent;//填写用户跟进评价
    @ViewModelInject
    public FocusClientViewModel(FocusClientRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    public SingleLiveEvent<ResultBean<List<ClientCompanyBean>>> getClientListByNameEvent() {
        clientListByNameEvent = createLiveData(clientListByNameEvent);
        return clientListByNameEvent;

    }

    public SingleLiveEvent<ResultBean<List<ClientCompanyBean>>> getClientListEvent() {
        clientListEvent = createLiveData(clientListEvent);
        return clientListEvent;

    }

    public SingleLiveEvent<ResultBean<ClientCompanyBean>> getClientBasicEvent() {
        clientBasicEvent = createLiveData(clientBasicEvent);
        return clientBasicEvent;

    }

    public SingleLiveEvent<ResultBean<RefreshBean<CommentsBean>>> getFollowListEvent() {
        followListEvent = createLiveData(followListEvent);
        return followListEvent;

    }


    public SingleLiveEvent<ResultBean<Integer>> getFollowUserEvent() {
        followUserEvent = createLiveData(followUserEvent);
        return followUserEvent;

    }
    /**
     * 根据名称模糊查询客户列表
     *
     * @param name 客户名称
     */
    public void getClientListByName(String name) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientListByName(name))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<List<ClientCompanyBean>> bean = (ResultBean<List<ClientCompanyBean>>) o;
                        clientListByNameEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 获取用户列表
     *
     * @param body 列表参数
     */
    public void getClientList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientList(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<List<ClientCompanyBean>> bean = (ResultBean<List<ClientCompanyBean>>) o;
                        clientListEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 根据客户id查看详情
     *
     * @param id 客户id
     */
    public void getClientBasicInfo(Long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getClientBasicInfo(id))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<ClientCompanyBean> bean = (ResultBean<ClientCompanyBean>) o;
                        clientBasicEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 根据客户id查询跟进
     *
     * @param body 列表参数
     */
    public void getFollowCommentList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getFollowCommentList(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<RefreshBean<CommentsBean>> bean = (ResultBean<RefreshBean<CommentsBean>>) o;
                        followListEvent.postValue(bean);
                    }
                }).getHelper().flow();
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
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<Integer> bean = (ResultBean<Integer>) o;
                        followUserEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }
}
