package com.guyuan.dear.focus.transport.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.transport.data.bean.TransportDetailBean;
import com.guyuan.dear.focus.transport.data.bean.TransportListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:31
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface TransportApiService extends BaseApiService {

    String TRANSPORT_LIST = BASE + "transport/queryTransportByApp";
    String TRANSPORT_DETAIL = BASE + "transport/findById";

    //运输列表
    @POST(TRANSPORT_LIST)
    Observable<ResultBean<TransportListBean>> getTransportList(@Body RequestBody body);

    //运输详情
    @GET(TRANSPORT_DETAIL)
    Observable<ResultBean<TransportDetailBean>> getTransportDetail(@Query(ID) int id);
}
