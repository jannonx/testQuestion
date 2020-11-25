package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.focus.contract.bean.ContractStatusFlowBean;
import com.guyuan.dear.net.DearNetHelper;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 15:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailRepo {

    public Disposable getContractStatusFlowById(int contractId, DearNetHelper.NetCallback<ContractStatusFlowBean> callback) {
        return DearNetHelper.getInstance().getContractStatusFlow(contractId, callback);
    }
}
