package com.guyuan.dear.focus.produce.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceOverViewBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;

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
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusProduceApiService extends BaseApiService {
    /**
     * 查询生产报告概况
     *
     * @param body 请求参数
     * @return
     */
    @POST("base/projectplan/findOverview")
    Observable<ResultBean<ProduceOverViewBean>> getProduceOverView(@Body RequestBody body);


    /**
     * 根据不同的生产状态查询生产报告列表
     *
     * @param body 请求参数
     * @return
     */
    @POST("base/projectplan/findReportList")
    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceListByStatus(@Body RequestBody body);


    /**
     * 查询异常生产状态的生产报告列表
     *
     * @param body 请求参数
     * @return
     */
    @POST("base/projectplan/findAbnormalList")
    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceExceptionList(@Body RequestBody body);

    /**
     * 查询生产详情列表
     *
     * @param body 请求参数
     * @return
     */
    @POST("base/projectplan/findReportList")
    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceList(@Body RequestBody body);

    /**
     * 生产详情--生产动态
     *
     * @param subPlanId 主生产计划id
     * @return
     */
    @GET("base/projectplan/findExamineBySubId")
    Observable<ResultBean<List<ProduceStateBean>>> getProduceStateList(@Query("subPlanId") long subPlanId);

    /**
     * 生产详情--基本信息
     *
     * @param equipmentId 配套设备id
     * @return
     */
    @GET("base/projectplan/findsubPlanById")
    Observable<ResultBean<FocusProduceBean>> getBasicInfoById(@Query("equipmentId") long equipmentId);

    /**
     * 根据设备id和操作类型执行操作
     *
     * @param body 提交信息
     * @return
     */
    @GET("base/projectplan/executeByType")
    Observable<ResultBean<Integer>> postExecuteProduceInfo(@Body RequestBody body);

}
