package com.guyuan.dear.office.approval.data;

import com.guyuan.dear.office.approval.api.ApprovalApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:46
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalRepository {
    private ApprovalApiService apiService;

    public ApprovalRepository(ApprovalApiService apiService) {
        this.apiService = apiService;
    }

    public ApprovalApiService getApiService() {
        return apiService;
    }
}