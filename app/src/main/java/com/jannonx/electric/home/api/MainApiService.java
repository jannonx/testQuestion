package com.jannonx.electric.home.api;

import com.jannonx.electric.base.api.BaseApiService;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:38
 **/

public interface MainApiService extends BaseApiService {

    String GET_LAST_UNREAD_MESSAGE = MESSAGE + "tMessage/appUnReadCount";

    String MSG_TYPE = "msgType";   //显示消息种类,1:显示警告消息、预警消息；2：正常消息、办公消息


}