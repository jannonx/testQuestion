package com.guyuan.dear.work.assess.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.work.assess.data.bean.CustomerBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessDetailBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessItemBean;
import com.guyuan.dear.work.assess.data.bean.WorkAssessListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/4 19:22
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface WorkAssessApiService extends BaseApiService {

    String MY_CREATED_ASSESS_LIST = BASE + "audit/myAuditInfo";
    String ASSESS_LIST = BASE + "audit/queryAuditFormByStatus";
    String ASSESS_DETAIL = BASE + "audit/queryAuditFormDetailByMyWork";
    String ASSESS_VOTE = BASE + "audit/auditCommit";
    String ASSESS_COMMIT = BASE + "audit/save";
    String CUSTOMER_LIST = BASE + "tCustomer/findAllCustomer";

    //我的新建列表
    @POST(MY_CREATED_ASSESS_LIST)
    Observable<ResultBean<WorkAssessListBean>> getMyCreatedAssessList(@Body RequestBody body);


    //评审列表
    @POST(ASSESS_LIST)
    Observable<ResultBean<WorkAssessListBean>> getAssessList(@Body RequestBody body);

    //评审详情
    @GET(ASSESS_DETAIL)
    Observable<ResultBean<WorkAssessDetailBean>> getAssessDetail(@Query(ID) int id);

    //评审投票
    @POST(ASSESS_VOTE)
    Observable<ResultBean<Integer>> voteAssess(@Body RequestBody body);

    //新建评审提交或修改
    @POST(ASSESS_COMMIT)
    Observable<ResultBean<Integer>> assessCommit(@Body RequestBody body);

    //查询所有客户及客户的合同
    @GET(CUSTOMER_LIST)
    Observable<ResultBean<List<CustomerBean>>> getAllCustomerList();
}