package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.ClientReceipt;

import java.util.List;

/**
 * @author: 廖华凯
 * @description: 首次使用：合同进度详情界面
 * @since: 2020/10/13 17:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PrgDetailContractBean extends BaseContractBean {
    private List<ContractPrgKnot> knotList;
    private List<ComponentStateBean> componentStates;
    private List<ContractLogBean> logs;
    private List<ClientReceipt> clientReceipts;


    public List<ContractPrgKnot> getKnotList() {
        return knotList;
    }

    public void setKnotList(List<ContractPrgKnot> knotList) {
        this.knotList = knotList;
    }

    public List<ComponentStateBean> getComponentStates() {
        return componentStates;
    }

    public void setComponentStates(List<ComponentStateBean> componentStates) {
        this.componentStates = componentStates;
    }

    public List<ContractLogBean> getLogs() {
        return logs;
    }

    public void setLogs(List<ContractLogBean> logs) {
        this.logs = logs;
    }

    public List<ClientReceipt> getClientReceipts() {
        return clientReceipts;
    }

    public void setClientReceipts(List<ClientReceipt> clientReceipts) {
        this.clientReceipts = clientReceipts;
    }
}
