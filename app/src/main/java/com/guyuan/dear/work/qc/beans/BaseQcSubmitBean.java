package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 12:15
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseQcSubmitBean {
    private BaseProjectBean projectBean;
    /**
     * 质检方式
     */
    private BaseQcApproachBean qcApproach;
    /**
     * 判定条件
     */
    private int judgeCondition;
    private int sampleSize;
    private ArrayList<StaffBean> qualityCheckers;
    /**
     * 参考 {@link BaseQcSubmitBean#QC_RESULT_PASS} {@link BaseQcSubmitBean#QC_RESULT_REJECT}
     */
    private int qcResult;
    public static final int QC_RESULT_PASS =1;
    public static final int QC_RESULT_REJECT=2;
    private String qcComment;
    /**
     * 是否审批
     */
    private boolean isToVerify;
    private ArrayList<StaffBean> verifiers;

    public BaseProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(BaseProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public BaseQcApproachBean getQcApproach() {
        return qcApproach;
    }

    public void setQcApproach(BaseQcApproachBean qcApproach) {
        this.qcApproach = qcApproach;
    }

    public int getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(int judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public ArrayList<StaffBean> getQualityCheckers() {
        if(qualityCheckers==null){
            qualityCheckers = new ArrayList<>();
        }
        return qualityCheckers;
    }

    public void setQualityCheckers(ArrayList<StaffBean> qualityCheckers) {
        this.qualityCheckers = qualityCheckers;
    }

    public int getQcResult() {
        return qcResult;
    }

    public void setQcResult(int qcResult) {
        this.qcResult = qcResult;
    }

    public String getQcComment() {
        return qcComment;
    }

    public void setQcComment(String qcComment) {
        this.qcComment = qcComment;
    }

    public boolean isToVerify() {
        return isToVerify;
    }

    public void setToVerify(boolean toVerify) {
        isToVerify = toVerify;
    }

    public ArrayList<StaffBean> getVerifiers() {
        if(verifiers==null){
            verifiers=new ArrayList<>();
        }
        return verifiers;
    }

    public void setVerifiers(ArrayList<StaffBean> verifiers) {
        this.verifiers = verifiers;
    }
}
