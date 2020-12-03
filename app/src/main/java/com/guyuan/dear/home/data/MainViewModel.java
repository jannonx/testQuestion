package com.guyuan.dear.home.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.home.api.MainApiService;
import com.guyuan.dear.message.data.bean.MessageListBean;
import com.guyuan.dear.message.data.bean.MessageUnreadBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MainViewModel extends BaseViewModel {
    private MainApiService apiService;
    private MutableLiveData<MessageUnreadBean> messageUnreadtMLD = new MutableLiveData<>();

    @ViewModelInject
    public MainViewModel(MainRepository repository) {
        this.apiService = repository.getApiService();
    }

    public MutableLiveData<MessageUnreadBean> getMessageListMLD() {
        return messageUnreadtMLD;
    }

    public void getLastUnReadMessage(int msgType) {

        //间隔获取消息
        Disposable disposable = Observable.interval(0, CommonUtils.getMessageUpdateTime(), TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                RxJavaHelper.build(MainViewModel.this, apiService.getLastUnReadMessage(msgType))
                        .showLoading(false)
                        .getHelper().flow(messageUnreadtMLD);
            }
        });

    }
}