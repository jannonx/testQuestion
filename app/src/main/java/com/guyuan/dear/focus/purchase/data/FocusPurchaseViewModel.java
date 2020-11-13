package com.guyuan.dear.focus.purchase.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.purchase.api.FocusPurchaseApiService;

/**
 * @description:
 * @author: 唐力
 * @since: 2020/9/17 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusPurchaseViewModel extends BaseViewModel {
    private FocusPurchaseApiService apiService;

    @ViewModelInject
    public FocusPurchaseViewModel(FocusPurchaseRepository focusPurchaseRepository) {
        apiService = focusPurchaseRepository.getFocusPurchaseApiService();
    }


}
