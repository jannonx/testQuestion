package com.guyuan.dear.office.approval.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.office.approval.api.ApprovalApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalViewModel extends BaseViewModel {
    private ApprovalApiService apiService;

    @ViewModelInject
    public ApprovalViewModel(ApprovalRepository repository) {
        apiService = repository.getApiService();
    }


}