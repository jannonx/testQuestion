package com.guyuan.dear.analyse.operate.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import retrofit2.http.PartMap;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateViewModel extends BaseViewModel {
    private OperateRepository repository;

    @ViewModelInject
    public OperateViewModel(OperateRepository focusClientRepository) {
        this.repository = focusClientRepository;
    }

    private MutableLiveData<RefreshBean<AfterSaleBean>> afterSaleListEvent = new MutableLiveData<>();
    private MutableLiveData<AfterSaleBean> afterSaleDetailEvent = new MutableLiveData<>();
    private MutableLiveData<List<AfterSaleStatusBean>> afterSaleStatusEvent = new MutableLiveData<>();
    private MutableLiveData<Integer> postAnswerInfoEvent = new MutableLiveData<>();
    private MutableLiveData<List<UploadBean>> uploadImageEvent = new MutableLiveData<>();//上传图片
    private MutableLiveData<Integer> postAcceptInfoEvent = new MutableLiveData<>();
    /**
     * 获取列表
     *
     * @param body 参数体
     * @return
     */
    public void getAfterSaleList(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.getAfterSaleList(body))
                .getHelper().flow(afterSaleListEvent);
        addSubscription(disposable);
    }

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return
     */
    public void getAfterSaleDetail(long id) {

        Disposable disposable = RxJavaHelper.build(this, repository.getAfterSaleDetail(id))
                .getHelper().flow(afterSaleDetailEvent);
        addSubscription(disposable);
    }

    /**
     * 工程现场-意见回复-异步查询意见回复集
     */
    public void getAfterSaleStatusList(long id, int type) {

        Disposable disposable = RxJavaHelper.build(this, repository.getAfterSaleStatusList(id, type))
                .getHelper().flow(afterSaleStatusEvent);
        addSubscription(disposable);
    }
    /**
     * 验收成功/失败-改变状态
     */
    public void postAcceptInfo(int status) {

        Disposable disposable = RxJavaHelper.build(this, repository.postAcceptInfo(status))
                .getHelper().flow(postAcceptInfoEvent);
        addSubscription(disposable);
    }

    /**
     * 意见回复-回复操作
     *
     * @param body
     * @return
     */
    public void postAnswerInfo(RequestBody body) {

        Disposable disposable = RxJavaHelper.build(this, repository.postAnswerInfo(body))
                .getHelper().flow(postAnswerInfoEvent);
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

    public MutableLiveData<RefreshBean<AfterSaleBean>> getAfterSaleListEvent() {
        return afterSaleListEvent;
    }

    public void setAfterSaleListEvent(MutableLiveData<RefreshBean<AfterSaleBean>> afterSaleListEvent) {
        this.afterSaleListEvent = afterSaleListEvent;
    }

    public MutableLiveData<AfterSaleBean> getAfterSaleDetailEvent() {
        return afterSaleDetailEvent;
    }

    public void setAfterSaleDetailEvent(MutableLiveData<AfterSaleBean> afterSaleDetailEvent) {
        this.afterSaleDetailEvent = afterSaleDetailEvent;
    }

    public MutableLiveData<List<AfterSaleStatusBean>> getAfterSaleStatusEvent() {
        return afterSaleStatusEvent;
    }

    public void setAfterSaleStatusEvent(MutableLiveData<List<AfterSaleStatusBean>> afterSaleStatusEvent) {
        this.afterSaleStatusEvent = afterSaleStatusEvent;
    }

    public MutableLiveData<Integer> getPostAnswerInfoEvent() {
        return postAnswerInfoEvent;
    }

    public void setPostAnswerInfoEvent(MutableLiveData<Integer> postAnswerInfoEvent) {
        this.postAnswerInfoEvent = postAnswerInfoEvent;
    }

    public MutableLiveData<List<UploadBean>> getUploadImageEvent() {
        return uploadImageEvent;
    }

    public void setUploadImageEvent(MutableLiveData<List<UploadBean>> uploadImageEvent) {
        this.uploadImageEvent = uploadImageEvent;
    }
}
