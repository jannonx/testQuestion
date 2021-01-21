package com.guyuan.dear.test.data;

import com.guyuan.dear.test.api.TestApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:37
 * @company : 固远（深圳）信息技术有限公司
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