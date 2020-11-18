package com.guyuan.dear.work.goodssign.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.goodssign.api.GoodsSignApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignViewModel extends BaseViewModel {
    private GoodsSignApiService apiService;

    @ViewModelInject
    public GoodsSignViewModel(GoodsSignRepository repository) {
        apiService = repository.getApiService();
    }


}