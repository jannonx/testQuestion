package com.guyuan.dear.focus.contract.view.contractRestart;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 15:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartContractListViewModel extends BaseViewModel {
    private MutableLiveData<List<RestartedContractBean>> contractList = new MutableLiveData<>();

}
