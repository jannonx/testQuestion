package com.guyuan.dear.focus.produce.data;

import com.guyuan.dear.focus.produce.api.FocusProduceApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/10 11:04
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusProduceRepository {
    private FocusProduceApiService apiService;

    public FocusProduceRepository(FocusProduceApiService apiService) {
        this.apiService = apiService;
    }

    public FocusProduceApiService getApiService() {
        return apiService;
    }
}
