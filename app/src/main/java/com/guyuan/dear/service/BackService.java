package com.guyuan.dear.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.httplibrary.bean.ErrorResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.google.gson.Gson;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.base.api.SchedulersCompat;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.bean.ContractStatusBean;
import com.guyuan.dear.busbean.LoginBusBean;
import com.guyuan.dear.busbean.MessageBusBean;
import com.guyuan.dear.busbean.MessageUnreadBusBean;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.login.api.LoginApiService;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.login.data.bean.LoginBody;
import com.guyuan.dear.message.api.MessageApiService;
import com.guyuan.dear.message.data.bean.MessageUnreadBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * .
 * 开启后台下载服务
 */
public class BackService extends IntentService {

    public static final String LOGIN = "service_action_login";
    public static final String UNREAD_WORK = "service_unread_message_work";
    public static final String UNREAD_OFFICE = "service_unread_message_office";
    public static final String UNREAD_CONTROL_MESSAGE = "service_control_message";
    public static final String NOT_HANDLE_CONTROL_MESSAGE = "service_not_handle_message";
    public static final String CONTRACT_STATUS = "service_contract_status";
    public static final String CONTRACT_PARAMETER = "contractParameter";
    public static final String CONTRACT_PARAMETER_TYPE = "contract_parameter_type";
    private DearApplication mApplication;

