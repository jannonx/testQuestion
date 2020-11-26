package com.guyuan.dear.message.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.message.data.bean.MessageDetailBean;
import com.guyuan.dear.message.data.bean.MessageListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface MessageApiService extends BaseApiService {
    String MESSAGE_LIST = MESSAGE + "tMessage/appFindByMyGetMessage";
    String MESSAGE_DETAIL = MESSAGE + "tMessage/findMessageInfo";
    String TYPE = "type";   //显示消息种类,1:显示警告消息、预警消息；2：正常消息、办公消息
    String MESSAGE_INFO_ID = "messageInfoId";

    @POST(MESSAGE_LIST)
    Observable<ResultBean<MessageListBean>> getMessageList(@Body RequestBody body);

    //获取消息详情
    @GET(MESSAGE_DETAIL)
    Observable<ResultBean<MessageDetailBean>> getMessageDetail(@Query(MESSAGE_INFO_ID) int messageId);
}