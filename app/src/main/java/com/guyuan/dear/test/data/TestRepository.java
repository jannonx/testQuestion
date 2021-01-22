package com.guyuan.dear.test.data;

import com.guyuan.dear.test.api.TestApiService;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:37
 **/

public class TestRepository {
    private TestApiService apiService;

    public TestRepository(TestApiService apiService) {
        this.apiService = apiService;
    }

    public TestApiService getApiService() {
        return apiService;
    }
}