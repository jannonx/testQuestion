package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.net.resultBeans.NetContractStatusFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 首次使用：合同进度详情界面
 * @since: 2020/10/13 17:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractStatusFlow {
    private List<ContractPrgKnot> knotList;
    private NoDepositPrgLog noDepositPrgLog;
    private DepositSubmitHistory depositSubmitHistory;
    private ClientPaymentHistory clientPaymentHistory;
    private List<ClientAcceptanceHistory> clientAcceptanceHistories = new ArrayList<>();
    private List<ContractPauseHistory> contractPauseHistories = new ArrayList<>();
    private List<ContractRestartHistory> contractRestartHistories = new ArrayList<>();


    public ContractStatusFlow(NetContractStatusFlow statusFlow, NetContractHistory history) {

        knotList = new ArrayList<>();
        knotList.add(new ContractPrgKnot("开始", ContractPrgKnot.KNOT_STATUS_FINISHED));
        knotList.add(new ContractPrgKnot("合同评审", statusFlow.getContractReview()));
        knotList.add(new ContractPrgKnot("设计院", statusFlow.getDesignInstitute()));
        knotList.add(new ContractPrgKnot("生产计划", statusFlow.getProductionPlan()));
        knotList.add(new ContractPrgKnot("采购", statusFlow.getPurchase()));
        knotList.add(new ContractPrgKnot("质检", statusFlow.getQualityTesting()));
        knotList.add(new ContractPrgKnot("运输", statusFlow.getTransport()));
        knotList.add(new ContractPrgKnot("清点", statusFlow.getCuntTheGoods()));
        knotList.add(new ContractPrgKnot("安装调试", statusFlow.getInstall()));
        knotList.add(new ContractPrgKnot("客户验收", statusFlow.getAcceptance()));
        knotList.add(new ContractPrgKnot("客户回款", statusFlow.getCollection()));
        knotList.add(new ContractPrgKnot("质保金", statusFlow.getQualityDeposit()));
        knotList.add(new ContractPrgKnot("合同完成", statusFlow.getCompletion()));
        int contractErrors = statusFlow.getContractErrors();
        if (contractErrors == 1) {
            for (ContractPrgKnot prgKnot : knotList) {
                if (prgKnot.getStatus() == ContractPrgKnot.KNOT_STATUS_PROCESSING) {
                    prgKnot.setWarning("合同暂停");
                }
            }
        } else if (contractErrors == 2) {
            for (ContractPrgKnot prgKnot : knotList) {
                if (prgKnot.getStatus() == ContractPrgKnot.KNOT_STATUS_PROCESSING&&prgKnot.getName().equals("质保金")) {
                    prgKnot.setWarning("无法回款");
                }
            }
        }

        NetContractHistory.QualityAbnormaVOBean qualityAbnormaVO = history.getQualityAbnormaVO();
        if (qualityAbnormaVO != null) {
            noDepositPrgLog = new NoDepositPrgLog(qualityAbnormaVO);
        }
        NetContractHistory.QualitySumVOBean qualitySumVO = history.getQualitySumVO();
        if (qualitySumVO != null) {
            depositSubmitHistory = new DepositSubmitHistory(qualitySumVO);
        }
        NetContractHistory.PaymentSumVOBean paymentSumVO = history.getPaymentSumVO();
        if (paymentSumVO != null) {
            clientPaymentHistory = new ClientPaymentHistory(paymentSumVO);
        }
        List<NetContractHistory.CustomerAcceptanceVOBean> acceptanceVO = history.getCustomerAcceptanceVO();
        if (acceptanceVO != null) {
            for (NetContractHistory.CustomerAcceptanceVOBean temp : acceptanceVO) {
                clientAcceptanceHistories.add(new ClientAcceptanceHistory(temp));
            }
        }
        List<NetContractHistory.ContractStopVOSBean> contractStopVOS = history.getContractStopVOS();
        if (contractStopVOS != null) {
            for (NetContractHistory.ContractStopVOSBean temp : contractStopVOS) {
                contractPauseHistories.add(new ContractPauseHistory(temp));
            }
        }
        List<NetContractHistory.ContractRestartVOSBean> contractRestartVOS = history.getContractRestartVOS();
        if (contractRestartVOS != null) {
            for (NetContractHistory.ContractRestartVOSBean temp : contractRestartVOS) {
                contractRestartHistories.add(new ContractRestartHistory(temp));
            }
        }
    }

    public List<ContractPrgKnot> getKnotList() {
        return knotList;
    }

    public void setKnotList(List<ContractPrgKnot> knotList) {
        this.knotList = knotList;
    }

    public NoDepositPrgLog getNoDepositPrgLog() {
        return noDepositPrgLog;
    }

    public void setNoDepositPrgLog(NoDepositPrgLog noDepositPrgLog) {
        this.noDepositPrgLog = noDepositPrgLog;
    }

    public DepositSubmitHistory getDepositSubmitHistory() {
        return depositSubmitHistory;
    }

    public void setDepositSubmitHistory(DepositSubmitHistory depositSubmitHistory) {
        this.depositSubmitHistory = depositSubmitHistory;
    }

    public ClientPaymentHistory getClientPaymentHistory() {
        return clientPaymentHistory;
    }

    public void setClientPaymentHistory(ClientPaymentHistory clientPaymentHistory) {
        this.clientPaymentHistory = clientPaymentHistory;
    }

    public List<ClientAcceptanceHistory> getClientAcceptanceHistories() {
        return clientAcceptanceHistories;
    }

    public void setClientAcceptanceHistories(List<ClientAcceptanceHistory> clientAcceptanceHistories) {
        this.clientAcceptanceHistories = clientAcceptanceHistories;
    }

    public List<ContractPauseHistory> getContractPauseHistories() {
        return contractPauseHistories;
    }

    public void setContractPauseHistories(List<ContractPauseHistory> contractPauseHistories) {
        this.contractPauseHistories = contractPauseHistories;
    }

    public List<ContractRestartHistory> getContractRestartHistories() {
        return contractRestartHistories;
    }

    public void setContractRestartHistories(List<ContractRestartHistory> contractRestartHistories) {
        this.contractRestartHistories = contractRestartHistories;
    }
}
