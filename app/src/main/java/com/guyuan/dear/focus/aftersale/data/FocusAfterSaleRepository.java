package com.guyuan.dear.focus.aftersale.data;


import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleRepository {
    private FocusAfterSaleApiService focusAfterSaleApiService;

    public FocusAfterSaleRepository(FocusAfterSaleApiService focusAfterSaleApiService) {
        this.focusAfterSaleApiService = focusAfterSaleApiService;
    }

    public FocusAfterSaleApiService getFocusAfterSaleApiService() {
        return focusAfterSaleApiService;
    }
}
