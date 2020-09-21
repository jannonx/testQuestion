package com.guyuan.dear.focus.device.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin
 * on 2019/11/25
 */
public class FactoryRealTimeBean {

  private List<WorkshopsBean> workshops;

  public List<WorkshopsBean> getWorkshops() {
    return workshops;
  }

  public void setWorkshops(List<WorkshopsBean> workshops) {
    this.workshops = workshops;
  }

  public static class WorkshopsBean implements Parcelable {
    /**
     * id : 3
     * code : A001
     * name : 车间1
     * factoryId : 1
     * description : 222
     * principalBy : 24
     * createTime : null
     * delFlag : 0
     * equipments : [{"id":5,"code":"A001","name":"焊接机","lineId":1,"workshopId":3,"factoryId":1,
     * "status":5,"img":"http://172.10.11.16:8010/admin/images/28c0ec3c42944ed9a3c5d93631a09013
     * .jpg","purchaseTime":"2019-11-04T16:00:00.000+0000",
     * "installationTime":"2019-11-05T16:00:00.000+0000","limitDate":1,"description":"222",
     * "principalBy":22,"createTime":null,"delFlag":0,"vprincipalBy":"刘备","vlineId":"一号流水线",
     * "vworkshopId":"车间1","vfactoryId":null},{"id":6,"code":"A002","name":"切割机","lineId":null,
     * "workshopId":3,"factoryId":1,"status":3,"img":"http://172.10.11
     * .16:8010/admin/images/dee44c1de1d5487b804812c1ab99fa6b.png",
     * "purchaseTime":"2019-11-03T16:00:00.000+0000","installationTime":"2019-11-20T16:00:00
     * .000+0000","limitDate":null,"description":"","principalBy":null,"createTime":null,
     * "delFlag":0,"vprincipalBy":null,"vlineId":null,"vworkshopId":"车间1","vfactoryId":null}]
     * vprincipalBy : wake
     * vfactoryId : 一号厂房
     */

    private int id;
    private String code;
    private String name;
    private int factoryId;
    private String description;
    private int principalBy;
    private String createTime;
    private int delFlag;
    private String vprincipalBy;
    private String vfactoryId;
    private String equipmentImg;
    private String securityImg;
    private List<EquipmentBean> equipments;

    protected WorkshopsBean(Parcel in) {
      id = in.readInt();
      code = in.readString();
      name = in.readString();
      factoryId = in.readInt();
      description = in.readString();
      principalBy = in.readInt();
      createTime = in.readString();
      delFlag = in.readInt();
      vprincipalBy = in.readString();
      vfactoryId = in.readString();
      equipmentImg = in.readString();
      securityImg = in.readString();
      equipments = in.createTypedArrayList(EquipmentBean.CREATOR);
    }

    public static final Creator<WorkshopsBean> CREATOR = new Creator<WorkshopsBean>() {
      @Override
      public WorkshopsBean createFromParcel(Parcel in) {
        return new WorkshopsBean(in);
      }

      @Override
      public WorkshopsBean[] newArray(int size) {
        return new WorkshopsBean[size];
      }
    };

    public String getEquipmentImg() {
      return equipmentImg;
    }

    public void setEquipmentImg(String equipmentImg) {
      this.equipmentImg = equipmentImg;
    }

    public String getSecurityImg() {
      return securityImg;
    }

    public void setSecurityImg(String securityImg) {
      this.securityImg = securityImg;
    }

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

    public int getFactoryId() {
      return factoryId;
    }

    public void setFactoryId(int factoryId) {
      this.factoryId = factoryId;
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

    public String getVprincipalBy() {
      return vprincipalBy;
    }

    public void setVprincipalBy(String vprincipalBy) {
      this.vprincipalBy = vprincipalBy;
    }

    public String getVfactoryId() {
      return vfactoryId;
    }

    public void setVfactoryId(String vfactoryId) {
      this.vfactoryId = vfactoryId;
    }

    public List<EquipmentBean> getEquipments() {
      return equipments;
    }

    public void setEquipments(List<EquipmentBean> equipments) {
      this.equipments = equipments;
    }

    @Override
    public int describeContents() {
      return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(id);
      dest.writeString(code);
      dest.writeString(name);
      dest.writeInt(factoryId);
      dest.writeString(description);
      dest.writeInt(principalBy);
      dest.writeString(createTime);
      dest.writeInt(delFlag);
      dest.writeString(vprincipalBy);
      dest.writeString(vfactoryId);
      dest.writeString(equipmentImg);
      dest.writeString(securityImg);
      dest.writeTypedList(equipments);
    }

  }
}
