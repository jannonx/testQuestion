package com.guyuan.dear.focus.device.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tl
 * created at 2020/3/16
 */
public class FarmBean implements Parcelable {

  /**
   * id : 11
   * code : LC008
   * name : 5栋2楼
   * qrcode : 158400158092329174
   * factoryId : 19
   * equipmentImg : null
   * securityImg : 158432785427195897
   * description : null
   * principalBy : 358
   * createTime : 2020-03-16 11:04:37
   * delFlag : 0
   * equipments : null
   * staffs : null
   * lines : [{"id":51,"code":"LSX5D2L001","name":"D6线(1)","qrcode":"158399951662187269","workshopId":11,"description":null,"principalBy":365,"createTime":"2020-03-12
   * 15:51:56","delFlag":0,"tfactory":{"id":19,"code":"DW01","name":"东莞厂区","qrcode":"158400135804495595","description":null,"principalBy":352,"createTime":"2020-03-09
   * 18:30:07","delFlag":0,"tWorkshopList":null,"VprincipalBy":null},"VworkshopId":"5栋2楼"}]
   */

  private int id;
  private String code;
  private String name;
  private String qrcode;
  private int factoryId;
  private String equipmentImg;
  private String securityImg;
  private String description;
  private int principalBy;
  private String createTime;
  private int delFlag;
  private Object staffs;
  private List<LinesBean> lines;
  private List<EquipmentBean> equipmentBeanList=new ArrayList<>();//不是接口返回,记录车间所有设备

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

  public String getQrcode() {
    return qrcode;
  }

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public int getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(int factoryId) {
    this.factoryId = factoryId;
  }

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

  public Object getStaffs() {
    return staffs;
  }

  public void setStaffs(Object staffs) {
    this.staffs = staffs;
  }

  public List<LinesBean> getLines() {
    return lines;
  }

  public void setLines(List<LinesBean> lines) {
    this.lines = lines;
  }

  public List<EquipmentBean> getEquipmentBeanList() {
    return equipmentBeanList;
  }

  public void setEquipmentBeanList(
      List<EquipmentBean> equipmentBeanList) {
    this.equipmentBeanList = equipmentBeanList;
  }

  public static class LinesBean implements Parcelable {
    /**
     * id : 51
     * code : LSX5D2L001
     * tfactory : {"id":19,"code":"DW01","name":"东莞厂区","qrcode":"158400135804495595","description":null,"principalBy":352,"createTime":"2020-03-09
     * 18:30:07","delFlag":0,"tWorkshopList":null,"VprincipalBy":null}
     * VworkshopId : 5栋2楼
     */

    private int id;
    private String code;
    private String name;
    private String qrcode;
    private int workshopId;
    private Object description;
    private int principalBy;
    private String createTime;
    private int delFlag;
    private TfactoryBean tfactory;
    private String VworkshopId;
    private List<EquipmentBean> equipments;

    protected LinesBean(Parcel in) {
      id = in.readInt();
      code = in.readString();
      name = in.readString();
      qrcode = in.readString();
      workshopId = in.readInt();
      principalBy = in.readInt();
      createTime = in.readString();
      delFlag = in.readInt();
      VworkshopId = in.readString();
      this.equipments = in.createTypedArrayList(EquipmentBean.CREATOR);
    }

    public static final Creator<LinesBean> CREATOR = new Creator<LinesBean>() {
      @Override
      public LinesBean createFromParcel(Parcel in) {
        return new LinesBean(in);
      }

      @Override
      public LinesBean[] newArray(int size) {
        return new LinesBean[size];
      }
    };

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

    public String getQrcode() {
      return qrcode;
    }

    public void setQrcode(String qrcode) {
      this.qrcode = qrcode;
    }

    public int getWorkshopId() {
      return workshopId;
    }

    public void setWorkshopId(int workshopId) {
      this.workshopId = workshopId;
    }

    public Object getDescription() {
      return description;
    }

    public void setDescription(Object description) {
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

    public TfactoryBean getTfactory() {
      return tfactory;
    }

    public void setTfactory(TfactoryBean tfactory) {
      this.tfactory = tfactory;
    }

    public String getVworkshopId() {
      return VworkshopId;
    }

    public void setVworkshopId(String VworkshopId) {
      this.VworkshopId = VworkshopId;
    }

    public List<EquipmentBean> getEquipments() {
      return equipments;
    }

    public void setEquipments(
        List<EquipmentBean> equipments) {
      this.equipments = equipments;
    }

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(id);
      dest.writeString(code);
      dest.writeString(name);
      dest.writeString(qrcode);
      dest.writeInt(workshopId);
      dest.writeInt(principalBy);
      dest.writeString(createTime);
      dest.writeInt(delFlag);
      dest.writeString(VworkshopId);
      dest.writeTypedList(this.equipments);
    }

    public static class TfactoryBean {
      /**
       * id : 19
       * code : DW01
       * name : 东莞厂区
       * qrcode : 158400135804495595
       * description : null
       * principalBy : 352
       * createTime : 2020-03-09 18:30:07
       * delFlag : 0
       * tWorkshopList : null
       * VprincipalBy : null
       */

      private int id;
      private String code;
      private String name;
      private String qrcode;
      private Object description;
      private int principalBy;
      private String createTime;
      private int delFlag;
      private Object tWorkshopList;
      private Object VprincipalBy;

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

      public String getQrcode() {
        return qrcode;
      }

      public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
      }

      public Object getDescription() {
        return description;
      }

      public void setDescription(Object description) {
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

      public Object getTWorkshopList() {
        return tWorkshopList;
      }

      public void setTWorkshopList(Object tWorkshopList) {
        this.tWorkshopList = tWorkshopList;
      }

      public Object getVprincipalBy() {
        return VprincipalBy;
      }

      public void setVprincipalBy(Object VprincipalBy) {
        this.VprincipalBy = VprincipalBy;
      }
    }
  }

  public FarmBean() {
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.code);
    dest.writeString(this.name);
    dest.writeString(this.qrcode);
    dest.writeInt(this.factoryId);
    dest.writeString(this.equipmentImg);
    dest.writeString(this.securityImg);
    dest.writeString(this.description);
    dest.writeInt(this.principalBy);
    dest.writeString(this.createTime);
    dest.writeInt(this.delFlag);
    dest.writeTypedList(this.lines);
    dest.writeTypedList(this.equipmentBeanList);
  }

  protected FarmBean(Parcel in) {
    this.id = in.readInt();
    this.code = in.readString();
    this.name = in.readString();
    this.qrcode = in.readString();
    this.factoryId = in.readInt();
    this.equipmentImg = in.readString();
    this.securityImg = in.readString();
    this.description = in.readString();
    this.principalBy = in.readInt();
    this.createTime = in.readString();
    this.delFlag = in.readInt();
    this.lines = in.createTypedArrayList(LinesBean.CREATOR);
    this.equipmentBeanList = in.createTypedArrayList(EquipmentBean.CREATOR);
  }

  public static final Creator<FarmBean> CREATOR = new Creator<FarmBean>() {
    @Override public FarmBean createFromParcel(Parcel source) {
      return new FarmBean(source);
    }

    @Override public FarmBean[] newArray(int size) {
      return new FarmBean[size];
    }
  };
}
