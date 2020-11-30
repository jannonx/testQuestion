package com.guyuan.dear.work.device.data.bean;

import java.util.List;

public class ControlDeviceBean {

  /**
   * code : A001
   * content : ["电气故障","机械故障","程序故障"]
   * description : 222
   * equipmentModel :
   * factoryId : 1
   * factoryName : 一号厂房
   * img : images/22d81c56b4484888821c77d4e7fdb71d.jpg
   * lastMaintainTime : 2019-11-20T14:29:27.000+0000
   * lineId : 1
   * lineName : 一号流水线
   * name : 焊接机
   * workShopName : 车间1
   * workshopId : 3
   */

  private String code;
  private String description;
  private String equipmentModel;
  private int factoryId;
  private String factoryName;
  private String img;
  private String lastMaintainTime;
  private int lineId;
  private String lineName;
  private String name;
  private String workShopName;
  private int workshopId;
  private List<String> content;
  private int maintanceType;
  private int id;
  private String planMaintainTimeEnd;
  private String planMaintainTimeStart;
  private List<String> optType;

  public String getPlanMaintainTimeEnd() {
    return planMaintainTimeEnd;
  }

  public void setPlanMaintainTimeEnd(String planMaintainTimeEnd) {
    this.planMaintainTimeEnd = planMaintainTimeEnd;
  }

  public String getPlanMaintainTimeStart() {
    return planMaintainTimeStart;
  }

  public void setPlanMaintainTimeStart(String planMaintainTimeStart) {
    this.planMaintainTimeStart = planMaintainTimeStart;
  }

  public List<String> getOptType() {
    return optType;
  }

  public void setOptType(List<String> optType) {
    this.optType = optType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMaintanceType() {
    return maintanceType;
  }

  public void setMaintanceType(int maintanceType) {
    this.maintanceType = maintanceType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEquipmentModel() {
    return equipmentModel;
  }

  public void setEquipmentModel(String equipmentModel) {
    this.equipmentModel = equipmentModel;
  }

  public int getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(int factoryId) {
    this.factoryId = factoryId;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getLastMaintainTime() {
    return lastMaintainTime;
  }

  public void setLastMaintainTime(String lastMaintainTime) {
    this.lastMaintainTime = lastMaintainTime;
  }

  public int getLineId() {
    return lineId;
  }

  public void setLineId(int lineId) {
    this.lineId = lineId;
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWorkShopName() {
    return workShopName;
  }

  public void setWorkShopName(String workShopName) {
    this.workShopName = workShopName;
  }

  public int getWorkshopId() {
    return workshopId;
  }

  public void setWorkshopId(int workshopId) {
    this.workshopId = workshopId;
  }

  public List<String> getContent() {
    return content;
  }

  public void setContent(List<String> content) {
    this.content = content;
  }
}
