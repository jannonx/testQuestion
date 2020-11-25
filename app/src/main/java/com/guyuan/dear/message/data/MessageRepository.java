package com.guyuan.dear.message.data;

import com.guyuan.dear.message.api.MessageApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:00
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageRepository {
    private MessageApiService apiService;

    public MessageRepository(MessageApiService apiService) {
        this.apiService = apiService;
    }

    public MessageApiService getApiService() {
        return apiService;
    }
}