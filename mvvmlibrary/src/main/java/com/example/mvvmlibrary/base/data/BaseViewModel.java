package com.example.mvvmlibrary.base.data;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.mvvmlibrary.data.SingleLiveEvent;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;

import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * created by tl
 * created at 2020/8/18
 */
public class BaseViewModel extends AndroidViewModel {

    private CompositeDisposable mCompositeSubscription;

    private SingleLiveEvent<String> showLoading;//显示加载框
    private SingleLiveEvent<Void> hideLoading;//隐藏加载框
    private SingleLiveEvent<String> tip; //提示语
    private SingleLiveEvent<Object> callBack;    //通用回调
    private SingleLiveEvent<Map<String, Object>> startActivityEvent;//activity跳转
    private SingleLiveEvent<Void> finishEvent;  //关闭当前activity
    private SingleLiveEvent<Void> onBackPressedEvent; //点击返回键

    /**
     * ！有坑注意！
     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
     * by Leo
     * @param application
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    public SingleLiveEvent<String> getShowLoading() {
        showLoading = createLiveData(showLoading);
        return showLoading;
    }

    public SingleLiveEvent<Void> getHideLoading() {
        hideLoading = createLiveData(hideLoading);
        return hideLoading;
    }

    public SingleLiveEvent<String> getTip() {
        tip = createLiveData(tip);
        return tip;
    }

    public SingleLiveEvent<Object> getCallBack() {
        callBack = createLiveData(callBack);
        return callBack;
    }

    public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
        startActivityEvent = createLiveData(startActivityEvent);
        return startActivityEvent;
    }

    public SingleLiveEvent<Void> getFinishEvent() {
        finishEvent = createLiveData(finishEvent);
        return finishEvent;
    }

    public SingleLiveEvent<Void> getOnBackPressedEvent() {
        onBackPressedEvent = createLiveData(onBackPressedEvent);
        return onBackPressedEvent;
    }

    protected  <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent<T>();
        }
        return liveData;
    }


    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        startActivityEvent.postValue(params);
    }

    /**
     * 关闭界面
     */
    public void finish() {
        finishEvent.call();
    }

    /**
     * 点击返回键
     */
    public void onBackPressed() {
        onBackPressedEvent.call();
    }


    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String BUNDLE = "BUNDLE";
    }

    protected void addSubscription(Disposable disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.size() > 0) {
            mCompositeSubscription.dispose();
        }
    }
}
