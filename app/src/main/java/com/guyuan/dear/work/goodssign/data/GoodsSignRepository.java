package com.guyuan.dear.work.goodssign.data;

import com.guyuan.dear.work.goodssign.api.GoodsSignApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:31
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignRepository {
    private GoodsSignApiService apiService;

    public GoodsSignRepository(GoodsSignApiService apiService) {
        this.apiService = apiService;
    }

    public GoodsSignApiService getApiService() {
        return apiService;
    }
}