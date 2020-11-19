package com.guyuan.dear.focus.projectsite.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusProjectSiteApiService extends BaseApiService {

    /**
     * 各模块展示数量
     *
     * @return
     */
//    @GET("base/auditProspect/siteList")
//    Observable<ResultBean<ProjectOverViewBean>> getProjectSiteOverViewData();


    /**
     * 现场勘察单-app查询列表(我的关注、我的工作共用；如果需要按是否满足条件、是否安全条件查询列表，加上查询参数
     * satisfyFlag 1:是，2:否;不加则显示所有详情列表)
     * 区分我的工作列表传 myWork:1，我的关注不传。
     *
     * @param body
     * @return
     */
//    @POST("base/auditProspect/appQueryList")
//    Observable<ResultBean<RefreshBean<SiteExploreBean>>> getSiteExploreDataList(@Body RequestBody body);
}