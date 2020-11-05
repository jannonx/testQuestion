package com.guyuan.dear.work.assess.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.assess.api.WorkAssessApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:38
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessViewModel extends BaseViewModel {
    private WorkAssessApiService apiService;

    @ViewModelInject
    public WorkAssessViewModel(WorkAssessRepository workAssessRepository) {
        this.apiService = workAssessRepository.getAssessApiService();
    }


}