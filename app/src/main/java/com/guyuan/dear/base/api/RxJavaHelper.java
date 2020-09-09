package com.guyuan.dear.base.api;

import android.text.TextUtils;
import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.SchedulersCompat;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 15:54
 * @company : 固远（深圳）信息技术有限公司
 **/
public class RxJavaHelper<T> {

    private BaseViewModel viewModel;
    private Observable<ResultBean<T>> observable;
    private String preTip;
    private Consumer<Object> successConsumer;
    private ErrorResultBean failConsumer;

    public RxJavaHelper(BaseViewModel viewModel, Observable<ResultBean<T>> observable) {
        this.viewModel = viewModel;
        this.observable = observable;
    }

    public static <T>Builder<T> build(BaseViewModel viewModel, Observable<ResultBean<T>> observable) {
        return new Builder<T>(viewModel, observable);
    }

    public static class Builder<T> {
        private RxJavaHelper<T> helper;

        public Builder(BaseViewModel viewModel, Observable<ResultBean<T>> observable) {
            helper = new RxJavaHelper<T>(viewModel, observable);
        }

        public RxJavaHelper<T> getHelper() {
            return helper;
        }

        //设置加载提示
        public Builder<T> setPreTip(String preTip) {
            helper.preTip = preTip;
            return this;
        }

        //设置成功回调
        public Builder<T> success(Consumer<Object> success) {
            helper.successConsumer = success;
            return this;
        }

        //设置失败回调
        public Builder<T> fail(ErrorResultBean fail) {
            helper.failConsumer = fail;
            return this;
        }

    }


    //默认的接口回调处理
    public Disposable flow() {
        return observable.compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (TextUtils.isEmpty(preTip)) {
                            viewModel.getShowLoading().postValue("数据加载中...");
                        } else {
                            viewModel.getShowLoading().postValue(preTip);
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        viewModel.getHideLoading().call();
                    }
                })
                .subscribe(getSuccessConsumer(), getFailConsumer());
    }


    private Consumer<Object> getSuccessConsumer() {
        if (successConsumer == null) {
            return new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    viewModel.getCallBack().postValue(o);
                }
            };
        } else {
            return successConsumer;
        }
    }


    private ErrorResultBean getFailConsumer() {
        if (failConsumer == null) {
            return new ErrorResultBean() {
                @Override
                protected void onError(ErrorResultBean.ErrorBean errorBean) {
                    viewModel.getTip().postValue(errorBean.getErrorResult());
                }
            };
        } else {
            return failConsumer;
        }
    }
}
