package com.guyuan.dear.focus.produce.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.produce.api.FocusProduceApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/10 11:05
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusProduceViewModel extends BaseViewModel {
    private FocusProduceApiService apiService;

    @ViewModelInject
    public FocusProduceViewModel(FocusProduceRepository focusProduceRepository) {
        apiService = focusProduceRepository.getApiService();
    }


}
