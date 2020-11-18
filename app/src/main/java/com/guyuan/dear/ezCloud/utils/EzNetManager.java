package com.guyuan.dear.ezCloud.utils;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.ezCloud.net.EzApiService;
import com.guyuan.dear.ezCloud.net.bean.DeviceCapacity;
import com.guyuan.dear.ezCloud.net.bean.EzBaseResponse;
import com.guyuan.dear.ezCloud.net.bean.EzSnapShot;
import com.guyuan.dear.ezCloud.net.bean.GenericAccessToken;
import com.guyuan.dear.ezCloud.net.bean.VideoPlaybackFile;
import com.guyuan.dear.ezCloud.net.rxjava.EzApiServiceModule;
import com.guyuan.dear.ezCloud.net.rxjava.EzObservableTransformer;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EzvizAPI;
import com.videogo.openapi.bean.EZAccessToken;
import com.videogo.openapi.bean.EZCloudRecordFile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 处理萤石云网络请求
 *
 * @author 廖华凯
 * @since 2020/1/20 11:24
 **/
public class EzNetManager {
  private static EzNetManager helper;
  private EzApiService ezApiService;

  private EzNetManager() {
    ezApiService = EzApiServiceModule.getInstance().getRetrofit();
  }

  public static EzNetManager getInstance() {
    if (helper == null) {
      synchronized (EzNetManager.class) {
        if (helper == null) {
          helper = new EzNetManager();
        }
      }
    }
    return helper;
  }

  public void makeSureAccessTokenValid(){
    getAccessToken(new NetCallback<EZAccessToken>() {
      @Override
      public void onSubscribe(Disposable disposable) {

      }

      @Override
      public void onSuccess(EZAccessToken result) {
        EZOpenSDK.getInstance().setAccessToken(result.getAccessToken());
      }

      @Override
      public void onError(Exception error) {
        LogUtils.showLog(error.getMessage());
      }
    });
  }

  public Disposable getAccessToken(NetCallback<EZAccessToken> callback) {
    EZAccessToken token = getLocalToken();
    if (token != null) {
      Disposable disposable = new Disposable() {
        @Override
        public void dispose() {
        }

        @Override
        public boolean isDisposed() {
          return true;
        }
      };
      callback.onSubscribe(disposable);
      callback.onSuccess(token);
      return disposable;
    } else {
      return getTokenByHttps(callback);
    }
  }

  /**
   * 该接口用于根据时间获取存储文件信息
   * 参考 https://open.ys7.com/doc/zh/book/index/device_select.html#device_select-api9
   * @deprecated 官方接口有bug，无法获取隔天的云端回播
   * @return
   */
  public void getVideoPlaybackFileFromCloud(String deviceSerial, int channelNo, int recType, long startTime,
                                                  long endTime, NetCallback<List<EZCloudRecordFile>> callback) {
    String accessToken = EzvizAPI.getInstance().getEZAccessToken().getAccessToken();
    Observable<EzBaseResponse<List<VideoPlaybackFile>>> observable = ezApiService.getPlaybackFiles(
        accessToken,deviceSerial,channelNo,recType
    );
     getDisposal(observable, callback, new Mapper<List<VideoPlaybackFile>, List<EZCloudRecordFile>>() {
      @Override
      public List<EZCloudRecordFile> map(List<VideoPlaybackFile> videoPlaybackFiles) {
        List<EZCloudRecordFile> result = new ArrayList<>();
        for (VideoPlaybackFile temp : videoPlaybackFiles) {
          result.add(temp.toEzCloudRecordFile());
        }
        return result;
      }
    });
  }

  /**
   *  获取摄像头抓拍图像
   *  参考 https://open.ys7.com/doc/zh/book/index/device_option.html#device_option-api4
   * @param callback
   * @return
   */
  public Disposable getCameraSnapShot(String serialNum,int channelId, NetCallback<EzSnapShot> callback){
    String accessToken = EzvizAPI.getInstance().getEZAccessToken().getAccessToken();
    Observable<EzBaseResponse<EzSnapShot>> observable = ezApiService.getSnapShot(
        accessToken, serialNum, channelId);
    return getDisposal(observable,callback,null);
  }

  /**
   * 根据设备序列号查询设备能力集
   * https://open.ys7.com/doc/zh/book/index/device_select.html#device_select-api6
   * @param serialNum
   * @param callback
   * @return
   */
  public Disposable getDeviceCapacity(String serialNum, NetCallback<DeviceCapacity> callback){
    String accessToken = EzvizAPI.getInstance().getEZAccessToken().getAccessToken();
    Observable<EzBaseResponse<DeviceCapacity>> observable = ezApiService.getDeviceCapacity(accessToken, serialNum);
    return getDisposal(observable,callback,null);
  }




  /******************************************私有函数******************************************************/

  private EZAccessToken getLocalToken() {
    String json = (String) DearApplication.getInstance().getCacheData(ConstantValue.KEY_YING_SHI_TOKEN, "");
    if (!TextUtils.isEmpty(json)) {
      GenericAccessToken token = new Gson().fromJson(json, GenericAccessToken.class);
      if (!TextUtils.isEmpty(token.getAccessToken()) && token.getExpireTime() > System.currentTimeMillis()) {
        return token.toSdkAccessToken();
      }
    }
    return null;
  }

  private Disposable getTokenByHttps(NetCallback<EZAccessToken> callback) {
    Observable<EzBaseResponse<GenericAccessToken>> observable = ezApiService.getEzAccessToken(EzApiService.APP_KEY, EzApiService.APP_SECRET);
    return getDisposal(observable, callback, new Mapper<GenericAccessToken, EZAccessToken>() {
      @Override
      public EZAccessToken map(GenericAccessToken genericAccessToken) {
        String json = new Gson().toJson(genericAccessToken);
        DearApplication.getInstance().saveCacheData(ConstantValue.KEY_YING_SHI_TOKEN, json);
        EZOpenSDK.getInstance().setAccessToken(genericAccessToken.getAccessToken());
        return genericAccessToken.toSdkAccessToken();
      }
    });
  }

  private <Response extends EzBaseResponse, Data, ReturnType> Disposable getDisposal(
      Observable<Response> observable, NetCallback<ReturnType> callback, @Nullable Mapper<Data, ReturnType> mapper) {

    Disposable disposable = observable
        .compose(EzObservableTransformer.getInstance())
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override
          public void accept(Disposable disposable) throws Exception {
            callback.onSubscribe(disposable);
          }
        })
        .subscribe(new Consumer() {
          @Override
          public void accept(Object o) throws Exception {
            if (mapper == null) {
              callback.onSuccess((ReturnType) o);
            } else {
              callback.onSuccess(mapper.map((Data) o));
            }

          }
        }, new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) throws Exception {
            callback.onError(new Exception(throwable.getMessage()));
          }
        });
    return disposable;
  }

  private interface Mapper<From, To> {
    To map(From from);
  }

  public interface NetCallback<T> {
    void onSubscribe(Disposable disposable);

    void onSuccess(T result);

    void onError(Exception error);
  }
}
