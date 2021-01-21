package com.guyuan.dear.test.data;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.test.api.TestApiService;

import androidx.hilt.lifecycle.ViewModelInject;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 17:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TestViewModel extends BaseViewModel {
    private TestApiService apiService;

    @ViewModelInject
    public TestViewModel(TestRepository repository) {
        this.apiService = repository.getApiService();
    }


}