package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.ClientReceipt;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 首次使用：合同进度详情界面
 * @since: 2020/10/13 17:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractStatusFlowBean {
    private List<ContractPrgKnot> knotList;
    private SuspendPrgLog suspendPrgLog;
    private NoDepositPrgLog noDepositPrgLog;


    public ContractStatusFlowBean(NetContractStatusFlow src) {
        NetContractStatusFlow.ContractAbnormalSuspendVOBean suspendVO = src.getContractAbnormalSuspendVO();
        if(suspendVO!=null){
            suspendPrgLog = new SuspendPrgLog(suspendVO);
        }
        NetContractStatusFlow.ContractAbnormalQualityVOBean qualityVO = src.getContractAbnormalQualityVO();
        if(qualityVO!=null){
            noDepositPrgLog = new NoDepositPrgLog(qualityVO);
        }
        knotList = new ArrayList<>();
        NetContractStatusFlow.ContractExecutionVOBean vo = src.getContractExecutionVO();
        knotList.add(new ContractPrgKnot("开始",ContractPrgKnot.KNOT_STATUS_FINISHED));
        knotList.add(new ContractPrgKnot("合同评审",vo.getContractReview()));
        knotList.add(new ContractPrgKnot("设计院",vo.getDesignInstitute()));
        knotList.add(new ContractPrgKnot("生产计划",vo.getProductionPlan()));
        knotList.add(new ContractPrgKnot("采购",vo.getPurchase()));
        knotList.add(new ContractPrgKnot("质检",vo.getQualityTesting()));
        knotList.add(new ContractPrgKnot("运输",vo.getTransport()));
        knotList.add(new ContractPrgKnot("清点",vo.getCuntTheGoods()));
        knotList.add(new ContractPrgKnot("安装调试",vo.getInstall()));
        knotList.add(new ContractPrgKnot("客户验收",vo.getAcceptance()));
        knotList.add(new ContractPrgKnot("客户回款",vo.getCollection()));
        knotList.add(new ContractPrgKnot("质保金",vo.getQualityDeposit()));
        knotList.add(new ContractPrgKnot("合同完成",vo.getCompletion()));
    }

    public ContractStatusFlowBean() {
    }

    public List<ContractPrgKnot> getKnotList() {
        return knotList;
    }

    public void setKnotList(List<ContractPrgKnot> knotList) {
        this.knotList = knotList;
    }

    public SuspendPrgLog getSuspendPrgLog() {
        return suspendPrgLog;
    }

    public void setSuspendPrgLog(SuspendPrgLog suspendPrgLog) {
        this.suspendPrgLog = suspendPrgLog;
    }

    public NoDepositPrgLog getNoDepositPrgLog() {
        return noDepositPrgLog;
    }

    public void setNoDepositPrgLog(NoDepositPrgLog noDepositPrgLog) {
        this.noDepositPrgLog = noDepositPrgLog;
    }
}
