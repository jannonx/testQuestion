package com.guyuan.dear.home.data;

import com.guyuan.dear.home.api.MainApiService;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:37
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