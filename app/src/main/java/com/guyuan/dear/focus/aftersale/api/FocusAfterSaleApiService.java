package com.guyuan.dear.focus.aftersale.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
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
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusAfterSaleApiService extends BaseApiService {
    /**
     * 获取列表
     *
     * @param body 参数体
     * @return
     */
    @POST("base/tSaleIssueMain/findPage")
    Observable<ResultBean<RefreshBean<AfterSaleBean>>> getAfterSaleList(@Body RequestBody body);

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return
     */
    @POST("base/tSaleIssueMain/findInfoById")
    Observable<ResultBean<AfterSaleBean>> getAfterSaleDetail(@Query("id") long id);


    /**
     * 工程现场-意见回复-异步查询意见回复集
     *
     * @return
     */
    @GET("base/replyIdea/queryAnswer")
    Observable<ResultBean<List<ProjectSiteStatusBean>>> getProjectSiteStatusList(@Query("id") long id,
                                                                                 @Query("type") int type);
} 