package com.guyuan.dear.net.reqBean;

import com.guyuan.dear.net.resultBeans.NetMaterialBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 20:15
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SubmitQcReportBody {

    /**
     * approvalStatus : 0
     * approveFlag : 0
     * approvers : []
     * id : 0
     * imgUrl :
     * productType : 0
     * projectId : 0
     * qualityBy : 0
     * qualityCondition : []
     * qualityNum : 0
     * qualityRemark :
     * qualityResult : 0
     * qualityType : 0
     * subCodeId : 0
     */

    /**
     * 审批标记：1.审批，2.不审批
     */
    private int approveFlag;
    /**
     * 商品类型：1.原材料，2.配套设备
     */
    private int productType;
    /**
     * 项目id
     */
    private int projectId;
    /**
     * 质检人员id
     */
    private int qualityBy;
    /**
     * 质检数量
     */
    private int qualityNum;
    /**
     * 备注
     */
    private String qualityRemark;
    /**
     * 质检判断结果：0.未质检，1.合格，2.不合格
     */
    private int qualityResult;
    /**
     *
     * 质检方式：1.全检，2.抽检，3.按标准质检文件
     */
    private int qualityType;
    /**
     * 商品ID {@link NetMaterialBean#getEquipmentId()}
     */
    private int subCodeId;
    private List<Integer> approvers;
    /**
     * 判定条件：1.设计图样，2.国家标准
     */
    private List<Integer> qualityCondition;

    public int getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(int approveFlag) {
        this.approveFlag = approveFlag;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getQualityBy() {
        return qualityBy;
    }

    public void setQualityBy(int qualityBy) {
        this.qualityBy = qualityBy;
    }

    public int getQualityNum() {
        return qualityNum;
    }

    public void setQualityNum(int qualityNum) {
        this.qualityNum = qualityNum;
    }

    public String getQualityRemark() {
        return qualityRemark;
    }

    public void setQualityRemark(String qualityRemark) {
        this.qualityRemark = qualityRemark;
    }

    public int getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(int qualityResult) {
        this.qualityResult = qualityResult;
    }

    public int getQualityType() {
        return qualityType;
    }

    public void setQualityType(int qualityType) {
        this.qualityType = qualityType;
    }

    public int getSubCodeId() {
        return subCodeId;
    }

    public void setSubCodeId(int subCodeId) {
        this.subCodeId = subCodeId;
    }

    public List<Integer> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<Integer> approvers) {
        this.approvers = approvers;
    }

    public List<Integer> getQualityCondition() {
        return qualityCondition;
    }

    public void setQualityCondition(List<Integer> qualityCondition) {
        this.qualityCondition = qualityCondition;
    }
}
