package com.guyuan.dear.work.device.data.bean;

import java.util.ArrayList;

public class RepairCommitBody {
 private Long equipmentId;
 private String errorDetail;
 private String errorImgUrl;
 private ArrayList<Long> sendUsers;

  public ArrayList<Long> getSendUsers() {
    return sendUsers;
  }

  public void setSendUsers(ArrayList<Long> sendUsers) {
    this.sendUsers = sendUsers;
  }

  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getErrorDetail() {
    return errorDetail;
  }

  public void setErrorDetail(String errorDetail) {
    this.errorDetail = errorDetail;
  }


  public String getErrorImgUrl() {
    return errorImgUrl;
  }

  public void setErrorImgUrl(String errorImgUrl) {
    this.errorImgUrl = errorImgUrl;
  }

}
