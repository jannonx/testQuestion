package com.guyuan.dear.approve.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApprovalData;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @description: 掌上办公--审批--后台接口定义
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public interface ApproveApiService {
    /**
     * 掌上办公--审批--我的审批--待我审批列表
     *
     * @param body 查询筛选条件
     * @return 待我审批列表数据
     */
    @POST("base/tApproveFlow/findByApprove")
    Observable<ResultBean<RefreshBean<ApprovalData>>> getApplyNotApproveList(@Body RequestBody body);

    /**
     * 掌上办公--审批--我的审批--我已审批列表
     *
     * @param body 查询筛选条件
     * @return 我已审批列表数据
     */
    @POST("base/tApproveFlow/findByOver")
    Observable<ResultBean<RefreshBean<ApprovalData>>> getApplyYetApproveList(@Body RequestBody body);


    /**
     * 掌上办公--审核--我发起的
     *
     * @param body 查询筛选条件
     * @return 我发起的列表数据
     */
    @POST("base/tApproveAll/findByUserId")
    Observable<ResultBean<RefreshBean<ApplyBean>>> getMyApplyList(@Body RequestBody body);


    /**
     * 掌上办公--审核--抄送我的
     *
     * @return 抄送我的列表数据
     */
    @GET("base/tDescriptionCc/findByUser")
    Observable<ResultBean<RefreshBean<ApplyBean>>> getApplyCopyList();


    /**
     * 掌上办公--审核--提交申请信息
     *
     * @param body 申请信息
     * @return 提交数据反馈，integer>0?成功:失败
     */
    @POST("base/tApproveAll/launch")
    Observable<ResultBean<Integer>> postApplyInfo(@Body RequestBody body);

    /**
     * 掌上办公--我的审批--审批
     *
     * @param body 批示内容
     * @return 提交数据反馈，integer>0?成功:失败
     */
    @POST("base/tApproveAll/ratify")
    Observable<ResultBean<Integer>> postApproveByMe(@Body RequestBody body);
}
