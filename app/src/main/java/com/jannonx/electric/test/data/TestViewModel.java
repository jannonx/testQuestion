package com.jannonx.electric.test.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.jannonx.electric.test.api.TestApiService;

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