package com.guyuan.dear.focus.client.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientViewModel extends BaseViewModel {
    private FocusClientRepository focusAfterSaleRepository;

    @ViewModelInject
    public FocusClientViewModel(FocusClientRepository focusAfterSaleRepository) {
        this.focusAfterSaleRepository = focusAfterSaleRepository;
    }


}
