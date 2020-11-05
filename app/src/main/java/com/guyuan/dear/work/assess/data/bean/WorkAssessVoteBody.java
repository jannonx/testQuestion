package com.guyuan.dear.work.assess.data.bean;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:25
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessVoteBody {

    /**
     * auditExplain :
     * auditStatus : 0
     * id : 0
     * imgUrl :
     */

    private String auditExplain;      //评审事项结果描述
    private int auditStatus;          //提交状态(1:通过；2:不通过)
    private int id;                   //评审记录ID(历史记录表ID)
    private String imgUrl;            //附件

    public String getAuditExplain() {
        return auditExplain;
    }

    public void setAuditExplain(String auditExplain) {
        this.auditExplain = auditExplain;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}