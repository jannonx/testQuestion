package com.guyuan.dear.focus.qc.beans;

import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 14:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcReportDetail extends BaseMaterialQcReport {
    private int batchSize;
    private String comment;
    private String  projectName;
    private String projectId;
    private String qcApproach;
    private String judgeCondition;
    private int sampleSize;
    private List<GenericQcLogBean> verifyLogs;

    public MaterialQcReportDetail() {
    }

    public MaterialQcReportDetail(BaseMaterialQcReport base){
        setDate(base.getDate());
        setMaterialId(base.getMaterialId());
        setMaterialName(base.getMaterialName());
        setMaterialType(base.getMaterialType());
        setNeedVerify(base.isNeedVerify());
        setSpec(base.getSpec());
        setTag(base.getTag());
        setQualityChecker(base.getQualityChecker());
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQcApproach() {
        return qcApproach;
    }

    public void setQcApproach(String qcApproach) {
        this.qcApproach = qcApproach;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public List<GenericQcLogBean> getVerifyLogs() {
        return verifyLogs;
    }

    public void setVerifyLogs(List<GenericQcLogBean> verifyLogs) {
        this.verifyLogs = verifyLogs;
    }
}
