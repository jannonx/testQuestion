package com.guyuan.dear.focus.device.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * created by tl
 * created at 2020/3/16
 */
public class EquipmentBean implements Parcelable {

    /**
     * id : 1536
     * code : Q2S10114
     * name : 绕线机
     * lineId : 32
     * workshopId : 12
     * factoryId : 19
     * status : 1
     * type : 设备
     * x : 0.0
     * y : 0.0
     * typeId : 33
     * cost : 0.0
     * qrcode : 157743587256910172
     * img : 157656543369700006
     * principalBy : 39001
     * createTime : 2020-03-16 10:03:17
     * delFlag : 0
     * updateTime : 2020-03-16 10:03:17
     * tEquipmentInfo : {"id":840,"equipmentId":1536,"status":1,"turnOnTime":0,"turnOffTime":0,"delFlag":0}
     * content : []
     * VfactoryId : 东莞厂区
     * VworkshopId : 5栋3楼
     * VprincipalBy : 张琳东
     * VlineId : Y1线
     */

    private int id;
    private String code;
    private String name;
    private int lineId;
    private int workshopId;
    private int factoryId;
    private int status;
    private String type;
    private double x;
    private double y;
    private int typeId;
    private double cost;
    private String qrcode;
    private String img;
    private int principalBy;
    private String createTime;
    private int delFlag;
    private String updateTime;
    private TEquipmentInfoBean tEquipmentInfo;
    private String VfactoryId;
    private String VworkshopId;
    private String VprincipalBy;
    private String VlineId;
    private List<?> content;
    private CameraEntity tcameraBase;
    private String supplierName;
    private String equipmentModel;
    private String installationTime;
    private String videoUrl;

    protected EquipmentBean(Parcel in) {
        id = in.readInt();
        code = in.readString();
        name = in.readString();
        lineId = in.readInt();
        workshopId = in.readInt();
        factoryId = in.readInt();
        status = in.readInt();
        type = in.readString();
        x = in.readDouble();
        y = in.readDouble();
        typeId = in.readInt();
        cost = in.readDouble();
        qrcode = in.readString();
        img = in.readString();
        principalBy = in.readInt();
        createTime = in.readString();
        delFlag = in.readInt();
        updateTime = in.readString();
        tEquipmentInfo = in.readParcelable(TEquipmentInfoBean.class.getClassLoader());
        VfactoryId = in.readString();
        VworkshopId = in.readString();
        VprincipalBy = in.readString();
        VlineId = in.readString();
        tcameraBase = in.readParcelable(CameraEntity.class.getClassLoader());
        supplierName = in.readString();
        equipmentModel = in.readString();
        installationTime = in.readString();
        videoUrl=in.readString();
    }

    public static final Creator<EquipmentBean> CREATOR = new Creator<EquipmentBean>() {
        @Override
        public EquipmentBean createFromParcel(Parcel in) {
            return new EquipmentBean(in);
        }

        @Override
        public EquipmentBean[] newArray(int size) {
            return new EquipmentBean[size];
        }
    };

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getInstallation() {
        return installationTime;
    }

