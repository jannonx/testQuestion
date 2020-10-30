package com.guyuan.dear.focus.contract.bean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/29 20:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractDetailBean extends RestartedContractBean {
    private String detailCause;
    private List<ContractApproveLog> comments;
    /**
     * 合同申请人，不一定是销售人员
     */
    private String applier;
    private String judgement;

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

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }
}
