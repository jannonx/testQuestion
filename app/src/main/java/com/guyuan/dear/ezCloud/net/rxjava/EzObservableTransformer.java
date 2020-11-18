package com.guyuan.dear.ezCloud.net.rxjava;

import com.guyuan.dear.ezCloud.net.bean.EzBaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 廖华凯
 * @since 2020/2/28 14:30
 **/
public class EzObservableTransformer implements ObservableTransformer {
    private static EzObservableTransformer instance;

    private EzObservableTransformer() {
    }

    public static EzObservableTransformer getInstance() {
        if (instance == null) {
            synchronized (EzObservableTransformer.class) {
                if (instance == null) {
                    instance = new EzObservableTransformer();
                }
            }
        }
        return instance;
    }

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream.flatMap(mFunction)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Function mFunction = new Function() {
        @Override
        public Object apply(Object o) throws Exception {
            if (o instanceof EzBaseResponse) {
                int code = ((EzBaseResponse) o).getCode();
                switch (code) {
                    case 200:
                        Object data = ((EzBaseResponse) o).getData();
                        if (data != null) {
                            return Observable.just(data);
                        }
                        break;
                    case 10002://token expire
                    default:
                        return Observable.error(new ServerApiException(code, ((EzBaseResponse) o).getMsg()));
                }
            }
            return Observable.error(new ServerApiException(-1, "服务器返回数据格式和协议不一致。"));
        }
    };
}
