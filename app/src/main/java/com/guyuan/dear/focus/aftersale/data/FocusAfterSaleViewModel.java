package com.guyuan.dear.focus.aftersale.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleViewModel extends BaseViewModel {
    private FocusAfterSaleApiService apiService;

    @ViewModelInject
    public FocusAfterSaleViewModel(FocusAfterSaleRepository focusAfterSaleRepository) {
        apiService = focusAfterSaleRepository.getFocusAfterSaleApiService();
    }


}
