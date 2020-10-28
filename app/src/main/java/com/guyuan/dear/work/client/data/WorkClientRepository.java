package com.guyuan.dear.work.client.data;


import com.guyuan.dear.work.client.api.WorkClientApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkClientRepository {
    private WorkClientApiService focusAfterSaleApiService;

    public WorkClientRepository(WorkClientApiService focusAfterSaleApiService) {
        this.focusAfterSaleApiService = focusAfterSaleApiService;
    }


}
