package com.guyuan.dear.focus.security.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by tl
 * created at 2020/4/16
 */

public class SecurityBaseBean implements Parcelable {
    /**
     * id : 25
     * name : 货梯
     * code : AQ001
     * factoryName : 东莞厂区
     * workshopName : 5栋8楼
     * typeName : 电气危险区域
     * dutyName : 宣观秀
     * dutyPhone : 18124855679
     * urgentName : null
     * urgentPhone : null
     * qrCode : 159178407883512708
     * status : 1
     * createTime : 1591782705000
     * imgUrl : 159178399565245136
     * cameraChannel : 15
     * dvrSeriesNum : E22840701
     */

    private int id;                          //主键id
    private String name;                     //安全点名称
    private String code;                     //安全点代号
    private String factoryName;              //所属厂区名称
    private String workshopName;             //所属车间名称
    private String typeName;                 //安全点类型名称
    private String dutyName;                 //负责人
    private String dutyPhone;                //负责人电话
    private Object urgentName;               //紧急联系人
    private Object urgentPhone;              //紧急联系人电话
    private String qrCode;                   //安全点对应二维码
    private int status;                      //安全点状态:0:异常 1:正常
    private String createTime;                 //创建时间
    private String imgUrl;                   //安全点图片地址
    private String cameraChannel;            //摄像头通道号
    private String dvrSeriesNum;             //摄像头序列号
    private String floorName;                //楼层名
    private String videoUrl;

    protected SecurityBaseBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        code = in.readString();
        factoryName = in.readString();
        workshopName = in.readString();
        typeName = in.readString();
        dutyName = in.readString();
        dutyPhone = in.readString();
        qrCode = in.readString();
        status = in.readInt();
        createTime = in.readString();
        imgUrl = in.readString();
        cameraChannel = in.readString();
        dvrSeriesNum = in.readString();
        floorName = in.readString();
        videoUrl=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(factoryName);
        dest.writeString(workshopName);
        dest.writeString(typeName);
        dest.writeString(dutyName);
        dest.writeString(dutyPhone);
        dest.writeString(qrCode);
        dest.writeInt(status);
        dest.writeString(createTime);
        dest.writeString(imgUrl);
        dest.writeString(cameraChannel);
        dest.writeString(dvrSeriesNum);
        dest.writeString(floorName);
        dest.writeString(videoUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SecurityBaseBean> CREATOR = new Creator<SecurityBaseBean>() {
        @Override
        public SecurityBaseBean createFromParcel(Parcel in) {
            return new SecurityBaseBean(in);
        }

        @Override
        public SecurityBaseBean[] newArray(int size) {
            return new SecurityBaseBean[size];
        }
    };

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyPhone() {
        return dutyPhone;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
    }

    public Object getUrgentName() {
        return urgentName;
    }

    public void setUrgentName(Object urgentName) {
        this.urgentName = urgentName;
    }

    public Object getUrgentPhone() {
        return urgentPhone;
    }

    public void setUrgentPhone(Object urgentPhone) {
        this.urgentPhone = urgentPhone;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCameraChannel() {
        return cameraChannel;
    }

    public void setCameraChannel(String cameraChannel) {
        this.cameraChannel = cameraChannel;
    }

    public String getDvrSeriesNum() {
        return dvrSeriesNum;
    }

    public void setDvrSeriesNum(String dvrSeriesNum) {
        this.dvrSeriesNum = dvrSeriesNum;
    }
}

