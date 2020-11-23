package com.guyuan.dear.work.matterapply.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.matterapply.api.MatterApplyApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyViewModel extends BaseViewModel {
    private MatterApplyApiService applyApiService;

    @ViewModelInject
    public MatterApplyViewModel(MatterApplyRepository repository) {
        applyApiService = repository.getApplyApiService();
    }


}