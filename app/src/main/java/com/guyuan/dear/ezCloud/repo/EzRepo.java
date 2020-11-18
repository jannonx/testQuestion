package com.guyuan.dear.ezCloud.repo;

import com.guyuan.dear.ezCloud.views.EzPlayBackViewModel;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 16:10
 * @company: 固远（深圳）信息技术有限公司
 **/
public class EzRepo {

    public Disposable getPlayBackListFromLocalFile(String serialNumber, int channelId, long from, long to,
                                                   GetLocalPlayBackFilesCallback callback) {
        Calendar fromDate = Calendar.getInstance();
        fromDate.setTimeInMillis(from);
        Calendar toDate = Calendar.getInstance();
        toDate.setTimeInMillis(to);
        return Observable
                .create(new ObservableOnSubscribe<List<EZDeviceRecordFile>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<EZDeviceRecordFile>> emitter)
                            throws Exception {
                        List<EZDeviceRecordFile> list = EZOpenSDK.getInstance()
                                .searchRecordFileFromDevice(serialNumber, (int) channelId, fromDate, toDate);

                        if (!emitter.isDisposed()) {
                            emitter.onNext(list);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<EZDeviceRecordFile>>() {
                    @Override
                    public void accept(List<EZDeviceRecordFile> list) throws Exception {
                        callback.onGetLocalFiles(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Exception exception = new Exception(throwable);
                        callback.onFailure(exception);
                    }
                });
    }

    public interface GetLocalPlayBackFilesCallback {

        void onGetLocalFiles(List<EZDeviceRecordFile> list);

        void onFailure(Exception error);
    }

}
