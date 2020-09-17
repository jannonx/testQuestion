package com.guyuan.dear.work.purchase.data;

import com.guyuan.dear.work.purchase.api.WorkPurchaseApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkPurchaseRepository {
    private WorkPurchaseApiService workPurchaseApiService;

    public WorkPurchaseRepository(WorkPurchaseApiService workPurchaseApiService) {
        this.workPurchaseApiService = workPurchaseApiService;
    }


}
