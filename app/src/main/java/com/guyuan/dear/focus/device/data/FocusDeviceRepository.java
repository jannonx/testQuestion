package com.guyuan.dear.focus.device.data;

import com.guyuan.dear.focus.device.api.FocusDeviceApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:23
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceRepository {
    private FocusDeviceApiService focusDeviceApiService;

    public FocusDeviceRepository(FocusDeviceApiService focusDeviceApiService) {
        this.focusDeviceApiService = focusDeviceApiService;
    }

    public FocusDeviceApiService getFocusDeviceApiService() {
        return focusDeviceApiService;
    }
}
