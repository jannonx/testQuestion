package com.guyuan.dear.focus.purchase.data;

import com.guyuan.dear.focus.assess.api.FocusAssessApiService;
import com.guyuan.dear.focus.purchase.api.FocusPurchaseApiService;

/**
 * @description:
 * @author: 唐力
 * @since: 2020/9/17 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusPurchaseRepository {
    private FocusPurchaseApiService focusPurchaseApiService;

    public FocusPurchaseRepository(FocusPurchaseApiService focusPurchaseApiService) {
        this.focusPurchaseApiService = focusPurchaseApiService;
    }

    public FocusPurchaseApiService getFocusPurchaseApiService() {
        return focusPurchaseApiService;
    }
}
