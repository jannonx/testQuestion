package com.guyuan.dear.focus.client.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.security.data.beans.DangerProfileBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusClientApiService extends BaseApiService {
    //AfterSale
    @POST("base//tCustomer/findCustomerQueryList")
    Observable<ResultBean<ClientCompanyBean>> getClientList(@Body RequestBody body);


    @GET("base//tCustomer/findCustomerQueryList")
    Observable<ResultBean<DangerProfileBean>> get(@Query(ID) Long workshopID);
} 