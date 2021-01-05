package com.guyuan.umengpush;

import android.app.Application;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/29 15:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UmengPushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UmengInitializer.getInstance().init(this, UmengConstants.APP_KEY, UmengConstants.MESSAGE_SECRET,
                new UmengInitializer.CustomMessageCallback() {
            @Override
            public void onGetCustomMsg(String jsong) {
                //todo 这里解析json字符串以及发广播给相关activity
            }
        });
    }
}
