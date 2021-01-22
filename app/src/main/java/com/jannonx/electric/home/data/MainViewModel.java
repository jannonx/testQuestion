package com.jannonx.electric.home.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.jannonx.electric.home.api.MainApiService;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:38
 **/

public class MainViewModel extends BaseViewModel {
    private MainApiService apiService;


    @ViewModelInject
    public MainViewModel(MainRepository repository) {
        this.apiService = repository.getApiService();
    }


}