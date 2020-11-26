package com.guyuan.dear.office.approval.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:45
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface ApprovalApiService extends BaseApiService {

    String APPROVAL_LIST = BASE + "projectplan/findApprList";

    String APPROVAL_COMMIT = BASE + "projectplan/ratifySubPlan";

    String BUSINESS_ID = "businessId";             //主、子生产计划id
    String BUSINESS_TYPE = "businessType";         //业务类型：1.主生产计划；2.子生产计划
    String REMARKS = "remarks";                    //审批备注
    String STATUS = "status";                      //审批操作类型：1.同意；2.拒绝
    String TYPE = "type";                          //审批类型：1.新建；2.暂停；3.激活

    //获取待我审批列表
    @POST(APPROVAL_LIST)
    Observable<ResultBean<ApprovalListBean>> getApprovalList(@Body RequestBody body);

    //审批同意或拒绝
    @GET(APPROVAL_COMMIT)
    Observable<ResultBean<Integer>> approval(@Query(BUSINESS_ID) int businessId,
                                             @Query(BUSINESS_TYPE) int businessType,
                                             @Query(REMARKS) String remarks, @Query(STATUS) int status,
                                             @Query(TYPE) int type);
}