package com.guyuan.dear.mine.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.mine.bean.PrivacyPolicyDataBean;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
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
    private MutableLiveData<Integer> editUserPwEvent = new MutableLiveData<>();//修改用户密码
    private MutableLiveData<List<UploadBean>> uploadImageEvent = new MutableLiveData<>();//上传图片
    private MutableLiveData<Integer> feedBackEvent = new MutableLiveData<>();//上传留言
    private MutableLiveData<Integer> userAvatarEvent = new MutableLiveData<>();//提交头像url
    private MutableLiveData<PrivacyPolicyDataBean> privacyDataEvent = new MutableLiveData<>();//隐私内容

    @ViewModelInject
    public MineViewModel(MineRepository mineRepository) {
        this.repository = mineRepository;
    }

    public MutableLiveData<Integer> getEditUserPwEvent() {
        return editUserPwEvent;

    }

    public MutableLiveData<List<UploadBean>> getUploadImageEvent() {
        return uploadImageEvent;

    }

    public MutableLiveData<Integer> getFeedBackEvent() {
        return feedBackEvent;

    }

    public MutableLiveData<Integer> getUserAvatarEvent() {
        return userAvatarEvent;

    }

    public MutableLiveData<PrivacyPolicyDataBean> getPrivacyDataEvent() {
        return privacyDataEvent;
    }

    /**
     * 修改密码
     *
     * @param password    旧密码
     * @param newPassword 新密码
     */
    public void editUserPassWord(String password, String newPassword) {

        Disposable disposable = RxJavaHelper.build(this, repository.editUserPassWord(password, newPassword))
                .getHelper().flow(editUserPwEvent);
        addSubscription(disposable);
    }

    /**
     * 获取隐私内容富文本
     */
    public void getPrivacyPolicyData() {

        Disposable disposable = RxJavaHelper.build(this, repository.getPrivacyPolicyData())
                .getHelper().flow(privacyDataEvent);
        addSubscription(disposable);
    }

    /**
     * 上传图片
     *
     * @param map 图片信息
     */
    public void uploadPic(@PartMap Map<String, RequestBody> map) {

        Disposable disposable = RxJavaHelper.build(this, repository.uploadPic(map))
                .getHelper().flow(uploadImageEvent);
        addSubscription(disposable);
    }

    /**
     * 保存意见反馈表
     *
     * @param body 反馈信息
     */
    public void postFeedBack(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postFeedBack(body))
                .getHelper().flow(feedBackEvent);
        addSubscription(disposable);
    }

    /**
     * 修改用户头像
     *
     * @param body 提交头像url
     */
    public void postUserAvatar(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postUserAvatar(body))
                .getHelper().flow(userAvatarEvent);
        addSubscription(disposable);
    }
}
