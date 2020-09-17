package com.guyuan.dear.work.approve.data;


import com.guyuan.dear.work.aftersale.api.WorkAfterSaleApiService;
import com.guyuan.dear.work.approve.api.WorkApproveApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkApproveRepository {
    private WorkApproveApiService workAfterSaleApiService;

    public WorkApproveRepository(WorkApproveApiService workApproveApiService) {
        this.workAfterSaleApiService = workApproveApiService;
    }


}
