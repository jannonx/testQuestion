package com.guyuan.dear.work.projectsite.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 15:01
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostAnswerInfo implements Serializable {
    /**
     * 主键ID（业务主键）
     */
    private long psAuditId;
    /**
     * 指导意见
     */
    private String idea;

    public long getPsAuditId() {
        return psAuditId;
    }

    public void setPsAuditId(long psAuditId) {
        this.psAuditId = psAuditId;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}
