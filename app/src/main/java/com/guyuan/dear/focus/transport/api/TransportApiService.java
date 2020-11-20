package com.guyuan.dear.focus.transport.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:31
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface TransportApiService extends BaseApiService {


    @POST("")
    Observable<ResultBean<Object>> getTransportList(@Body RequestBody body);
}
