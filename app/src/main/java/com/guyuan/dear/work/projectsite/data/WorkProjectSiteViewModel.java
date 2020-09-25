package com.guyuan.dear.work.projectsite.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleRepository;
import com.guyuan.dear.work.aftersale.data.WorkAfterSaleRepository;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProjectSiteViewModel extends BaseViewModel {
    private WorkAfterSaleRepository focusAfterSaleRepository;

    @ViewModelInject
    public WorkProjectSiteViewModel(WorkAfterSaleRepository focusAfterSaleRepository) {
        this.focusAfterSaleRepository = focusAfterSaleRepository;
    }


}
