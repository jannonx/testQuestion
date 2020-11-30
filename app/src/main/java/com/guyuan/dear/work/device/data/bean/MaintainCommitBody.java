package com.guyuan.dear.work.device.data.bean;

public class MaintainCommitBody {
  private Long equipmentId;
  private String type;   //保养类型：1、正常计划，2、临时保养
  private String detail; //保养内容
  private int equipmentStatus;//设备是否故障
  private String groupUrl;    //附件地址
  private Long createBy;   //用户id
  private String imgUrl;

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public int getEquipmentStatus() {
    return equipmentStatus;
  }

  public void setEquipmentStatus(int equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }



  public String getGroupUrl() {
    return groupUrl;
  }

  public void setGroupUrl(String groupUrl) {
    this.groupUrl = groupUrl;
  }



  public Long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

}
