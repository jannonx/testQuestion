package com.guyuan.dear.focus.contract.bean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 10:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptDetailBean extends BaseContractExcptBean {
    private String detailCause;
    private List<ContractExcptComment> comments;
    /**
     * 合同申请人，不一定是销售人员
     */
    private String applier;

    public String getDetailCause() {
        return detailCause;
    }

    public void setDetailCause(String detailCause) {
        this.detailCause = detailCause;
    }

    public List<ContractExcptComment> getComments() {
        return comments;
    }

    public void setComments(List<ContractExcptComment> comments) {
        this.comments = comments;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }
}
