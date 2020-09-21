package com.guyuan.dear.focus.device.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/26
 */
public class DeviceExceptionBean {

    /**
     * pageNum : 1
     * pageSize : 2
     * totalSize : 12
     * totalPages : 6
     * content : [{"id":12,"equipmentId":13,"exExplain":"打桩机坏了","status":2,"degree":null,
     * "description":null,"imgUrl":"error.jpg","groupId":null,"principalBy":35,"createBy":23,
     * "createTime":"2019-11-21T02:17:08.000+0000","exTime":"2019-11-21T02:17:08.000+0000",
     * "repairBy":26,"endTime":"2019-11-21T02:21:51.000+0000","unusualTime":1,
     * "cause":"修好啦啦啦啦啦啦啦啦啦","delFlag":0,"VrepairBy":"典韦","VcreateBy":"赵云","VequipmentName":"打桩机"},
     * {"id":11,"equipmentId":7,"exExplain":"炸了","status":2,"degree":null,"description":null,
     * "imgUrl":"jfidsojfs.jpg","groupId":null,"principalBy":35,"createBy":23,
     * "createTime":"2019-11-20T11:22:26.000+0000","exTime":"2019-11-20T11:22:26.000+0000",
     * "repairBy":26,"endTime":"2019-11-20T12:13:29.000+0000","unusualTime":1,"cause":"奋斗故事大纲",
     * "delFlag":0,"VrepairBy":"典韦","VcreateBy":"赵云","VequipmentName":"丝丝机"}]
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private List<ContentBean> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable {
        /**
         * id : 12
         * equipmentId : 13
         * exExplain : 打桩机坏了
         * status : 2
         * degree : null
         * description : null
         * imgUrl : error.jpg
         * groupId : null
         * principalBy : 35
         * createBy : 23
         * createTime : 2019-11-21T02:17:08.000+0000
         * exTime : 2019-11-21T02:17:08.000+0000
         * repairBy : 26
         * endTime : 2019-11-21T02:21:51.000+0000
         * unusualTime : 1
         * cause : 修好啦啦啦啦啦啦啦啦啦
         * delFlag : 0
         * VrepairBy : 典韦
         * VcreateBy : 赵云
         * VequipmentName : 打桩机
         */

        private int id;
        private int equipmentId;
        private String exExplain;
        private int status;
        private Object degree;
        private Object description;
        private String imgUrl;
        private Object groupId;
        private int principalBy;
        private int createBy;
        private String createTime;
        private String exTime;
        private int repairBy;
        private String endTime;
        private int unusualTime;
        private String cause;
        private int delFlag;
        private String VrepairBy;
        private String VcreateBy;
        private String VequipmentName;
        private String equipmentCode;
        private CameraEntity cameraBase;
        private String equipmentModel;
        private String VfactoryId;
        private String VworkshopId;
        private String supplierName;
        private String startTime;
        private String type;
        private String videoUrl;

        protected ContentBean(Parcel in) {
            id = in.readInt();
            equipmentId = in.readInt();
            exExplain = in.readString();
            status = in.readInt();
            imgUrl = in.readString();
            principalBy = in.readInt();
            createBy = in.readInt();
            createTime = in.readString();
            exTime = in.readString();
            repairBy = in.readInt();
            endTime = in.readString();
            unusualTime = in.readInt();
            cause = in.readString();
            delFlag = in.readInt();
            VrepairBy = in.readString();
            VcreateBy = in.readString();
            VequipmentName = in.readString();
            equipmentCode = in.readString();
            cameraBase = in.readParcelable(CameraEntity.class.getClassLoader());
            equipmentModel = in.readString();
            VfactoryId = in.readString();
            VworkshopId = in.readString();
            supplierName = in.readString();
            startTime = in.readString();
            type=in.readString();
            videoUrl=in.readString();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean(in);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getVfactoryId() {
            return VfactoryId;
        }

        public void setVfactoryId(String vfactoryId) {
            VfactoryId = vfactoryId;
        }

        public String getVworkshopId() {
            return VworkshopId;
        }

        public void setVworkshopId(String vworkshopId) {
            VworkshopId = vworkshopId;
        }

        public String getEquipmentCode() {
            return equipmentCode;
        }

        public void setEquipmentCode(String equipmentCode) {
            this.equipmentCode = equipmentCode;
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

        public String getExExplain() {
            return exExplain;
        }

        public void setExExplain(String exExplain) {
            this.exExplain = exExplain;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getDegree() {
            return degree;
        }

        public void setDegree(Object degree) {
            this.degree = degree;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public int getPrincipalBy() {
            return principalBy;
        }

        public void setPrincipalBy(int principalBy) {
            this.principalBy = principalBy;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExTime() {
            return exTime;
        }

        public void setExTime(String exTime) {
            this.exTime = exTime;
        }

        public int getRepairBy() {
            return repairBy;
        }

        public void setRepairBy(int repairBy) {
            this.repairBy = repairBy;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getUnusualTime() {
            return unusualTime;
        }

        public void setUnusualTime(int unusualTime) {
            this.unusualTime = unusualTime;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public String getVrepairBy() {
            return VrepairBy;
        }

        public void setVrepairBy(String VrepairBy) {
            this.VrepairBy = VrepairBy;
        }

        public String getVcreateBy() {
            return VcreateBy;
        }

        public void setVcreateBy(String VcreateBy) {
            this.VcreateBy = VcreateBy;
        }

        public String getVequipmentName() {
            return VequipmentName;
        }

        public void setVequipmentName(String VequipmentName) {
            this.VequipmentName = VequipmentName;
        }

        public CameraEntity getTcameraBase() {
            return cameraBase;
        }

        public void setTcameraBase(CameraEntity tcameraBase) {
            this.cameraBase = tcameraBase;
        }

        public String getEquipmentModel() {
            return equipmentModel;
        }

        public void setEquipmentModel(String equipmentModel) {
            this.equipmentModel = equipmentModel;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(equipmentId);
            dest.writeString(exExplain);
            dest.writeInt(status);
            dest.writeString(imgUrl);
            dest.writeInt(principalBy);
            dest.writeInt(createBy);
            dest.writeString(createTime);
            dest.writeString(exTime);
            dest.writeInt(repairBy);
            dest.writeString(endTime);
            dest.writeInt(unusualTime);
            dest.writeString(cause);
            dest.writeInt(delFlag);
            dest.writeString(VrepairBy);
            dest.writeString(VcreateBy);
            dest.writeString(VequipmentName);
            dest.writeString(equipmentCode);
            dest.writeParcelable(cameraBase, flags);
            dest.writeString(equipmentModel);
            dest.writeString(VfactoryId);
            dest.writeString(VworkshopId);
            dest.writeString(supplierName);
            dest.writeString(startTime);
            dest.writeString(type);
            dest.writeString(videoUrl);
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
