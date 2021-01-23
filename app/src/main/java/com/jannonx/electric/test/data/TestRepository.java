package com.jannonx.electric.test.data;

import com.jannonx.electric.test.api.TestApiService;

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