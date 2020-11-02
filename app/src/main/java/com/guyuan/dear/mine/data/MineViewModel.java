package com.guyuan.dear.mine.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.data.SingleLiveEvent;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleRepository;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;
import retrofit2.http.PartMap;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineViewModel extends BaseViewModel {
    private MineRepository repository;
    private SingleLiveEvent<ResultBean<Integer>> editUserPwEvent;//修改用户密码
    private SingleLiveEvent<ResultBean<List<UploadBean>>> uploadImageEvent;//上传图片
    private SingleLiveEvent<ResultBean<Integer>> feedBackEvent;//上传留言
    private SingleLiveEvent<ResultBean<Integer>> userAvatarEvent;//提交头像url

    @ViewModelInject
    public MineViewModel(MineRepository mineRepository) {
        this.repository = mineRepository;
    }

    public SingleLiveEvent<ResultBean<Integer>> getEditUserPwEvent() {
        editUserPwEvent = createLiveData(editUserPwEvent);
        return editUserPwEvent;

    }

    public SingleLiveEvent<ResultBean<List<UploadBean>>> getUploadImageEvent() {
        uploadImageEvent = createLiveData(uploadImageEvent);
        return uploadImageEvent;

    }

    public SingleLiveEvent<ResultBean<Integer>> getFeedBackEvent() {
        feedBackEvent = createLiveData(feedBackEvent);
        return feedBackEvent;

    }

    public SingleLiveEvent<ResultBean<Integer>> getUserAvatarEvent() {
        userAvatarEvent = createLiveData(userAvatarEvent);
        return userAvatarEvent;

    }

    /**
     * 修改密码
     *
     * @param password    旧密码
     * @param newPassword 新密码
     */
    public void editUserPassWord(String password, String newPassword) {

        Disposable disposable = RxJavaHelper.build(this, repository.editUserPassWord(password, newPassword))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<Integer> bean = (ResultBean<Integer>) o;
                        editUserPwEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 上传图片
     *
     * @param map 图片信息
     */
    public void uploadPic(@PartMap Map<String, RequestBody> map) {

        Disposable disposable = RxJavaHelper.build(this, repository.uploadPic(map))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<List<UploadBean>> bean = (ResultBean<List<UploadBean>>) o;
                        uploadImageEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 保存意见反馈表
     *
     * @param body 反馈信息
     */
    public void postFeedBack(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postFeedBack(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<Integer> bean = (ResultBean<Integer>) o;
                        feedBackEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }

    /**
     * 修改用户头像
     *
     * @param body 提交头像url
     */
    public void postUserAvatar(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postFeedBack(body))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ResultBean<Integer> bean = (ResultBean<Integer>) o;
                        userAvatarEvent.postValue(bean);
                    }
                }).getHelper().flow();
        addSubscription(disposable);
    }
}
