package com.guyuan.dear.focus.qc.beans;

import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 15:19
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcReportDetail extends BaseProductQcReport {
    private int batchSize;
    private String  projectName;
    private String projectId;
    private String qcApproach;
    private String judgeCondition;
    private int sampleSize;
    private List<GenericQcLogBean> verifyLogs;

    public ProductQcReportDetail() {
    }

    public ProductQcReportDetail(BaseProductQcReport base){
        setBatchId(base.getBatchId());
        setDate(base.getDate());
        setNeedVerify(base.isNeedVerify());
        setProductId(base.getProductId());
        setProductName(base.getProductName());
        setQualityChecker(base.getQualityChecker());
        setTag(base.getTag());
    }


    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
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
