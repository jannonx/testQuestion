package com.jannonx.electric.home.data;

import com.jannonx.electric.home.api.MainApiService;

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