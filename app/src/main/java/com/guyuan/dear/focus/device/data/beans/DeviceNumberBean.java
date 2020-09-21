package com.guyuan.dear.focus.device.data.beans;

import java.util.List;

/**
 * Created by admin
 * on 2019/11/25
 */
public class DeviceNumberBean {

  /**
   * equipments : [{"sum":"1","type":"冲冲设备"},{"sum":"1","type":"压压设备"}]
   * counts : 2
   * close : 2
   * open : 0
   */

  private int equipmentSum;
  private int close;
  private int open;
  private int repair;
  private int maintance;
  private int fault;
  private List<EquipmentsBean> equipments;

  public int getRepair() {
    return repair;
  }

  public void setRepair(int repair) {
    this.repair = repair;
  }

  public int getMaintance() {
    return maintance;
  }

  public void setMaintance(int maintance) {
    this.maintance = maintance;
  }

  public int getFault() {
    return fault;
  }

  public void setFault(int fault) {
    this.fault = fault;
  }

  public int getEquipmentSum() {
    return equipmentSum;
  }

  public void setEquipmentSum(int equipmentSum) {
    this.equipmentSum = equipmentSum;
  }

  public int getClose() {
    return close;
  }

  public void setClose(int close) {
    this.close = close;
  }

  public int getOpen() {
    return open;
  }

  public void setOpen(int open) {
    this.open = open;
  }

  public List<EquipmentsBean> getEquipments() {
    return equipments;
  }

  public void setEquipments(List<EquipmentsBean> equipments) {
    this.equipments = equipments;
  }

  public static class EquipmentsBean {
    /**
     * sum : 1
     * type : 冲冲设备
     */

    private String sum;
    private String type;
    private String openSum;
    private String closeSum;
    private String maintanceSum;
    private String repairSum;
    private String faultSum;
    private long typeId;

    public long getTypeId() {
      return typeId;
    }

    public void setTypeId(long typeId) {
      this.typeId = typeId;
    }

    public String getOpenSum() {
      return openSum;
    }

    public void setOpenSum(String openSum) {
      this.openSum = openSum;
    }

    public String getCloseSum() {
      return closeSum;
    }

    public void setCloseSum(String closeSum) {
      this.closeSum = closeSum;
    }

    public String getMaintanceSum() {
      return maintanceSum;
    }

    public void setMaintanceSum(String maintanceSum) {
      this.maintanceSum = maintanceSum;
    }

    public String getRepairSum() {
      return repairSum;
    }

    public void setRepairSum(String repairSum) {
      this.repairSum = repairSum;
    }

    public String getFaultSum() {
      return faultSum;
    }

    public void setFaultSum(String faultSum) {
      this.faultSum = faultSum;
    }

    public String getSum() {
      return sum;
    }

    public void setSum(String sum) {
      this.sum = sum;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}
