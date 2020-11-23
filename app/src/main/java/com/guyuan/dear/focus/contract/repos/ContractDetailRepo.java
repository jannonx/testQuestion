package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/20 19:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailRepo {

    public Disposable loadContractDetailById(int contractId, DearNetHelper.NetCallback<DetailContractBean> callback){
        return DearNetHelper.getInstance().getContractDetail(contractId,callback);
    }

    public Disposable getVerifyFlowByContractId(int contractId, DearNetHelper.NetCallback<List<ContractComment>> callback){
        return DearNetHelper.getInstance().getVerifyFlowByContractId(contractId,callback);
    }

}
