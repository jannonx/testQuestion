package com.guyuan.dear.work.projectsite.data;


import com.guyuan.dear.work.projectsite.api.WorkProjectSiteApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProjectSiteRepository {
    private WorkProjectSiteApiService workProjectSiteApiService;

    public WorkProjectSiteRepository(WorkProjectSiteApiService workProjectSiteApiService) {
        this.workProjectSiteApiService = workProjectSiteApiService;
    }


}
