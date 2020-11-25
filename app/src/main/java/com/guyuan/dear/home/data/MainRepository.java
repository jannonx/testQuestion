package com.guyuan.dear.home.data;

import com.guyuan.dear.home.api.MainApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:37
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MainRepository {
    private MainApiService apiService;

    public MainRepository(MainApiService apiService) {
        this.apiService = apiService;
    }

    public MainApiService getApiService() {
        return apiService;
    }
}