    public void setInstallation(String installation) {
        this.installationTime = installation;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public TEquipmentInfoBean getTEquipmentInfo() {
        return tEquipmentInfo;
    }

    public void setTEquipmentInfo(TEquipmentInfoBean tEquipmentInfo) {
        this.tEquipmentInfo = tEquipmentInfo;
    }

    public String getVfactoryId() {
        return VfactoryId;
    }

    public void setVfactoryId(String VfactoryId) {
        this.VfactoryId = VfactoryId;
    }

    public String getVworkshopId() {
        return VworkshopId;
    }

    public void setVworkshopId(String VworkshopId) {
        this.VworkshopId = VworkshopId;
    }

    public String getVprincipalBy() {
        return VprincipalBy;
    }

    public void setVprincipalBy(String VprincipalBy) {
        this.VprincipalBy = VprincipalBy;
    }

    public String getVlineId() {
        return VlineId;
    }

    public void setVlineId(String VlineId) {
        this.VlineId = VlineId;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public CameraEntity getTcameraBase() {
        return tcameraBase;
    }

    public void setTcameraBase(
        CameraEntity tcameraBase) {
        this.tcameraBase = tcameraBase;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
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
        dest.writeInt(lineId);
        dest.writeInt(workshopId);
        dest.writeInt(factoryId);
        dest.writeInt(status);
        dest.writeString(type);
        dest.writeDouble(x);
        dest.writeDouble(y);
        dest.writeInt(typeId);
        dest.writeDouble(cost);
        dest.writeString(qrcode);
        dest.writeString(img);
        dest.writeInt(principalBy);
        dest.writeString(createTime);
        dest.writeInt(delFlag);
        dest.writeString(updateTime);
        dest.writeParcelable(tEquipmentInfo, flags);
        dest.writeString(VfactoryId);
        dest.writeString(VworkshopId);
        dest.writeString(VprincipalBy);
        dest.writeString(VlineId);
        dest.writeParcelable(tcameraBase, flags);
        dest.writeString(supplierName);
        dest.writeString(equipmentModel);
        dest.writeString(installationTime);
        dest.writeString(videoUrl);
    }

    public static class TEquipmentInfoBean implements Parcelable {
        /**
         * id : 840
         * equipmentId : 1536
         * status : 1            //status-(状态)：1.运行 2.停机 3.保养 4.维修 5.故障
         * turnOnTime : 0.0
         * turnOffTime : 0.0
         * delFlag : 0
         */

        private int id;
        private int equipmentId;
        private int status;
        private double turnOnTime;
        private double turnOffTime;
        private int delFlag;
        private String startTime;
        private String stopTime;

        protected TEquipmentInfoBean(Parcel in) {
            id = in.readInt();
            equipmentId = in.readInt();
            status = in.readInt();
            turnOnTime = in.readDouble();
            turnOffTime = in.readDouble();
            delFlag = in.readInt();
            startTime = in.readString();
            stopTime = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(equipmentId);
            dest.writeInt(status);
            dest.writeDouble(turnOnTime);
            dest.writeDouble(turnOffTime);
            dest.writeInt(delFlag);
            dest.writeString(startTime);
            dest.writeString(stopTime);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<TEquipmentInfoBean> CREATOR = new Creator<TEquipmentInfoBean>() {
            @Override
            public TEquipmentInfoBean createFromParcel(Parcel in) {
                return new TEquipmentInfoBean(in);
            }

            @Override
            public TEquipmentInfoBean[] newArray(int size) {
                return new TEquipmentInfoBean[size];
            }
        };

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(int equipmentId) {
            this.equipmentId = equipmentId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getTurnOnTime() {
            return turnOnTime;
        }

        public void setTurnOnTime(double turnOnTime) {
            this.turnOnTime = turnOnTime;
        }

        public double getTurnOffTime() {
            return turnOffTime;
        }

        public void setTurnOffTime(double turnOffTime) {
            this.turnOffTime = turnOffTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class CameraEntity implements Parcelable {
        private String dvrSeriesNum;
        private String cameraChannel;

        protected CameraEntity(Parcel in) {
            dvrSeriesNum = in.readString();
            cameraChannel = in.readString();
        }

        public static final Creator<CameraEntity> CREATOR = new Creator<CameraEntity>() {
            @Override
            public CameraEntity createFromParcel(Parcel in) {
                return new CameraEntity(in);
            }

            @Override
            public CameraEntity[] newArray(int size) {
                return new CameraEntity[size];
            }
        };

        public String getDvrSeriesNum() {
            return dvrSeriesNum;
        }

        public void setDvrSeriesNum(String dvrSeriesNum) {
            this.dvrSeriesNum = dvrSeriesNum;
        }

        public String getCameraChannel() {
            return cameraChannel;
        }

        public void setCameraChannel(String cameraChannel) {
            this.cameraChannel = cameraChannel;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(dvrSeriesNum);
            dest.writeString(cameraChannel);
        }
    }
}
