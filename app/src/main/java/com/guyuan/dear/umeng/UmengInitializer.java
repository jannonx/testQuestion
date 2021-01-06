package com.guyuan.dear.umeng;

import android.content.Context;
import android.util.Log;

import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.LogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/29 15:28
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UmengInitializer {
    private static final String TAG = "UmengInitializer";
    private PushAgent pushAgent;
    private static UmengInitializer umengInitializer;
    private static String deviceToken;

    public static UmengInitializer getInstance() {
        if (umengInitializer == null) {
            synchronized (UmengInitializer.class) {
                if (umengInitializer == null) {
                    umengInitializer = new UmengInitializer();
                }
            }
        }
        return umengInitializer;
    }


    private UmengInitializer() {

    }




    public void init(Context context, String appKey, String appSecret, final CustomMessageCallback callback) {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(context, appKey, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, appSecret);
        UMConfigure.setLogEnabled(false);


        //获取消息推送代理示例
        pushAgent = PushAgent.getInstance(context);
        pushAgent.setResourcePackageName(BuildConfig.APPLICATION_ID);
        pushAgent.setNotificaitonOnForeground(false);
        //注册推送服务，每次调用register方法都会回调该接口
        pushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LogUtils.showLog("注册成功：deviceToken：-------->  " + deviceToken);
                UmengInitializer.deviceToken = deviceToken;
                dealWithCustomMsg(callback);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.showLog("注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }

    private void dealWithCustomMsg(final CustomMessageCallback callback) {
        pushAgent.setMessageHandler(new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(Context context, UMessage uMessage) {
                super.dealWithCustomMessage(context, uMessage);
                LogUtils.showLog(uMessage.custom);
                callback.onGetCustomMsg(uMessage.custom);
            }
        });
    }

    public interface CustomMessageCallback {
        void onGetCustomMsg(String jsong);
    }
}