    public BackService() {
        super("BackService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = DearApplication.getInstance();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            final Bundle bundle = intent.getExtras();
            if (LOGIN.equals(action)) {//自动登录
                LoginApiService loginApiService =
                        mApplication.getRetrofit().create(LoginApiService.class);
                String name = (String) mApplication.getCacheData(ConstantValue.KEY_USER_NAME, "");
                String pwd = (String) mApplication.getCacheData(ConstantValue.KEY_USER_PW, "");
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    LoginBody loginBody = new LoginBody();
                    loginBody.setAccount(name);
                    loginBody.setPassword(pwd);
                    loginBody.setDeviceId("android");
                    String str = new Gson().toJson(loginBody);
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                            "charset=utf-8"), str);
                    Disposable disposable = loginApiService.getLoginInfo(body)
                            .compose(SchedulersCompat.getInstance().applyIoSchedulers())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    LoginBean loginBean = (LoginBean) o;
                                    LogUtils.showLog("loginBean=" + loginBean.getUserInfo().getName());
                                    mApplication.saveCacheData(ConstantValue.USER_JSON_STRING,
                                            new Gson().toJson(loginBean));
                                    LoginBusBean busBean = new LoginBusBean();
                                    busBean.setSuccess(true);
                                    EventBus.getDefault().post(busBean);
                                }
                            }, new ErrorResultBean() {
                                @Override
                                protected void onError(ErrorResultBean.ErrorBean errorBean) {
                                    LoginBusBean busBean = new LoginBusBean();
                                    busBean.setSuccess(false);
                                    EventBus.getDefault().post(busBean);
                                }
                            });
                }
            } else if (UNREAD_WORK.equals(action)) {//我的工作未读消息
                Disposable disposable = mApplication
                        .getRetrofit().create(MessageApiService.class).getUnReadMessageNumber(3)
                        .compose(SchedulersCompat.getInstance().applyIoNoMainSchedulers())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                MessageUnreadBean unreadBusBean = (MessageUnreadBean) o;
                                MessageUnreadBusBean messageUnreadBusBean = new MessageUnreadBusBean();
                                messageUnreadBusBean.setMessageType(MessageBusBean.WORK);
                                messageUnreadBusBean.setUnreadNumber(unreadBusBean.getNumber());
                                messageUnreadBusBean.setMessageBeanList(unreadBusBean.getNewMessage());
                                EventBus.getDefault().post(messageUnreadBusBean);
                            }
                        }, new ErrorResultBean() {
                            @Override
                            protected void onError(ErrorResultBean.ErrorBean errorBean) {

                            }
                        });
            } else if (UNREAD_OFFICE.equals(action)) {//掌上办公未读消息
                Disposable disposable =
                        mApplication.getRetrofit().create(MessageApiService.class).getUnReadMessageNumber(2)
                                .compose(SchedulersCompat.getInstance().applyIoNoMainSchedulers())
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Exception {
                                        MessageUnreadBean unreadBusBean = (MessageUnreadBean) o;
                                        MessageUnreadBusBean messageUnreadBusBean = new MessageUnreadBusBean();
                                        messageUnreadBusBean.setMessageType(MessageBusBean.OFFICE);
                                        messageUnreadBusBean.setUnreadNumber(unreadBusBean.getNumber());
                                        messageUnreadBusBean.setMessageBeanList(unreadBusBean.getNewMessage());
                                        EventBus.getDefault().post(messageUnreadBusBean);
                                    }
                                }, new ErrorResultBean() {
                                    @Override
                                    protected void onError(ErrorResultBean.ErrorBean errorBean) {

                                    }
                                });
            } else if (NOT_HANDLE_CONTROL_MESSAGE.equals(action)) {//智能管控未处理消息
                Disposable disposable = mApplication
                        .getRetrofit().create(MessageApiService.class).getUnreadControlMessage(1, 30)
                        .compose(SchedulersCompat.getInstance().applyIoNoMainSchedulers())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                List<MessageBean> messageBeans = (List<MessageBean>) o;
                                MessageUnreadBusBean messageUnreadBusBean = new MessageUnreadBusBean();
                                messageUnreadBusBean.setMessageType(MessageBusBean.SMART_CONTROL_NOT_HANDLE);
                                messageUnreadBusBean.setMessageBeanList(messageBeans);
                                EventBus.getDefault().post(messageUnreadBusBean);
                            }
                        }, new ErrorResultBean() {
                            @Override
                            protected void onError(ErrorResultBean.ErrorBean errorBean) {

                            }
                        });
            } else if (UNREAD_CONTROL_MESSAGE.equals(action)) {//智能管控未读消息
                Disposable disposable =
                        mApplication.getRetrofit().create(MessageApiService.class).getUnReadMessageNumber(1)
                                .compose(SchedulersCompat.getInstance().applyIoNoMainSchedulers())
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Exception {
                                        MessageUnreadBean unreadBusBean = (MessageUnreadBean) o;
                                        MessageUnreadBusBean messageUnreadBusBean = new MessageUnreadBusBean();
                                        messageUnreadBusBean.setMessageType(MessageBusBean.SMART_CONTROL_UNREAD);
                                        messageUnreadBusBean.setUnreadNumber(unreadBusBean.getNumber());
                                        messageUnreadBusBean.setMessageBeanList(unreadBusBean.getNewMessage());
                                        EventBus.getDefault().post(messageUnreadBusBean);
                                    }
                                }, new ErrorResultBean() {
                                    @Override
                                    protected void onError(ErrorResultBean.ErrorBean errorBean) {

                                    }
                                });
            } else if (CONTRACT_STATUS.equals(action)) {
                Observable<ResultBean<ContractStatusBean>> observable = null;
                if (bundle != null) {
                    String contractParameterType = bundle.getString(CONTRACT_PARAMETER_TYPE);
                    String contractParameter = bundle.getString(CONTRACT_PARAMETER);
                    switch (contractParameterType) {
                        case BaseApiService.ID:
                            observable = mApplication.getRetrofit().create(BaseApiService.class)
                                    .getContractStatusByID(Integer.valueOf(contractParameter));
                            break;

                        case BaseApiService.CONTRACT_ID:
                            observable = mApplication.getRetrofit().create(BaseApiService.class)
                                    .getContractStatusByContractID(Integer.valueOf(contractParameter));
                            break;
                        case BaseApiService.CONTRACT_NUMBER:
                            observable = mApplication.getRetrofit().create(BaseApiService.class)
                                    .getContractStatusByContractNumber(contractParameter);
                            break;
                        case BaseApiService.FLAG:
                            observable = mApplication.getRetrofit().create(BaseApiService.class)
                                    .getContractStatusByFlag(Integer.valueOf(contractParameter));
                            break;
                        case BaseApiService.PROJECT_ID:
                            observable = mApplication.getRetrofit().create(BaseApiService.class)
                                    .getContractStatusByProjectID(Integer.valueOf(contractParameter));
                            break;
                    }

                    if (observable != null) {
                        Disposable disposable = observable.compose(SchedulersCompat.getInstance().applyIoNoMainSchedulers())
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Exception {
                                        ContractStatusBean contractStatusBean = (ContractStatusBean) o;
                                        int stopStatus = contractStatusBean.getStopStatus();
                                        //0.正常 1.暂停 2.被激活 3审批中
                                        if (stopStatus == 1) {
                                            EventBus.getDefault().post(contractStatusBean);
                                        }
                                    }
                                }, new ErrorResultBean() {
                                    @Override
                                    protected void onError(ErrorResultBean.ErrorBean errorBean) {

                                    }
                                });
                    }

                }

            }
        }
    }
}
