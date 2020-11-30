package com.guyuan.dear.work.device.data.bean;

import java.util.List;

/**
 * created by tl
 * created at 2020/7/13
 */
public class WorkDeviceDetailBean {

    /**
     * id : 207
     * userName : dcq
     * userPhone : null
     * workshopName : 5栋1楼
     * equipmentName : 双门烤箱
     * equipmentCode : Q3S40239
     * equipmentType : 烤箱
     * errorDetail : 机械故障
     * repairResult : 维修合格
     * repairImgUrl : http://183.62.99.102:8082/test/app/159426471465163929_Screenshot_20200622_111328.jpg
     * lastMaintainTime : 2020-07-09 11:24:54
     * reportTime : 2020-07-09 11:18:20
     * errorImgUrl : http://183.62.99.102:8082/test/app/159426469945195847_Screenshot_20200606_161506.jpg
     * status : 2
     * repairerId : 1335
     * reporterId : 1335
     * repairTime : 2020-07-09 11:18:28
     * completeTime : 2020-07-09 11:18:35
     * groupId : null
     * groupUrl : null
     * repairerName : dcq
     * reporterName : dcq
     * equipmentModel : S3S40238
     * equipmentStatus : 4
     * repairResultOption : ["维修合格","无法维修","报废"]
     */

    private long id;
    private String userName;
    private String userPhone;
    private String workshopName;
    private String equipmentName;
    private String equipmentCode;
    private String equipmentType;
    private String errorDetail;
    private String repairResult;
    private String repairImgUrl;
    private String lastMaintainTime;
    private String reportTime;
    private String errorImgUrl;
    private int status;
    private int repairerId;
    private int reporterId;
    private String repairTime;
    private String completeTime;
    private Object groupId;
    private Object groupUrl;
    private String repairerName;
    private String reporterName;
    private String equipmentModel;
    private int equipmentStatus;
    private List<String> repairResultOption;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public String getRepairImgUrl() {
        return repairImgUrl;
    }

    public void setRepairImgUrl(String repairImgUrl) {
        this.repairImgUrl = repairImgUrl;
    }

    public String getLastMaintainTime() {
        return lastMaintainTime;
    }

    public void setLastMaintainTime(String lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getErrorImgUrl() {
        return errorImgUrl;
    }

    public void setErrorImgUrl(String errorImgUrl) {
        this.errorImgUrl = errorImgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRepairerId() {
        return repairerId;
    }

    public void setRepairerId(int repairerId) {
        this.repairerId = repairerId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public Object getGroupId() {
        return groupId;
    }

    public void setGroupId(Object groupId) {
        this.groupId = groupId;
    }

    public Object getGroupUrl() {
        return groupUrl;
    }

    public void setGroupUrl(Object groupUrl) {
        this.groupUrl = groupUrl;
    }

    public String getRepairerName() {
        return repairerName;
    }

    public void setRepairerName(String repairerName) {
        this.repairerName = repairerName;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public int getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(int equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public List<String> getRepairResultOption() {
        return repairResultOption;
    }

    public void setRepairResultOption(List<String> repairResultOption) {
        this.repairResultOption = repairResultOption;
    }

    public String getDeviceStatus() {
        String statusStr = "";
        switch (equipmentStatus) {
            case 1:
                statusStr = "运行";
                break;

            case 2:
                statusStr = "停机";
                break;

            case 3:
                statusStr = "保养";

                break;


            case 4:
                statusStr = "维修";
                break;

            case 5:
                statusStr = "故障";
                break;

        }
        return statusStr;
    }
}
