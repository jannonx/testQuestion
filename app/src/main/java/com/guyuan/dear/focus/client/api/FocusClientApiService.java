package com.guyuan.dear.focus.client.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.client.bean.ClientBean;
import com.guyuan.dear.focus.security.data.beans.DangerReportListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusClientApiService extends BaseApiService {

    @GET("base/tCustomer/findById")
    Observable<ResultBean<ClientBean>> getClientInfoById(@Query("id") int id);

    @GET("base/tCustomer/findByName")
    Observable<ResultBean<List<ClientBean>>> getClientInfoByName(@Query("name") String name);


    @GET("base/tCustomer/findCustomerFollowUpList")
    Observable<ResultBean<List<ClientBean>>> getFollowUpList(@Query("id") int id);

    //危险汇报列表
    @POST("base/tCustomer/findCustomerQueryList")
    Observable<ResultBean<ClientBean>> getClientLIst(@Body RequestBody body);


} 