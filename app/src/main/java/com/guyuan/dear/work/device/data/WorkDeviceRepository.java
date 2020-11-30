package com.guyuan.dear.work.device.data;

import com.guyuan.dear.work.device.api.WorkDeviceApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/30 11:00
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkDeviceRepository {
    private WorkDeviceApiService apiService;

    public WorkDeviceRepository(WorkDeviceApiService apiService) {
        this.apiService = apiService;
    }

    public WorkDeviceApiService getApiService() {
        return apiService;
    }
}