package com.jannonx.electric.base.api;

import com.example.httplibrary.bean.ResultBean;
import com.example.httplibrary.rx.BaseSchedulersCompat;

import io.reactivex.Observable;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/12/10 15:15
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