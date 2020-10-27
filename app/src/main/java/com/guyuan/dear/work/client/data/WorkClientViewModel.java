package com.guyuan.dear.work.client.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkClientViewModel extends BaseViewModel {
    private WorkClientRepository focusAfterSaleRepository;

    @ViewModelInject
    public WorkClientViewModel(WorkClientRepository focusAfterSaleRepository) {
        this.focusAfterSaleRepository = focusAfterSaleRepository;
    }


}
