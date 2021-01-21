package com.guyuan.dear.base.api;

import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.BaseSchedulersCompat;

import io.reactivex.Observable;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/10 15:15
 * @company : 固远（深圳）信息技术有限公司
 **/

public class SchedulersCompat extends BaseSchedulersCompat {

    private static SchedulersCompat schedulersCompat;

    public static SchedulersCompat getInstance() {
        if (schedulersCompat == null) {
            schedulersCompat = new SchedulersCompat();
        }
        return schedulersCompat;
    }

    @Override
    protected Observable onError(int errorCode, ResultBean resultBean) {
        return null;
    }
}