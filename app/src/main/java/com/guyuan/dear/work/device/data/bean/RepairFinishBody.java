package com.guyuan.dear.work.device.data.bean;

public class RepairFinishBody {
  private Long id;
  private String repairResult;
  private String repairImgUrl;
  private int openBtn;  //1开机,2关机

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

    public int getOpenBtn() {
        return openBtn;
    }

    public void setOpenBtn(int openBtn) {
        this.openBtn = openBtn;
    }
}
