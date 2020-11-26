package com.guyuan.dear.analyse.operate.api;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public interface OperateApiService extends BaseApiService {
    /**
     * 上传图片
     *
     * @param map 图片信息
     * @return
     */
    @Multipart
    @POST("file/file/uploadApp")
    Observable<ResultBean<List<UploadBean>>> uploadPic(@PartMap Map<String, RequestBody> map);

    /**
     * 获取列表
     *
     * @param body 参数体
     * @return
     */
    @POST("base/tSaleIssueMain/findPage")
    Observable<ResultBean<RefreshBean<AfterSaleBean>>> getAfterSaleList(@Body RequestBody body);

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return
     */
    @GET("base/tSaleIssueMain/findInfoById")
    Observable<ResultBean<AfterSaleBean>> getAfterSaleDetail(@Query("id") long id);


    /**
     * 工程现场-意见回复-异步查询意见回复集
     *
     * @return
     */
    @GET("base/replyIdea/queryAnswer")
    Observable<ResultBean<List<AfterSaleStatusBean>>> getAfterSaleStatusList(@Query("id") long id,
                                                                             @Query("type") int type);

    /**
     * 验收成功/失败-改变状态
     *
     * @return
     */
    @GET("base/tSaleIssueMain/checkSuccess")
    Observable<ResultBean<Integer>> postAcceptInfo(@Query("status") int status);


    /**
     * 意见回复-回复操作
     *
     * @param body
     * @return
     */
    @POST("base/replyIdea/reply")
    Observable<ResultBean<Integer>> postAnswerInfo(@Body RequestBody body);
} 