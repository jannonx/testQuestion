package com.guyuan.dear.analyse.operate.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.analyse.operate.bean.OperateAnalyseBean;
import com.guyuan.dear.analyse.operate.bean.OperateOverViewBean;
import com.guyuan.dear.analyse.operate.bean.OperateStatisticsBean;
import com.guyuan.dear.base.api.BaseApiService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public interface OperateApiService extends BaseApiService {


    /**
     * 首页
     *
     * @return
     */
    @GET("base/operational/findOverview")
    Observable<ResultBean<OperateOverViewBean>> getOperateOverViewData(@Query("time") String time);

  /**
     * 首页
     *
     * @return
     */
    @GET("base/operational/findRevenueStatistics")
    Observable<ResultBean<OperateStatisticsBean>> getOperateStatistics(@Query("time") String time);


    /**
     * 查询回款列表
     *
     * @return
     */
    @GET("base/operational/findPayment")
    Observable<ResultBean<List<OperateAnalyseBean>>> getPaymentList(@Query("time") String time);

    /**
     * 查询成本列表
     *
     * @return
     */
    @GET("base/operational/findCost")
    Observable<ResultBean<List<OperateAnalyseBean>>> getCostList(@Query("time") String time);

    /**
     * 查询项目成本详情
     *
     * @return
     */
    @GET("base/operational/findCostDetails")
    Observable<ResultBean<List<OperateAnalyseBean>>> getOperateDetailData(@Query("project") long project);


} 