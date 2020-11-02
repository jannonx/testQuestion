package com.guyuan.dear.mine.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.base.api.UploadBean;

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
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public interface MineApiService extends BaseApiService {
    /**
     * 修改密码
     *
     * @param password    旧密码
     * @param newPassword 新密码
     * @return
     */
    @GET("base/user/updatePassword")
    Observable<ResultBean<Integer>> editUserPassWord(@Query("password") String password,
                                                     @Query("newPassword") String newPassword
    );


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
     * 保存意见反馈表
     *
     * @param body 反馈信息
     * @return
     */
    @POST("base/tOpinion/save")
    Observable<ResultBean<Integer>> postFeedBack(@Body RequestBody body);

    /**
     * 修改用户头像
     *
     * @param body 头像链接
     * @return
     */
    @POST("base/user/updateUserHeadPortrait")
    Observable<ResultBean<Integer>> postUserAvatar(@Body RequestBody body);
} 