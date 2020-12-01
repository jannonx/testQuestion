package com.guyuan.dear.work.contractRestart.view.home;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 11:43
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractRestartHomeViewModel extends BaseViewModel {
    public MutableLiveData<Boolean> refreshMyRestartApplyList = new MutableLiveData<>(false);
}
