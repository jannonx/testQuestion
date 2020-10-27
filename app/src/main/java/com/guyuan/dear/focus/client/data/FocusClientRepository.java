package com.guyuan.dear.focus.client.data;


import com.guyuan.dear.focus.client.api.FocusClientApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientRepository {
    private FocusClientApiService focusAfterSaleApiService;

    public FocusClientRepository(FocusClientApiService focusAfterSaleApiService) {
        this.focusAfterSaleApiService = focusAfterSaleApiService;
    }


}
