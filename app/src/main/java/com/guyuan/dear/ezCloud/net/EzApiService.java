package com.guyuan.dear.ezCloud.net;


import com.guyuan.dear.ezCloud.net.bean.DeviceCapacity;
import com.guyuan.dear.ezCloud.net.bean.EzBaseResponse;
import com.guyuan.dear.ezCloud.net.bean.EzSnapShot;
import com.guyuan.dear.ezCloud.net.bean.GenericAccessToken;
import com.guyuan.dear.ezCloud.net.bean.VideoPlaybackFile;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author 廖华凯
 * @since 2019/11/25 14:28
 **/
public interface EzApiService {
    String URL = "https://open.ys7.com/";
    String APP_KEY = "72658dcdbc2641d0b9c3a8e11f74282d";
    String APP_SECRET = "ce76d3f60d9b30cdfaeee211086b5ec8";

    /**
     * 获取萤石云access token
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    @POST("api/lapp/token/get")
    @FormUrlEncoded
    Observable<EzBaseResponse<GenericAccessToken>> getEzAccessToken(
            @Field("appKey") String appKey, @Field("appSecret") String appSecret);

    /**
     * 该接口用于根据时间获取存储文件信息
     * 参考 https://open.ys7.com/doc/zh/book/index/device_select.html#device_select-api9
     */
    @POST("api/lapp/video/by/time")
    @FormUrlEncoded
    Observable<EzBaseResponse<List<VideoPlaybackFile>>> getPlaybackFiles(
            @Field("accessToken") String accessToken, @Field("deviceSerial") String deviceSerial,
            @Field("channelNo") int channelNo, @Field("recType") int recType);

    /**
     * 获取摄像头抓拍图像
     * 参考 https://open.ys7.com/doc/zh/book/index/device_option.html#device_option-api4
     *
     * @param accessToken
     * @param deviceSerial
     * @param channelNo
     * @return
     */
    @POST("/api/lapp/device/capture")
    @FormUrlEncoded
    Observable<EzBaseResponse<EzSnapShot>> getSnapShot(
            @Field("accessToken") String accessToken, @Field("deviceSerial") String deviceSerial,
            @Field("channelNo") int channelNo
    );

    /**
     * 根据设备序列号查询设备能力集
     * https://open.ys7.com/doc/zh/book/index/device_select.html#device_select-api6
     *
     * @param accessToken
     * @param deviceSerial
     * @return
     */
    @POST("/api/lapp/device/capacity")
    @FormUrlEncoded
    Observable<EzBaseResponse<DeviceCapacity>> getDeviceCapacity(
            @Field("accessToken") String accessToken, @Field("deviceSerial") String deviceSerial);
}
