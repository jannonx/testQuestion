package com.guyuan.dear.focus.stock.data;

import com.guyuan.dear.focus.stock.api.FocusStockApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:36
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusStockRepository {
    private FocusStockApiService apiService;

    public FocusStockRepository(FocusStockApiService apiService) {
        this.apiService = apiService;
    }

    public FocusStockApiService getApiService() {
        return apiService;
    }
}
