package com.guyuan.dear.work.device.data.bean;

public class QRBean {

  /**
   * equipment : {"id":1,"code":"332","name":"杀鸭机","lineId":1,"workshopId":1,"factoryId":1,
   * "status":1,"img":null,"purchaseTime":"2019-11-12T19:53:46.000+0000",
   * "installationTime":"2019-11-12T19:53:49.000+0000","limitDate":3,"description":"十三点",
   * "principalBy":1,"createTime":"2019-11-12T19:54:00.000+0000","delFlag":0,"vlineId":"一号流水线",
   * "vworkshopId":"一号车间","vfactoryId":null}
   */

  private EquipmentBean equipment;

  public EquipmentBean getEquipment() {
    return equipment;
  }

  public void setEquipment(EquipmentBean equipment) {
    this.equipment = equipment;
  }

  public static class EquipmentBean {
    /**
     * id : 1
     * code : 332
     * name : 杀鸭机
     * lineId : 1
     * workshopId : 1
     * factoryId : 1
     * status : 1
     * img : null
     * purchaseTime : 2019-11-12T19:53:46.000+0000
     * installationTime : 2019-11-12T19:53:49.000+0000
     * limitDate : 3
     * description : 十三点
     * principalBy : 1
     * createTime : 2019-11-12T19:54:00.000+0000
     * delFlag : 0
     * vlineId : 一号流水线
     * vworkshopId : 一号车间
     * vfactoryId : null
     */

    private int id;
    private String code;
    private String name;
    private int lineId;
    private int workshopId;
    private int factoryId;
    private int status;
    private Object img;
    private String purchaseTime;
    private String installationTime;
    private int limitDate;
    private String description;
    private int principalBy;
    private String createTime;
    private int delFlag;
    private String vlineId;
    private String vworkshopId;
    private Object vfactoryId;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getLineId() {
      return lineId;
    }

    public void setLineId(int lineId) {
      this.lineId = lineId;
    }

    public int getWorkshopId() {
      return workshopId;
    }

    public void setWorkshopId(int workshopId) {
      this.workshopId = workshopId;
    }

    public int getFactoryId() {
      return factoryId;
    }

    public void setFactoryId(int factoryId) {
      this.factoryId = factoryId;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public Object getImg() {
      return img;
    }

    public void setImg(Object img) {
      this.img = img;
    }

    public String getPurchaseTime() {
      return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
      this.purchaseTime = purchaseTime;
    }

    public String getInstallationTime() {
      return installationTime;
    }

    public void setInstallationTime(String installationTime) {
      this.installationTime = installationTime;
    }

    public int getLimitDate() {
      return limitDate;
    }

    public void setLimitDate(int limitDate) {
      this.limitDate = limitDate;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public int getPrincipalBy() {
      return principalBy;
    }

    public void setPrincipalBy(int principalBy) {
      this.principalBy = principalBy;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public int getDelFlag() {
      return delFlag;
    }

    public void setDelFlag(int delFlag) {
      this.delFlag = delFlag;
    }

    public String getVlineId() {
      return vlineId;
    }

    public void setVlineId(String vlineId) {
      this.vlineId = vlineId;
    }

    public String getVworkshopId() {
      return vworkshopId;
    }

    public void setVworkshopId(String vworkshopId) {
      this.vworkshopId = vworkshopId;
    }

    public Object getVfactoryId() {
      return vfactoryId;
    }

    public void setVfactoryId(Object vfactoryId) {
      this.vfactoryId = vfactoryId;
    }
  }
}
