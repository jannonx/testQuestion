package com.guyuan.dear.focus.transport.data;

import com.guyuan.dear.focus.transport.api.TransportApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 19:32
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportRepository {
    private TransportApiService apiService;

    public TransportRepository(TransportApiService apiService) {
        this.apiService = apiService;
    }

    public TransportApiService getApiService() {
        return apiService;
    }
}