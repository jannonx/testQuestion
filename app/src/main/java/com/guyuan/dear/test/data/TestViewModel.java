package com.guyuan.dear.test.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.test.api.TestApiService;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/25 17:38
 **/

public class TestViewModel extends BaseViewModel {
    private TestApiService apiService;

    @ViewModelInject
    public TestViewModel(TestRepository repository) {
        this.apiService = repository.getApiService();
    }


}