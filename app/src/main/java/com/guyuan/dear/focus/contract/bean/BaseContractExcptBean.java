package com.guyuan.dear.focus.contract.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseContractExcptBean extends BaseContractBean {
    /**
     * 标签，如：“暂停”，“终止”
     */
    private String exceptionTag;
    /**
     * 异常原因
     */
    private String cause;
    /**
     * 判定维度，如“国家政策终止”
     */
    private String judgement;

    public BaseContractExcptBean() {
    }

    public String getExceptionTag() {
        return exceptionTag;
    }

    public void setExceptionTag(String exceptionTag) {
        this.exceptionTag = exceptionTag;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }
}
