package com.guyuan.dear.net.api;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.BasePageResultBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.net.reqBean.ContractApplyBody;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetContractSumBean;
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

    @POST("base/staffSummary/allUserInfo")
    Observable<ResultBean<BasePageResultBean<NetStaffBean>>> getAllStaffs(@Body BasePageReqBean body);

    @POST("base/tCustomer/findCustomerQueryList")
    Observable<ResultBean<BasePageResultBean<NetClientInfo>>> getClientInfos(@Body BasePageReqBean body);

    @GET("base/tContractInfo/findContractNo")
    Observable<ResultBean<List<NetBaseContractInfo>>> getContractBaseInfosByClientId(@Query("cusId") long cusId);

    @GET("base/config/sysInit")
    Observable<ResultBean<NetServerParam>> getNetServerParams();

    @POST("base/tContractInfo/StopOrStartContractFlow")
    Observable<ResultBean<Integer>> submitContractApply(@Body ContractApplyBody body);

    @GET("base/tContractInfo/findContractOverview")
    Observable<ResultBean<NetContractSumBean>> getContractSumByDate(@Query("time") String date);
}
