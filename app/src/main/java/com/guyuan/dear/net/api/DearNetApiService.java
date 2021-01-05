package com.guyuan.dear.net.api;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.contract.bean.ContractBean;
import com.guyuan.dear.net.reqBean.ClockInRqBody;
import com.guyuan.dear.net.reqBean.ContractApplyBody;
import com.guyuan.dear.net.reqBean.SearchRqBody;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetBaseProjectBean;
import com.guyuan.dear.net.resultBeans.NetBaseQcBean;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.net.resultBeans.NetContractDetailInfo;
import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.net.resultBeans.NetContractInfo;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;
import com.guyuan.dear.net.resultBeans.NetContractSumBean;
import com.guyuan.dear.net.resultBeans.NetHrSummary;
import com.guyuan.dear.net.resultBeans.NetIdAndStatusMapping;
import com.guyuan.dear.net.resultBeans.NetMaterialBean;
import com.guyuan.dear.net.resultBeans.NetProductInfo;
import com.guyuan.dear.net.resultBeans.NetQcApproach;
import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;
import com.guyuan.dear.net.resultBeans.NetQcReportDetailBean;
import com.guyuan.dear.net.resultBeans.NetQcSummaryBean;
import com.guyuan.dear.net.resultBeans.NetSearchContactInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.net.resultBeans.NetVerifyFlowBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public interface DearNetApiService extends BaseApiService {

    /**
     * 获取所有员工的清单
     *
     * @param body
     * @return
     */
    @POST("base/staffSummary/allUserInfo")
    Observable<ResultBean<BasePageResultBean<NetStaffBean>>> getAllStaffs(@Body BasePageReqBean body);

    /**
     * 获取所有客户的清单
     *
     * @param body
     * @return
     */
    @POST("base/tCustomer/findCustomerQueryList")
    Observable<ResultBean<BasePageResultBean<NetClientInfo>>> getClientInfos(@Body SearchRqBody body);

    /**
     * 根据客户id获取所签的合同列表
     *
     * @param cusId
     * @param status 1.查已暂停的 2.查正常的
     * @return
     */
    @GET("base/tContractInfo/findContractNoByStatus")
    Observable<ResultBean<List<NetBaseContractInfo>>> getContractBaseInfosByClientId(@Query("cusId") long cusId, @Query("status") int status);

    /**
     * 获取一些基本的键值对
     *
     * @return
     */
    @GET("base/config/sysInit")
    Observable<ResultBean<NetServerParam>> getNetServerParams();

    /**
     * 提交合同申请（暂停、重启）
     *
     * @param body
     * @return
     */
    @POST("base/tContractInfo/StopOrStartContractFlow")
    Observable<ResultBean<Integer>> submitContractApply(@Body ContractApplyBody body);

    /**
     * 获取合同概况
     *
     * @param date
     * @return
     */
    @GET("base/tContractInfo/findContractOverview")
    Observable<ResultBean<NetContractSumBean>> getContractSumByDate(@Query("time") String date);

    /**
     * 根据日期和类型查询合同列表
     *
     * @param body
     * @return
     */
    @POST("base/tContractInfo/findContractSearch")
    Observable<ResultBean<BasePageResultBean<NetSearchContactInfo>>> getContractListByTypeAndDate(@Body SearchRqBody body);

    /**
     * 根据客户名字或合同编号查找合同列表
     * @param body
     * @return
     */
    @POST("base/tContractInfo/findContractSearch")
    Observable<ResultBean<BasePageResultBean<NetSearchContactInfo>>> getContractListByCompanyNameOrContractNo(@Body SearchRqBody body);

    /**
     * 获取当前用户申请的合同异常或合同重启清单
     */
    @POST("base/tContractInfo/findContractStatusPage")
    Observable<ResultBean<BasePageResultBean<NetContractInfo>>> getContractApplyList(@Body SearchRqBody body);

    /**
     * 获取合同异常列表或全部列表
     */
    @POST("base/tContractInfo/findContractSearch")
    Observable<ResultBean<ContractBean>> getExceptionOrTotalContractList(@Body RequestBody body);






    /**
     * 获取合同异常/重启审批状况
     *
     * @param examineId
     * @return
     */
    @GET("base/tContractInfo/findContractStatusChangeInfo")
    Observable<ResultBean<NetContractStatusDetail>> getContractStatusDetail(@Query("examineId") int examineId);

    /**
     * 根据合同ID获取详细内容(查看合同详情)
     *
     * @return
     */
    @GET("base/tContractInfo/findById")
    Observable<ResultBean<NetContractDetailInfo>> getContractDetailById(@Query("id") int contractId);

//    /**
//     * 根据合同id获取合同状态流程图
//     *
//     * @param contractId
//     * @return
//     */
//    @GET("base/tContractInfo/findContractExecutionStatus")
//    @Deprecated
//    Observable<ResultBean<NetContractStatusFlow>> getContractStatusFlowById(@Query("id") int contractId);

    /**
     * 根据合同id获取合同状态流程图
     *
     * @param contractId
     * @return
     */
    @GET("base/tContractInfo/findContractExecution")
    Observable<ResultBean<NetContractStatusFlow>> getContractStatusFlowById(@Query("id") int contractId);

    /**
     * 根据合同id获取合同历史异常事件清单
     * @param contractId
     * @return
     */
    @GET("base/tContractInfo/findContractHistory")
    Observable<ResultBean<NetContractHistory>> getContractHistoryById(@Query("id") int contractId);

    /**
     * 根据合同id查询跟进
     *
     * @param body
     * @return
     */
    @POST("base/tContractInfo/findFollowById")
    Observable<ResultBean<BasePageResultBean<NetVerifyFlowBean>>> getVerifyFlowById(@Body SearchRqBody body);

    /**
     * 获取质量概况列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("base/qualitycodedetails/findOverview")
    Observable<ResultBean<NetQcSummaryBean>> getQcSummary(@Query("startTime") String startTime, @Query("endTime") String endTime);


    /**
     * 根据类型获取QC列表
     *
     * @param body listType（必填）：1.详情列表，2.不合格列表，3.合格列表，4.我的工作列表；name（选填）：产品名称、代号、出厂编号
     * @return
     */
    @POST("base/qualitycodedetails/findAllPage")
    Observable<ResultBean<BasePageResultBean<NetBaseQcBean>>> getBaseQcListByType(@Body SearchRqBody body);

    /**
     * 根据ID获取QC报告详情
     *
     * @return
     */
    @GET("base/qualitycodedetails/findInfoById")
    Observable<ResultBean<NetQcReportDetailBean>> getQcReportDetailById(@Query("recordId") int qcReportId);

    /**
     * 根据ID获取QC报告详情下面的审批历史
     *
     * @return
     */
    @GET("base/qualitycodedetails/findApprByIdAPP")
    Observable<ResultBean<List<NetQcReportApproveFlow>>> getQcReportApproveFlow(@Query("recordId") int qcReportId);

    /**
     * 成品质检时获取所有项目列表
     *
     * @return
     */
    @GET("base/qualitycodedetails/findAllProject")
    Observable<ResultBean<List<NetBaseProjectBean>>> getProductProjectList();


    /**
     * 原材料质检时获取所有项目列表
     * @return
     */
    @GET("base/qualitycodedetails/findAllMateProject")
    Observable<ResultBean<List<NetBaseProjectBean>>> getMaterialProjectList();

    /**
     * 根据项目id获取原材料清单
     *
     * @param projectId
     * @return
     */
    @GET("base/qualitycodedetails/findAllMaterials")
    Observable<ResultBean<List<NetMaterialBean>>> getMaterialListByProjectId(@Query("projectId") int projectId);


    /**
     * 根据项目Id获取产品列表
     *
     * @param projectId
     * @return
     */
    @GET("base/qualitycodedetails/findAllCode")
    Observable<ResultBean<List<NetProductInfo>>> getProductListByProjectId(@Query("projectId") int projectId);


    /**
     * 获取质检抽查方式
     *
     * @return
     */
    @GET("base/qualitycodedetails/findAllType")
    Observable<ResultBean<List<NetQcApproach>>> getQcApproaches();

    /**
     * 提交质检报告
     *
     * @param body
     * @return
     */
    @POST("base/qualitycodedetails/addRecord")
    Observable<ResultBean<Integer>> submitQcReport(@Body SubmitQcReportBody body);


    /**
     * 获取今日人员出勤概况
     *
     * @return
     */
    @GET("base/staffSummary/staffAttendance")
    Observable<ResultBean<NetHrSummary>> getHrSummary();


    /**
     * 获取我的打卡状态和上下班时间，地点等配置
     *
     * @return
     */
    @GET("base/tCompanyWorkTime/findNowWorkTimeConfig")
    Observable<ResultBean<NetClockInConfig>> getClockInConfig();

    /**
     * 上下班打卡
     */
    @POST("base/tStaffRecordDay/appClock")
    Observable<ResultBean<Integer>> clockIn(@Body ClockInRqBody body);

    /**
     * 根据当天出勤状况获取人员id列表
     *
     * @return
     */
    @GET("base/staffSummary/staffAttendIdByType")
    Observable<ResultBean<List<Integer>>> getStaffIdsByAttendantType(@Query("type") int type);

    /**
     * 获取员工当月出勤概况（上半部）
     *
     * @param userId
     * @return
     */
    @GET("base/staffSummary/getUserDetail")
    Observable<ResultBean<NetStaffAttendStatus>> getStaffAttendStatus(@Query("userId") int userId);

    /**
     * 获取员工当月出勤概况（下半部）
     *
     * @param yearMonth
     * @param userId
     * @return
     */
    @GET("base/staffSummary/getStaffInfoAchineve")
    Observable<ResultBean<List<NetStaffAttendRecord>>> getStaffAttendRecord(@Query("month") String yearMonth, @Query("userId") int userId);


    /**
     * 根据月份查询异常的人数
     *
     * @param month yyyy-mm
     * @return
     */
    @GET("base/staffSummary/getStaffAbnormalAchineve")
    Observable<ResultBean<NetHrSummary>> getHrAbnormalSumByDate(@Query("month") String month);

    /**
     * 根据月份和异常类型查询出人员id
     *
     * @param yearMonth
     * @param type      异常类型 2.未到岗 3.迟到 4.早退 5.请假
     * @return
     */
    @GET("base/staffSummary/getUserIdAbnormalAchineve")
    Observable<ResultBean<List<Integer>>> getHrAbnormalListByTypeAndDate(@Query("month") String yearMonth, @Query("type") int type);

    /**
     * 获取所有的id和当前出勤状况的映射表
     *
     * @return
     */
    @GET("base/staffSummary/getAllStaffAttendanceStatus")
    Observable<ResultBean<List<NetIdAndStatusMapping>>> getStaffIdAndAttendStatus();




}
