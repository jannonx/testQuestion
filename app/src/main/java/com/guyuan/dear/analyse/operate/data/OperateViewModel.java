package com.guyuan.dear.analyse.operate.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.base.api.RxJavaHelper;

import io.reactivex.disposables.Disposable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateViewModel extends BaseViewModel {
    private OperateRepository repository;

    @ViewModelInject
    public OperateViewModel(OperateRepository operateRepository) {
        this.repository = operateRepository;
    }


    private MutableLiveData<OperateOverViewBean> overViewEvent = new MutableLiveData<>();

    /**
     * 工程现场-意见回复-异步查询意见回复集
     */
    public void getOperateOverViewData(String time) {

        Disposable disposable = RxJavaHelper.build(this, repository.getOperateOverViewData(time))
                .getHelper().flow(overViewEvent);
        addSubscription(disposable);
    }


    public MutableLiveData<OperateOverViewBean> getOverViewEvent() {
        return overViewEvent;
    }

    public void setOverViewEvent(MutableLiveData<OperateOverViewBean> overViewEvent) {
        this.overViewEvent = overViewEvent;
    }

    public OperateRepository getRepository() {
        return repository;
    }

    public void setRepository(OperateRepository repository) {
        this.repository = repository;
    }
}
