package com.guyuan.dear.focus.contract.view.contractDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.bean.ContractApproveLog;
import com.guyuan.dear.focus.contract.bean.ContractComment;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/20 18:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentsViewModel extends BaseViewModel {
    public MutableLiveData<List<ContractComment>> comments = new MutableLiveData<>();
    public MutableLiveData<Long> contractDate = new MutableLiveData<>();
    public MutableLiveData<String> applier = new MutableLiveData<>();
}
