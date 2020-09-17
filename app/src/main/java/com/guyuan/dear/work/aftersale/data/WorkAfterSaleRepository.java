package com.guyuan.dear.work.aftersale.data;


import com.guyuan.dear.work.aftersale.api.WorkAfterSaleApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkAfterSaleRepository {
    private WorkAfterSaleApiService workAfterSaleApiService;

    public WorkAfterSaleRepository(WorkAfterSaleApiService workAfterSaleApiService) {
        this.workAfterSaleApiService = workAfterSaleApiService;
    }


}
