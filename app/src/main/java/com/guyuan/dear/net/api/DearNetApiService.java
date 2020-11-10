package com.guyuan.dear.net.api;

import com.example.httplibrary.bean.BasePageReqBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.example.httplibrary.bean.BasePageResultBean;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 14:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public interface DearNetApiService extends BaseApiService {
    String GET_ALL_STAFF = BASE + "staffSummary/allUserInfo";

    @POST(GET_ALL_STAFF)
    Observable<ResultBean<BasePageResultBean<NetStaffBean>>> getAllStaffs(@Body BasePageReqBean body);
}
