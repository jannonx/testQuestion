package com.guyuan.dear.work.client.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;

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
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public interface WorkClientApiService extends BaseApiService {

    /**
     * 根据名称模糊查询客户列表
     *
     * @param name 客户名称
     * @return
     */
    @POST("base/tCustomer/findByName")
    Observable<ResultBean<List<ClientCompanyBean>>> getClientListByName(@Query("name") String name);

    /**
     * 客户查询列表
     *
     * @param body 列表参数
     * @return
     */
    @POST("base/tCustomer/findCustomerQueryList")
    Observable<ResultBean<List<ClientCompanyBean>>> getClientList(@Body RequestBody body);

    /**
     * 查询我的跟进记录
     *
     * @param id 列表参数
     * @return
     */
    @POST("base/tCustomer/findCustomerFollowUpList")
    Observable<ResultBean<List<ClientCompanyBean>>> getMyClientList(@Query("id") long id);

    /**
     * 根据客户id查看详情
     *
     * @param id 客户id
     * @return
     */
    @GET("base/tCustomer/findDetailsById")
    Observable<ResultBean<ClientCompanyBean>> getClientBasicInfo(@Query("id") Long id);

    /**
     * 根据客户id查询跟进
     *
     * @param body 列表参数
     * @return
     */
    @POST("base/tCustomer/findFollowById")
    Observable<ResultBean<RefreshBean<ClientCompanyBean>>> getFollowCommentList(@Body RequestBody body);
    /**
     * 填写用户跟进
     *
     * @param content    评价内容
     * @param customerId 客户id
     * @return
     */
    @GET("base/tCustomer/followUp")
    Observable<ResultBean<Integer>> postClientFollowUp(@Query("customerId") long customerId,
                                                       @Query("content") String content
    );

} 