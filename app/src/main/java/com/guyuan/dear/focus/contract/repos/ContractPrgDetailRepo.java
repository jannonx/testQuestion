package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 15:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailRepo {

    public Disposable getContractStatusFlowById(int contractId, DearNetHelper.NetCallback<NetContractStatusFlow> callback) {
        return DearNetHelper.getInstance().getContractStatusFlow(contractId, callback);
    }

    public Disposable getContractHistoryById(int contractId, DearNetHelper.NetCallback<NetContractHistory> callback){
        return DearNetHelper.getInstance().getContractHistoryById(contractId,callback);
    }
}
