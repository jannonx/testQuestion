package com.guyuan.dear.work.produce.data;

import com.guyuan.dear.work.produce.api.WorkProduceApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceRepository {
    private WorkProduceApiService apiService;

    public WorkProduceRepository(WorkProduceApiService apiService) {
        this.apiService = apiService;
    }

    public WorkProduceApiService getApiService() {
        return apiService;
    }
}
