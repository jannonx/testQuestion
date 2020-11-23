package com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail;

import com.guyuan.dear.focus.contract.bean.ContractApproveLog;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 16:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyDetailBean extends MyApplyBean {
    private String detailCause;
    private List<ContractApproveLog> logs;

    public MyApplyDetailBean(NetContractStatusDetail src) {
        NetContractStatusDetail.TcontractInfoBean contractInfo = src.getTcontractInfo();
        NetContractStatusDetail.TexamineVoBean examine = src.getTexamineVo();
        if (contractInfo != null) {
            setBuyer(contractInfo.getCusName());
            try {
                Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(contractInfo.getSignTime());
                setDate(date.getTime());
            } catch (Exception e) {
                setDate(0);
            }
            setApplier(examine.getCreateName());
            setContractNum(contractInfo.getContractNum());

        }
        if (examine != null) {
            setDetailCause(examine.getRemark());
            setJudgement(examine.getRemark1());
            //状态 0.审批中 1.已同意 2.已拒绝
            int status = examine.getStatus();
            switch (status){
                case 0:
                    setState(MyApplyBean.APPLY_PROCESSING);
                    break;
                case 1:
                    setState(MyApplyBean.APPLY_APPROVED);
                    break;
                case 2:
                    setState(MyApplyBean.APPLY_REJECTED);
                    break;
                default:
                    setState(MyApplyBean.APPLY_PENDING_FOR_START);
                    break;
            }
            List<NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean> flows = examine.getTexamineFlows();
            logs = new ArrayList<>();
            for (NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean bean : flows) {
                ContractApproveLog comment = new ContractApproveLog(bean);
                logs.add(comment);
            }
        }
    }

    public String getDetailCause() {
        return detailCause;
    }

    public void setDetailCause(String detailCause) {
        this.detailCause = detailCause;
    }

    public List<ContractApproveLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ContractApproveLog> logs) {
        this.logs = logs;
    }
}
