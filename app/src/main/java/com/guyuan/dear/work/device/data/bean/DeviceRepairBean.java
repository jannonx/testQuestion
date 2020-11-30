package com.guyuan.dear.work.device.data.bean;

public class DeviceRepairBean {


  /**
   * id : 8
   * userName : tl
   * userPhone : null
   * workshopName : 一号车间
   * equipmentName : 切割机
   * equipmentCode : A001
   * equipmentType :
   * errorDetail : 电气故障,机械故障
   * repairResult : null
   * repairImgUrl : null
   * lastMaintainTime : null
   * reportTime : 2019-11-21 12:56:13
   */

  private int id;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
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
}
