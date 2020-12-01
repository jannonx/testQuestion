package com.guyuan.dear.work.contractPause.views.home;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 11:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseHomeViewModel extends BaseViewModel {
    public MutableLiveData<Boolean> refreshMyPauseApplyList = new MutableLiveData<>(false);
}
