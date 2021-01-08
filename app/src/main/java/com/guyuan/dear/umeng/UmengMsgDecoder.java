package com.guyuan.dear.umeng;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.example.mvvmlibrary.util.ActivityUtils;
import com.example.mvvmlibrary.util.SharedPreferencesUtils;
import com.example.mvvmlibrary.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.busbean.MessagePushBusBean;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.login.ui.LoginActivity;
import com.guyuan.dear.umeng.beans.PushLogout;
import com.guyuan.dear.umeng.beans.UmengMessageWrapper;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.umeng.commonsdk.debug.E;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/31 18:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UmengMsgDecoder {
    private static UmengMsgDecoder decoder;
    private HandlerThread handlerThread;
    private Handler handler;

    private UmengMsgDecoder() {
        handlerThread = new HandlerThread("UmengMsgDecoderThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);
    }

    public static UmengMsgDecoder getInstance() {
        if (decoder == null) {
            synchronized (UmengMsgDecoder.class) {
                decoder = new UmengMsgDecoder();
            }
        }
        return decoder;
    }

    public void decodeMsg(String customMsg) {
        try {
            UmengMessageWrapper wrapper = new Gson().fromJson(customMsg, UmengMessageWrapper.class);
            int msgType = wrapper.getMsgType();
            switch (msgType) {
                case UmengMessageWrapper.MSG_TYPE_PUSH_LOG_OUT:
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handlePushLogout(wrapper.getJson());
                        }
                    });
                    break;
                case UmengMessageWrapper.MSG_TYPE_PUSH_MSG_CENTER:
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleMsgCenter(wrapper.getJson());
                        }
                    });
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LogUtils.showLog(e.getMessage());
        }
    }

    private void handleMsgCenter(String json) {
        MessageBean msg = null;
        try {
            msg = new Gson().fromJson(json, MessageBean.class);
            int label = msg.getLabel();
            MessagePushBusBean pushBusBean = new MessagePushBusBean();
            pushBusBean.setMessageType(label);
            pushBusBean.setMessageBean(msg);
            EventBus.getDefault().post(pushBusBean);
        } catch (JsonParseException e) {
            LogUtils.showLog(e.getMessage());
        }
    }

    private void handlePushLogout(String json) {
        try {
            PushLogout pushLogout = new Gson().fromJson(json, PushLogout.class);
            long userId = pushLogout.getUserId();
            String accessToken = pushLogout.getAccessToken();
            if (CommonUtils.getCurrentUserId() == userId) {
                if (!CommonUtils.getLoginInfo().getToken().equals(accessToken)) {
                    ToastUtils.showToast(DearApplication.getInstance(), "您已在别的手机登录。");
//                    CommonUtils.logout(DearApplication.getInstance());
                    //要加上FLAG_ACTIVITY_NEW_TASK才能正常启动activity
                    LoginBean loginInfo = CommonUtils.getLoginInfo();
                    if (loginInfo != null) {
                        UmengAliasManager.getInstance().unregisterAlias(CommonUtils.getCurrentUserId());
                    }
                    SharedPreferencesUtils.removeData(DearApplication.getInstance(), ConstantValue.KEY_USER_PW);
                    SharedPreferencesUtils.removeData(DearApplication.getInstance(), ConstantValue.USER_JSON_STRING);
                    ActivityUtils.removeAllActivity();
                    Intent starter = new Intent(DearApplication.getInstance(), LoginActivity.class);
                    starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DearApplication.getInstance().startActivity(starter);
                }
            }
        } catch (Exception e) {
            LogUtils.showLog(e.getMessage());
        }

    }
}
