package com.guyuan.dear.focus.assess.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:15
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface FocusAssessApiService extends BaseApiService {

    String ASSESS_LIST = BASE + "audit/queryAuditForm";
    String ASSESS_OVERVIEW = BASE + "audit/queryAuditFormBySum";
    String ASSESS_DETAIL = BASE + "audit/queryAuditFormDetail";


    String AUDIT_TIME = "auditTime";
    String QUERY_PARAMS = "queryParams";
    String STATUS = "status";
    String CONTRACT_NUMBER = "contractNumber";

    //评审概览
    @POST(ASSESS_OVERVIEW)
    @FormUrlEncoded
    Observable<ResultBean<AssessOverviewBean>> getAssessOverview(@Field(AUDIT_TIME) String auditTime);

    //评审列表
    @POST(ASSESS_LIST)
    Observable<ResultBean<AssessListBean>> getAssessList(@Field(PAGE_INDEX) int pageIndex,
                                                         @Field(PAGE_SIZE) int pageSize,
                                                         @Field(QUERY_PARAMS) String queryParams,
                                                         @Field(STATUS) int status);

    //评审详情
    @POST(ASSESS_DETAIL)
    Observable<ResultBean<List<AssessDetailBean>>> getAssessDetail(@Field(ID) int id,
                                                                   @Field(CONTRACT_NUMBER) String contractNumber);
}
