package com.guyuan.dear.home.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.message.data.bean.MessageListBean;
import com.guyuan.dear.message.data.bean.MessageUnreadBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface MainApiService extends BaseApiService {

    String GET_LAST_UNREAD_MESSAGE = MESSAGE + "tMessage/appUnReadCount";

    String MSG_TYPE = "msgType";   //显示消息种类,1:显示警告消息、预警消息；2：正常消息、办公消息

    //获取最新一条未读消息和未读消息数量
    @GET(GET_LAST_UNREAD_MESSAGE)
    Observable<ResultBean<MessageUnreadBean>> getLastUnReadMessage(@Query(MSG_TYPE) int label);
}