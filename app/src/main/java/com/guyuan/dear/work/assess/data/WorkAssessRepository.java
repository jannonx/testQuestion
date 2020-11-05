package com.guyuan.dear.work.assess.data;

import com.guyuan.dear.work.assess.api.WorkAssessApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:37
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessRepository {
    private WorkAssessApiService assessApiService;

    public WorkAssessRepository(WorkAssessApiService assessApiService) {
        this.assessApiService = assessApiService;
    }

    public WorkAssessApiService getAssessApiService() {
        return assessApiService;
    }
}