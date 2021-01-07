package com.guyuan.dear.focus.produce.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
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
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusProduceApiService extends BaseApiService {

    String APPROVAL_COMMIT = BASE + "projectplan/ratifySubPlan";

    String BUSINESS_ID = "businessId";             //主、子生产计划id
    String BUSINESS_TYPE = "businessType";         //业务类型：1.主生产计划；2.子生产计划
    String REMARKS = "remarks";                    //审批备注
    String STATUS = "status";                      //审批操作类型：1.同意；2.拒绝
    String TYPE = "type";                          //审批类型：1.新建；2.暂停；3.激活


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
    //base/projectplan/findsubPlanById
    @GET("base/projectplan/findSubPlanByIdW")
    Observable<ResultBean<FocusProduceBean>> getBasicInfoById(@Query("subPlanId") long equipmentId);

    /**
     * 根据设备id和操作类型执行操作
     *
     * @param body 提交信息
     * @return
     */
    @POST("base/projectplan/executeByType")
    Observable<ResultBean<Integer>> postExecuteProduceInfo(@Body RequestBody body);


    //生产计划审批
    @GET(APPROVAL_COMMIT)
    Observable<ResultBean<Integer>> approval(@Query(BUSINESS_ID) int businessId,
                                             @Query(BUSINESS_TYPE) int businessType,
                                             @Query(REMARKS) String remarks, @Query(STATUS) int status,
                                             @Query(TYPE) int type);
}
