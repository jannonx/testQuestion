package com.guyuan.dear.focus.stock.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.stock.api.FocusStockApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/12 10:38
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusStockViewModel extends BaseViewModel {

    private FocusStockApiService apiService;

    @ViewModelInject
    public FocusStockViewModel(FocusStockRepository focusStockRepository) {
        apiService = focusStockRepository.getApiService();
    }
}
