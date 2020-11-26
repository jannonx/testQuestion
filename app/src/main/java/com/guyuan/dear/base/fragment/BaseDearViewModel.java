package com.guyuan.dear.base.fragment;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 18:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseDearViewModel extends BaseViewModel {
    protected abstract class BaseNetCallback<T> implements DearNetHelper.NetCallback<T> {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(T result) {
            isShowLoading.postValue(false);
            handleResult(result);
        }

        protected abstract void handleResult(T result);

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    }

    protected void showToast(String msg){
        ToastUtils.showShort(DearApplication.getInstance(),msg);
    }
}
