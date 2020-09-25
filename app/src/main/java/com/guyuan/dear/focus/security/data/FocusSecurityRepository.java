package com.guyuan.dear.focus.security.data;

import com.guyuan.dear.focus.security.api.FocusSecurityApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 17:18
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityRepository {
    private FocusSecurityApiService apiService;

    public FocusSecurityRepository(FocusSecurityApiService apiService) {
        this.apiService = apiService;
    }

    public FocusSecurityApiService getApiService() {
        return apiService;
    }
}
