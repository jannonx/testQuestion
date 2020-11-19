package com.guyuan.dear.net;

import androidx.annotation.Nullable;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.SchedulersCompat;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.net.api.DearNetApiService;
import com.guyuan.dear.net.reqBean.ContractApplyBody;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetContractSumBean;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;

import java.util.List;

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
     *
     * @param pageIndex      分页页码，从1开始
     * @param pageSize       分页容量
     * @param lastUpdateTime 上次更新时间，服务器返回这个时间段后改动的人员清单。如果为空，则返回所有人员。
     * @param callback       回调
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

    /**
     * 获取所有客户清单
     *
     * @param callback
     * @return
     */
    public Disposable getClientList(NetCallback<BasePageResultBean<NetClientInfo>> callback) {
        BasePageReqBean body = new BasePageReqBean();
        body.setPageNum(0);
        body.setPageSize(-1);
        Observable<ResultBean<BasePageResultBean<NetClientInfo>>> observable = netApiService.getClientInfos(body);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 根据客户id获取客户合同列表，列表信息为基本信息。
     *
     * @param id
     * @param callback
     * @return
     */
    public Disposable getBaseContractListByClientId(long id, NetCallback<List<NetBaseContractInfo>> callback) {
        Observable<ResultBean<List<NetBaseContractInfo>>> observable = netApiService.getContractBaseInfosByClientId(id);
        return getDisposalAsync(observable, callback, null);
    }

    /**
     * 获取判断维度列表
     *
     * @param callback
     * @return
     */
    public Disposable getJudgeConditions(NetCallback<List<NetServerParam.JudgeCondition>> callback) {
        Observable<ResultBean<NetServerParam>> observable = netApiService.getNetServerParams();
        Mapper<NetServerParam, List<NetServerParam.JudgeCondition>> mapper =
                new Mapper<NetServerParam, List<NetServerParam.JudgeCondition>>() {
                    @Override
                    public List<NetServerParam.JudgeCondition> map(NetServerParam src) {
                        return src.getJudgeConditions();
                    }
                };
        return getDisposalAsync(observable, callback, mapper);
    }

    /**
     * 提交合同暂停/重启申请
     * @param bean 提交内容
     * @param callback
     * @return
     */
    public Disposable submitContractApply(ContractApplyBean bean, NetCallback<Integer> callback){
        ContractApplyBody body = new ContractApplyBody(bean);
        Observable<ResultBean<Integer>> observable = netApiService.submitContractApply(body);
        return getDisposalAsync(observable,callback,null);
    }

    /**
     * 查询合同概况
     * @param date 截至日期
     * @return
     */
    public Disposable getContractSumByDate(long date,NetCallback<ComContractsBean> callback){
        Observable<ResultBean<NetContractSumBean>> observable =
                netApiService.getContractSumByDate(CalenderUtils.getInstance().toSmartFactoryDateStringFormat(date));
        Mapper<NetContractSumBean, ComContractsBean> mapper = new Mapper<NetContractSumBean, ComContractsBean>() {
            @Override
            public ComContractsBean map(NetContractSumBean src) {
                return null;
            }
        };
        return getDisposalAsync(observable,callback,mapper);
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
     *
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
