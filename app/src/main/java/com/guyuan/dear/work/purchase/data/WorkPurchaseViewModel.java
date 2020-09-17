package com.guyuan.dear.work.purchase.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseRepository;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkPurchaseViewModel extends BaseViewModel {
    private WorkPurchaseRepository workPurchaseRepository;

    @ViewModelInject
    public WorkPurchaseViewModel(WorkPurchaseRepository workPurchaseRepository) {
        super(DearApplication.getInstance());
        this.workPurchaseRepository = workPurchaseRepository;
    }


}
