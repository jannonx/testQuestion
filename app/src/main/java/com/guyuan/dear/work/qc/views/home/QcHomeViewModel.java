package com.guyuan.dear.work.qc.views.home;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 10:41
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcHomeViewModel extends BaseViewModel {
    public MutableLiveData<Boolean> refreshMyApplyList = new MutableLiveData<>(false);
}
