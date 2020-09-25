package com.guyuan.dear.focus.security.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.security.data.beans.DangerNumberBean;
import com.guyuan.dear.focus.security.data.beans.DangerProfileBean;
import com.guyuan.dear.focus.security.data.beans.DangerReportListBean;
import com.guyuan.dear.focus.security.data.beans.DangerTypeBean;
import com.guyuan.dear.focus.security.data.beans.SecurityHistoryBean;

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
 * @since: 2020/9/24 17:02
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface FocusSecurityApiService extends BaseApiService {

    String DANGER_NUMBER = BASE + "tSecurityBase/findByType";
    String DANGER_PROFILE = BASE + "tSecurityBase/findByFactory";
    String DANGER_REPORT_LIST = BASE + "tSecurityReport/findPage";
    String DANGER_SEARCH_HISTORY = BASE + "tSecurityReport/findReportBySecurity";
    String DANGER_TYPE_LIST = BASE + "tSecurityBase/findPage";

    //获取危险点总数概览
    @GET(DANGER_NUMBER)
    Observable<ResultBean<DangerNumberBean>> getDangerPointNumber();

    //危险点位置分布
    @GET(DANGER_PROFILE)
    Observable<ResultBean<DangerProfileBean>> getDangerPointProfile(@Query(ID) Long workshopID);

    //危险汇报列表
    @POST(DANGER_REPORT_LIST)
    Observable<ResultBean<DangerReportListBean>> getDangerPointReportList(@Body RequestBody body);

    //获取报警历史
    @GET(DANGER_SEARCH_HISTORY)
    Observable<ResultBean<List<SecurityHistoryBean>>> getDangerPointHistory(@Query("id") int id);

    //危险点类型列表
    @POST(DANGER_TYPE_LIST)
    Observable<ResultBean<DangerTypeBean>> getDangerPointTypeList(@Body RequestBody body);
}
