package com.guyuan.dear.home.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.home.api.MainApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MainViewModel extends BaseViewModel {
    private MainApiService apiService;


    @ViewModelInject
    public MainViewModel(MainRepository repository) {
        this.apiService = repository.getApiService();
    }


}