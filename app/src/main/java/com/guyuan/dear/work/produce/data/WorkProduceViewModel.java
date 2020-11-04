package com.guyuan.dear.work.produce.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.produce.api.WorkProduceApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceViewModel extends BaseViewModel {
    private WorkProduceApiService apiService;

    @ViewModelInject
    public WorkProduceViewModel(WorkProduceRepository repository) {
        apiService = repository.getApiService();
    }


}
