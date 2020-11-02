package com.guyuan.dear.focus.produce.data;

import com.guyuan.dear.focus.produce.api.FocusProduceApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceRepository {
    private FocusProduceApiService apiService;

    public FocusProduceRepository(FocusProduceApiService apiService) {
        this.apiService = apiService;
    }

    public FocusProduceApiService getApiService() {
        return apiService;
    }
}
