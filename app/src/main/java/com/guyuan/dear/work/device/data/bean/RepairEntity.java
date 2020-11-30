package com.guyuan.dear.work.device.data.bean;

/**
 * Created by TL
 * on 2020/1/3
 */
public class RepairEntity {

  public RepairEntity(String content, boolean state) {
    this.content = content;
    this.state = state;
  }

  String content;
  boolean state;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }
}
