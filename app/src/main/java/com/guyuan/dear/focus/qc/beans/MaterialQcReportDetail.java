package com.guyuan.dear.focus.qc.beans;

import android.text.TextUtils;

import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;
import com.guyuan.dear.net.resultBeans.NetQcReportDetailBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_CHECKING;
import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_PASS;
import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_REJECT;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 14:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcReportDetail extends BaseMaterialQcReport {
    private int batchSize;
    private String materialRemarks;
    private String projectName;
    private String projectId;
    private String qcApproach;
    private String judgeCondition;
    private int sampleSize;
    private String qcRemark;
    private List<GenericQcLogBean> verifyLogs;

    public MaterialQcReportDetail() {
    }

    public MaterialQcReportDetail(BaseMaterialQcReport base) {
        setDate(base.getDate());
        setMaterialId(base.getMaterialId());
        setMaterialName(base.getMaterialName());
        setMaterialType(base.getMaterialType());
        setNeedVerify(base.isNeedVerify());
        setSpec(base.getSpec());
        setTag(base.getTag());
        setQualityChecker(base.getQualityChecker());
    }

    public MaterialQcReportDetail(NetQcReportDetailBean src) {
        setReportId(src.getId());
        setSpec(src.getModel());
        setMaterialType(src.getMaterial());
        String materialRemark = src.getMaterialRemark();
        if(TextUtils.isEmpty(materialRemark)){
            materialRemark="无";
        }
        setMaterialRemarks(materialRemark);
        setBatchSize(src.getProductNum());
        setProjectId(src.getProjectCode());
        setProjectName(src.getProjectName());
        setMaterialName(src.getProductName());
        setMaterialId(src.getProductCode());
        setSampleSize(src.getQualityNum());
        setQcRemark(src.getQualityRemark());
        //判定条件  1,设计图样 2 国家标准
        List<Integer> qualityCondition = src.getQualityCondition();
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iterator = qualityCondition.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 1) {
                sb.append("设计图样");
            } else if (next == 2) {
                sb.append("国家标准");
            }
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
        setJudgeCondition(sb.toString());
        //质检方式      * 质检方式：1.全检，2.抽检，3.按标准质检文件
        int qualityType = src.getQualityType();
        switch (qualityType) {
            case 1:
                setQcApproach("全检");
                break;
            case 2:
                setQcApproach("抽检");
                break;
            case 3:
                setQcApproach("按标准质检文件");
                break;
            default:
                break;
        }
        try {
            String createTime = src.getUpdateTime();
            setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime).getTime());
        } catch (Exception e) {
            setDate(0L);
        }
        setQualityChecker(src.getQualityName());
        int approvalStatus = src.getApprovalStatus();
        setNeedVerify(approvalStatus != 1);
        //质检判断结果：0.未质检，1.合格，2.不合格
        int qualityResult = src.getQualityResult();
        switch (qualityResult) {
            case 1:
                setTag(TAG_TYPE_PASS);
                break;
            case 2:
                setTag(TAG_TYPE_REJECT);
                break;
            case 0:
            default:
                setTag(TAG_TYPE_CHECKING);
                break;
        }
        verifyLogs = new ArrayList<>();
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getMaterialRemarks() {
        return materialRemarks;
    }

    public void setMaterialRemarks(String materialRemarks) {
        this.materialRemarks = materialRemarks;
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

    public String getQcRemark() {
        return qcRemark;
    }

    public void setQcRemark(String qcRemark) {
        this.qcRemark = qcRemark;
    }
}
