package com.guyuan.dear.focus.produce.bean;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.LogUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/9 10:22
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceBean implements Serializable {

    private int id;

    /**
     * 审批状态：
     * 0.暂停待1级审批，1.暂停审批中，2.暂停审批通过，
     * 3.暂停审批被驳回，4.激活待1级审批，5.激活审批中，
     * 6.激活审批通过，7.激活审批被驳回
     */
    private int approvalStatus;
    private ApprovalStatusType approvalStatusType;

    /**
     * 产品代号
     */
    private String code;
    /**
     * 显示原因的类型：1.暂停原因；2.激活原因；3.驳回原因；4.通过原因
     */
    private int reasonType;
    private ProduceReasonType produceReasonType;
    /**
     * 显示原因
     */
    private String displayReason;

    /**
     * 显示时间
     */
    private String displayTime;

    /**
     * 配套设备id
     */
    private long equipmentId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 配套设备生产计划id
     */
    private long planId;

    /**
     * 责任单位
     */
    private String principalDept;

    /**
     * 生产计划状态：
     * 0.暂停，1.待开始，2.生产中，3.激活生产中，
     * 4.完成，5.拖期未完成，6.拖期已完成
     */
    private int status;

    private ProductStatusType statusType;
    /**
     * 显示时间的类型：
     * 1.实际完成时间；2.拖期完成时间；3.实际开始时间；
     * 4.实际激活时间；5.实际暂停时间；6.审批驳回时间；7.审批通过时间
     */
    private int timeType;
    private DisPlayTimeType disPlayTimeType;
    /**
     * 操作人
     */
    private String updateName;

    private int peopleType;
    private OperatorType operatorType;

    /**
     * 实际生产结束时间
     */
    private String actualEndTime;
    /**
     * 外购资料提供时间
     */
    private String outsourcingTime;
    /**
     * 实际生产开始时间
     */
    private String actualStartTime;
    /**
     * 图纸下发时间
     */
    private String drawingIssuedTime;
    /**
     * 料单下发时间
     */
    private String materialIssuedTime;
    /**
     * 材料到厂时间
     */
    private String materialReceiveTime;
    /**
     * 实际暂停时间
     */
    private String pauseTime;
    /**
     * 计划生产结束时间
     */
    private String planEndTime;
    /**
     * 计划生产开始时间
     */
    private String planStartTime;
    /**
     * 计划入库时间
     */
    private String planStockTime;
    /**
     * 最后一次审批状态
     */
    private String apprStatus;
    /**
     * 最后一次审批时间
     */
    private String apprTime;

    /**
     * 数量
     */
    private int num;

    /**
     * 责任人
     */
    private String principalPerson;
    /**
     * 项目编号
     */
    private String projectCode;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 外购件到厂时间
     */
    private String purchaseReceiveTime;
    /**
     * 单位
     */
    private String unit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public ApprovalStatusType getApprovalStatusType() {
        return ApprovalStatusType.toType(approvalStatus);
    }


    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getReasonType() {
        return reasonType;
    }

    /**
     * 显示原因的类型：1.暂停原因；2.激活原因；3.驳回原因；4.通过原因
     */

    public String getReasonTypeStr() {
        return ProduceReasonType.toText(reasonType);
    }

    public void setReasonType(int reasonType) {
        this.reasonType = reasonType;
    }

    public String getDisplayReason() {
        return displayReason;
    }

    public void setDisplayReason(String displayReason) {
        this.displayReason = displayReason;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getPrincipalDept() {
        return principalDept;
    }

    public void setPrincipalDept(String principalDept) {
        this.principalDept = principalDept;
    }

    /**
     * 生产计划状态：
     * 0.暂停，1.待开始，2.生产中，3.激活生产中，
     * 4.完成，5.拖期未完成，6.拖期已完成
     */
    public String getStatusText() {
        return getStatusType().getDes();
    }

    public int getStatusTextColor() {
        return getStatusType().getTextColor();

    }
    public int getStatusTextBg() {
       return getStatusType().getTextBgColor();

    }

    public int getStatus() {
        return status;
    }

    public ProductStatusType getStatusType() {
        return ProductStatusType.toType(status);
    }

    public void setStatusType(int status) {
        this.statusType = ProductStatusType.toType(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimeType() {
        return timeType;
    }

    public DisPlayTimeType getDisPlayTimeType() {
        return DisPlayTimeType.toType(timeType);
    }

    public void setDisPlayTimeType(DisPlayTimeType disPlayTimeType) {
        this.disPlayTimeType = disPlayTimeType;
    }

    public String getOperatorStr() {
        statusType = ProductStatusType.toType(status);
        approvalStatusType = ApprovalStatusType.toType(approvalStatus);
        if (ProductStatusType.TYPE_PRODUCE_ING == statusType) {
            if (ApprovalStatusType.TYPE_APPROVAL_NOT_APPLY == approvalStatusType
                    || ApprovalStatusType.TYPE_APPROVAL_PAUSE_PASS == approvalStatusType) {
                return "操作员：" + (updateName==null?"":updateName);
            }
        } else if (ProductStatusType.TYPE_PRODUCE_COMPLETE == statusType
                || ProductStatusType.TYPE_PRODUCE_DELAY_FINISH == statusType
                || ProductStatusType.TYPE_PRODUCE_DELAY_NOT_FINISH == statusType) {
            return "操作员：" + (updateName==null?"":updateName);
        }

        return getApprovalStatusType().getDes();
    }

    public String getTimeTypeStr() {
//        LogUtils.showLog("getTimeTypeStr="+getDisPlayTimeType().getCode());
        return getDisPlayTimeType().getMessage();
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public String getUpdateName() {
        return updateName;
    }

    public int getPeopleType() {
        return peopleType;
    }

    public OperatorType getOperatorType() {
        return OperatorType.getEnum(peopleType);
    }

    public void setPeopleType(int peopleType) {
        this.peopleType = peopleType;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getDrawingIssuedTime() {
        return drawingIssuedTime;
    }

    public void setDrawingIssuedTime(String drawingIssuedTime) {
        this.drawingIssuedTime = drawingIssuedTime;
    }

    public String getMaterialIssuedTime() {
        return materialIssuedTime;
    }

    public void setMaterialIssuedTime(String materialIssuedTime) {
        this.materialIssuedTime = materialIssuedTime;
    }

    public String getMaterialReceiveTime() {
        return materialReceiveTime;
    }

    public void setMaterialReceiveTime(String materialReceiveTime) {
        this.materialReceiveTime = materialReceiveTime;
    }

    public String getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(String pauseTime) {
        this.pauseTime = pauseTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPlanStockTime() {
        return planStockTime;
    }

    public void setPlanStockTime(String planStockTime) {
        this.planStockTime = planStockTime;
    }

    public String getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(String apprStatus) {
        this.apprStatus = apprStatus;
    }

    public String getApprTime() {
        return apprTime;
    }

    public void setApprTime(String apprTime) {
        this.apprTime = apprTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPrincipalPerson() {
        return principalPerson;
    }

    public void setPrincipalPerson(String principalPerson) {
        this.principalPerson = principalPerson;
    }

    public String getOutsourcingTime() {
        return outsourcingTime;
    }

    public void setOutsourcingTime(String outsourcingTime) {
        this.outsourcingTime = outsourcingTime;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPurchaseReceiveTime() {
        return purchaseReceiveTime;
    }

    public void setPurchaseReceiveTime(String purchaseReceiveTime) {
        this.purchaseReceiveTime = purchaseReceiveTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
