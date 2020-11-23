package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 10:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyDetailBean extends BaseContractExcptBean {
    private String detailCause;
    private List<ContractApproveLog> comments;
    /**
     * 合同申请人，不一定是销售人员
     */
    private String applier;
    /**
     * 0 正常 1 暂停 2被激活
     */
    private int applyType;
    public static final int APPLY_TYPE_PAUSE = 1;
    public static final int APPLY_TYPE_RESTART = 2;
    public static final int APPLY_TYPE_NORMAL=0;


    public ContractApplyDetailBean(NetContractStatusDetail src) {
        NetContractStatusDetail.TcontractInfoBean contract = src.getTcontractInfo();
        NetContractStatusDetail.TexamineVoBean flow = src.getTexamineVo();
        if (contract != null) {
            setBuyer(contract.getCusName());
            setContractNum(contract.getContractNum());
            setApplyType(contract.getStopStatus());
            String createTime = contract.getCreateTime();
            if (!TextUtils.isEmpty(createTime)) {
                try {
                    setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime).getTime());
                } catch (Exception e) {
                    setDate(0);
                }
            }

        }
        comments = new ArrayList<>();
        if (flow != null) {
            setDetailCause(flow.getRemark());
            setJudgement(flow.getRemark1());
            setApplier(flow.getCreateName());
            List<NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean> list = flow.getTexamineFlows();
            for (NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean bean : list) {
                ContractApproveLog log = new ContractApproveLog(bean);
                comments.add(log);
            }
        }
    }


    public String getDetailCause() {
        return detailCause;
    }

    public void setDetailCause(String detailCause) {
        this.detailCause = detailCause;
    }

    public List<ContractApproveLog> getComments() {
        return comments;
    }

    public void setComments(List<ContractApproveLog> comments) {
        this.comments = comments;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }
}
