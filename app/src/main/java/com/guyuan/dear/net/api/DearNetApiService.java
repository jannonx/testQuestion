package com.guyuan.dear.net.api;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.net.reqBean.ContractApplyBody;
import com.guyuan.dear.net.reqBean.SearchRqBody;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetContractSumBean;
import com.guyuan.dear.net.resultBeans.NetSearchContactInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.net.resultBeans.NetStaffBean;

import java.util.List;

import io.reactivex.Observable;
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
     * @param body
     * @return
     */
    @POST("base/staffSummary/allUserInfo")
    Observable<ResultBean<BasePageResultBean<NetStaffBean>>> getAllStaffs(@Body BasePageReqBean body);

    /**
     * 获取所有客户的清单
     * @param body
     * @return
     */
    @POST("base/tCustomer/findCustomerQueryList")
    Observable<ResultBean<BasePageResultBean<NetClientInfo>>> getClientInfos(@Body BasePageReqBean body);

    /**
     * 根据客户id获取所签的合同列表
     * @param cusId
     * @return
     */
    @GET("base/tContractInfo/findContractNo")
    Observable<ResultBean<List<NetBaseContractInfo>>> getContractBaseInfosByClientId(@Query("cusId") long cusId);

    /**
     * 获取一些基本的键值对
     * @return
     */
    @GET("base/config/sysInit")
    Observable<ResultBean<NetServerParam>> getNetServerParams();

    /**
     * 提交合同申请（暂停、重启）
     * @param body
     * @return
     */
    @POST("base/tContractInfo/StopOrStartContractFlow")
    Observable<ResultBean<Integer>> submitContractApply(@Body ContractApplyBody body);

    /**
     * 获取合同概况
     * @param date
     * @return
     */
    @GET("base/tContractInfo/findContractOverview")
    Observable<ResultBean<NetContractSumBean>> getContractSumByDate(@Query("time") String date);

    /**
     * 根据日期和类型查询合同列表
     * @param body
     * @return
     */
    @POST("base/tContractInfo/findContractSearch")
    Observable<ResultBean<List<NetSearchContactInfo>>> getContractListByTypeAndDate(@Body SearchRqBody body);

}
