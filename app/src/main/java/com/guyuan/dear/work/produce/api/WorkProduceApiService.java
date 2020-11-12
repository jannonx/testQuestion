package com.guyuan.dear.work.produce.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public interface WorkProduceApiService extends BaseApiService {
    /**
     * 查询生产报告列表
     *
     * @param body 请求参数
     * @return
     */
    @POST("base/projectplan/findExecutionList")
    Observable<ResultBean<RefreshBean<FocusProduceBean>>> getProduceList(@Body RequestBody body);
}
