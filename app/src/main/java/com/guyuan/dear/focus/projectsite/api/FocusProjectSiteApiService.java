package com.guyuan.dear.focus.projectsite.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.projectsite.bean.ProjectOverViewBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

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
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusProjectSiteApiService extends BaseApiService {

    /**
     * 各模块展示数量
     *
     * @return
     */
    @GET("base/auditProspect/siteList")
    Observable<ResultBean<ProjectOverViewBean>> getProjectSiteOverViewData();

    /**
     * 工程现场-意见回复-异步查询意见回复集
     *
     * @return
     */
    @GET("base/replyIdea/queryAnswer")
    Observable<ResultBean<List<ProjectSiteStatusBean>>> getProjectSiteStatusList(@Query("id") long id,
                                                                                 @Query("type") int type);

    /**
     * 意见回复-回复操作
     *
     * @param body
     * @return
     */
    @POST("base/replyIdea/reply")
    Observable<ResultBean<Integer>> postAnswerInfo(@Body RequestBody body);


    //=====================================================================//
    //===============================现场勘察===============================//

    /**
     * 现场勘察单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
    @POST("base/auditProspect/appQueryList")
    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getSiteExploreList(@Body RequestBody body);


    /**
     * 工程现场-现场勘察单-app根据评审id查看详情
     *
     * @param id 查询id
     * @return
     */
    @GET("base/auditProspect/queryDetailById")
    Observable<ResultBean<SiteExploreBean>> getSiteExploreDetailData(@Query("id") long id);


    /**
     * 工程现场-现场勘察单-app提交操作
     *
     * @param body
     * @return
     */
    @POST("base/auditProspect/commit")
    Observable<ResultBean<Integer>> commitSiteExploreInfo(@Body RequestBody body);


    /**
     * 工程现场-现场勘察单-回复意见
     *
     * @param body
     * @return
     */
    @POST("base/auditProspect/saveAnswer")
    Observable<ResultBean<Integer>> postSiteExploreOpinion(@Body RequestBody body);

    //=====================================================================//
    //===============================安全排查===============================//

    /**
     * 安全排查单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
    @POST("base/auditSafety/appQueryList")
    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCheckSafeList(@Body RequestBody body);


    /**
     * 工程现场-安全排查单-app根据评审id查看详情
     *
     * @param id 查询id
     * @return
     */
    @GET("base/auditSafety/queryDetailById")
    Observable<ResultBean<SiteExploreBean>> getCheckSafeDetailData(@Query("id") long id);


    /**
     * 工程现场-安全排查单-app提交操作
     *
     * @param body
     * @return
     */
    @POST("base/auditSafety/commit")
    Observable<ResultBean<Integer>> postCheckSafeInfo(@Body RequestBody body);


    /**
     * 工程现场-安全排查单-回复意见
     *
     * @param body
     * @return
     */
    @POST("base/auditSafety/saveAnswer")
    Observable<ResultBean<Integer>> postCheckSafeOpinion(@Body RequestBody body);


    //=====================================================================//
    //===============================清点货物===============================//

    /**
     * 安清点货物单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
    @POST("base/auditGoods/queryCheckTransportGoods")
    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCheckGoodList(@Body RequestBody body);


    /**
     * 工程现场-清点货物单-app根据评审id查看详情
     *
     * @param id 查询id
     * @return
     */
    @GET("base/auditGoods/queryDetailById")
    Observable<ResultBean<SiteExploreBean>> getCheckGoodDetailData(@Query("id") long id);


    /**
     * 工程现场-清点货物单-app提交操作
     *
     * @param body
     * @return
     */
    @POST("base/auditGoods/commit")
    Observable<ResultBean<Integer>> postCheckGoodInfo(@Body RequestBody body);


    /**
     * 工程现场-清点货物察单-回复意见
     *
     * @param body
     * @return
     */
    @POST("base/auditGoods/saveAnswer")
    Observable<ResultBean<Integer>> postCheckGoodOpinion(@Body RequestBody body);


    //=====================================================================//
    //===============================安装调试===============================//

    /**
     * 安装调试单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
    @POST("base/install/findPage")
    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getInstallDebugList(@Body RequestBody body);


    /**
     * 工程现场-安装调试单-app根据评审id查看详情(多个集合)
     *
     * @param id 查询id
     * @return
     */
    @GET("base/install/findByDetails")
    Observable<ResultBean<SiteExploreBean>> getInstallDebugDetailData(@Query("id") long id);

    /**
     * 工程现场-安装调试单-app根据评审id查看详情(单个)
     *
     * @param id 查询id
     * @return
     */
    @GET("base/install/findBySingleDetails")
    Observable<ResultBean<SiteExploreBean>> getInstallDebugDetailDataBySingle(@Query("id") long id);


    /**
     * 工程现场-安装调试单-app提交操作
     *
     * @param body
     * @return
     */
    @POST("base/install/commitMyWork")
    Observable<ResultBean<Integer>> postInstallDebugInfo(@Body RequestBody body);


    /**
     * 工程现场-安装调试单-回复意见
     *
     * @param body
     * @return
     */
    @POST("base/install/saveAnswer")
    Observable<ResultBean<Integer>> postInstallDebugOpinion(@Body RequestBody body);


    //=====================================================================//
    //===============================客户验收===============================//

    /**
     * 客户验收单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
    @POST("base/auditCustomerAccept/findPage")
    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getCustomerAcceptanceList(@Body RequestBody body);


    /**
     * 工程现场-客户验收单-app根据评审id查看详情
     *
     * @param id 查询id
     * @return
     */
    @GET("base/auditCustomerAccept/findByCheckDetails")
    Observable<ResultBean<SiteExploreBean>> getCustomerAcceptanceDetailData(@Query("id") long id);


    /**
     * 工程现场-客户验收单-app提交操作
     *
     * @param body
     * @return
     */
    @POST("base/auditCustomerAccept/commitCheck")
    Observable<ResultBean<Integer>> postCustomerAcceptanceInfo(@Body RequestBody body);


    /**
     * 工程现场-客户验收单-回复意见
     *
     * @param body
     * @return
     */
    @POST("base/auditCustomerAccept/saveAnswer")
    Observable<ResultBean<Integer>> postCustomerAcceptanceOpinion(@Body RequestBody body);
}