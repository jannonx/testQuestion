package com.guyuan.dear.net;

import androidx.annotation.Nullable;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.SchedulersCompat;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.net.api.DearNetApiService;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.utils.CalenderUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DearNetHelper {
    private static DearNetHelper instance;
    private final DearNetApiService netApiService;

    private DearNetHelper() {
        netApiService = DearApplication.getInstance().getRetrofit().create(DearNetApiService.class);
    }

    public static DearNetHelper getInstance() {
        if (instance == null) {
            synchronized (DearNetHelper.class) {
                if (instance == null) {
                    instance = new DearNetHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 获取所有人员
     * @param pageIndex 分页页码，从1开始
     * @param pageSize 分页容量
     * @param lastUpdateTime 上次更新时间，服务器返回这个时间段后改动的人员清单。如果为空，则返回所有人员。
     * @param callback 回调
     * @return
     */
    public Disposable getAllStaffs(int pageIndex, int pageSize, long lastUpdateTime, NetCallback<BasePageResultBean<NetStaffBean>> callback) {
        BasePageReqBean body = new BasePageReqBean();
        body.setPageNum(pageIndex);
        body.setPageSize(pageSize);
        body.setUpdateTime(CalenderUtils.getInstance().toSmartFactoryDateStringFormat(lastUpdateTime));
        Observable<ResultBean<BasePageResultBean<NetStaffBean>>> observable = netApiService.getAllStaffs(body);
        return getDisposal(observable, callback, null);
    }


    /*************************************底层方法分界线******************************************/
    public interface NetCallback<T> {
        void onStart(Disposable disposable);

        void onGetResult(T result);

        void onError(Throwable error);
    }

    public interface Mapper<FROM, TO> {
        TO map(FROM src);
    }

    /**
     * @param mapper         把Data类转换成ReturnType的适配器
     * @param <Response>     网络请求的返回类型，必须继承ResultBean
     * @param <ResponseData> 必须为Response的变量data代表的类，否则会报错
     * @param <ResultClass>  最终返回类型，可以是Data，也可以是经过Data转化过的其他类
     */
    private <Response extends ResultBean, ResponseData, ResultClass> Disposable getDisposalAsync(
            Observable<Response> observable, @Nullable NetCallback<ResultClass> callback,
            @Nullable Mapper<ResponseData, ResultClass> mapper) {

        return observable.compose(SchedulersCompat.applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(disposable);
                        }
                    }
                }).subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object obj) throws Exception {
                        if (callback == null) {
                            return;
                        }
                        if (mapper != null) {
                            ResponseData responseData = (ResponseData) obj;
                            callback.onGetResult(mapper.map(responseData));
                        } else {
                            ResultClass data = (ResultClass) obj;
                            callback.onGetResult(data);
                        }
                    }
                }, new ErrorResultBean() {
                    @Override
                    protected void onError(ErrorBean errorBean) {
                        if (callback != null) {
                            Throwable throwable = new Throwable(errorBean.getErrorResult());
                            callback.onError(throwable);
                        }

                    }
                });
    }

    /**
     * 注意：这个回调是在子线程中的，不能在回调中修改UI
     * @param mapper         把Data类转换成ReturnType的适配器
     * @param <Response>     网络请求的返回类型，必须继承ResultBean
     * @param <ResponseData> 必须为Response的变量data代表的类，否则会报错
     * @param <ResultClass>  最终返回类型，可以是Data，也可以是经过Data转化过的其他类
     */
    private <Response extends ResultBean, ResponseData, ResultClass> Disposable getDisposal(
            Observable<Response> observable, @Nullable NetCallback<ResultClass> callback,
            @Nullable Mapper<ResponseData, ResultClass> mapper) {

        return observable.compose(SchedulersCompat.applyIoSchedulers())
                .observeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(disposable);
                        }
                    }
                }).subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object obj) throws Exception {
                        if (callback == null) {
                            return;
                        }
                        if (mapper != null) {
                            ResponseData responseData = (ResponseData) obj;
                            callback.onGetResult(mapper.map(responseData));
                        } else {
                            ResultClass data = (ResultClass) obj;
                            callback.onGetResult(data);
                        }
                    }
                }, new ErrorResultBean() {
                    @Override
                    protected void onError(ErrorBean errorBean) {
                        if (callback != null) {
                            Throwable throwable = new Throwable(errorBean.getErrorResult());
                            callback.onError(throwable);
                        }

                    }
                });
    }


}