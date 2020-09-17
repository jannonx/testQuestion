package com.guyuan.dear.work.customerfollow.data;


import com.guyuan.dear.work.customerfollow.api.WorkCustomerApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkCustomerRepository {
    private WorkCustomerApiService workAfterSaleApiService;

    public WorkCustomerRepository(WorkCustomerApiService workApproveApiService) {
        this.workAfterSaleApiService = workApproveApiService;
    }


}
