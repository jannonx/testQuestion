package com.guyuan.dear.work.device.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ControlRepairBean {


    private List<RepairEntity> content;

    public List<RepairEntity> getContent() {
        return content;
    }

    public void setContent(List<RepairEntity> content) {
        this.content = content;
    }

    public static class RepairEntity implements Parcelable {

        /**
         * id : 215
         * userName : 唐力
         * userPhone : null
         * workshopName : 5栋1楼
         * equipmentName : 双门烤箱
         * equipmentCode : Q3S40239
         * equipmentType : null
         * errorDetail : 程序故障
         * repairResult : 维修合格
         * repairImgUrl : http://183.62.99.102:8082/test/app/159460959616654422_IMG_20200619_182656.jpg
         * lastMaintainTime : 2020-07-09 15:19:20
         * reportTime : 2020-07-13 11:05:50
         * errorImgUrl : http://183.62.99.102:8082/test/app/159460954939188692_萤石云图片2020-06-22 20_47_27.jpg
         * status : 2
         * repairerId : 17
         * reporterId : 17
         * repairTime : 2020-07-13 11:05:53
         * completeTime : 2020-07-13 11:06:36
         * groupId : null
         * groupUrl : null
         * repairerName : 唐力
         * reporterName : 唐力
         */

        private int id;
        private String userName;
        private String userPhone;
        private String workshopName;
        private String equipmentName;
        private String equipmentCode;
        private Object equipmentType;
        private String errorDetail;
        private String repairResult;
        private String repairImgUrl;
        private String lastMaintainTime;
        private String reportTime;
        private String errorImgUrl;
        private int status;                //1.运行 2.停机 3.保养 4.维修 5.故障
        private int repairerId;
        private int reporterId;
        private String repairTime;
        private String completeTime;
        private Object groupId;
        private Object groupUrl;
        private String repairerName;
        private String reporterName;
        private String equipmentModel;

        protected RepairEntity(Parcel in) {
            id = in.readInt();
            userName = in.readString();
            userPhone = in.readString();
            workshopName = in.readString();
            equipmentName = in.readString();
            equipmentCode = in.readString();
            errorDetail = in.readString();
            repairResult = in.readString();
            repairImgUrl = in.readString();
            lastMaintainTime = in.readString();
            reportTime = in.readString();
            errorImgUrl = in.readString();
            status = in.readInt();
            repairerId = in.readInt();
            reporterId = in.readInt();
            repairTime = in.readString();
            completeTime = in.readString();
            repairerName = in.readString();
            reporterName = in.readString();
            equipmentModel = in.readString();
        }

        public static final Creator<RepairEntity> CREATOR = new Creator<RepairEntity>() {
            @Override
            public RepairEntity createFromParcel(Parcel in) {
                return new RepairEntity(in);
            }

            @Override
            public RepairEntity[] newArray(int size) {
                return new RepairEntity[size];
            }
        };

        public String getEquipmentModel() {
            return equipmentModel;
        }

        public void setEquipmentModel(String equipmentModel) {
            this.equipmentModel = equipmentModel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getWorkshopName() {
            return workshopName;
        }

        public void setWorkshopName(String workshopName) {
            this.workshopName = workshopName;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public String getEquipmentCode() {
            return equipmentCode;
        }

        public void setEquipmentCode(String equipmentCode) {
            this.equipmentCode = equipmentCode;
        }

        public Object getEquipmentType() {
            return equipmentType;
        }

        public void setEquipmentType(Object equipmentType) {
            this.equipmentType = equipmentType;
        }

        public String getErrorDetail() {
            return errorDetail;
        }

        public void setErrorDetail(String errorDetail) {
            this.errorDetail = errorDetail;
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

        public String getLastMaintainTime() {
            return lastMaintainTime;
        }

        public void setLastMaintainTime(String lastMaintainTime) {
            this.lastMaintainTime = lastMaintainTime;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public String getErrorImgUrl() {
            return errorImgUrl;
        }

        public void setErrorImgUrl(String errorImgUrl) {
            this.errorImgUrl = errorImgUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRepairerId() {
            return repairerId;
        }

        public void setRepairerId(int repairerId) {
            this.repairerId = repairerId;
        }

        public int getReporterId() {
            return reporterId;
        }

        public void setReporterId(int reporterId) {
            this.reporterId = reporterId;
        }

        public String getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(String repairTime) {
            this.repairTime = repairTime;
        }

        public String getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(String completeTime) {
            this.completeTime = completeTime;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public Object getGroupUrl() {
            return groupUrl;
        }

        public void setGroupUrl(Object groupUrl) {
            this.groupUrl = groupUrl;
        }

        public String getRepairerName() {
            return repairerName;
        }

        public void setRepairerName(String repairerName) {
            this.repairerName = repairerName;
        }

        public String getReporterName() {
            return reporterName;
        }

        public void setReporterName(String reporterName) {
            this.reporterName = reporterName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(userName);
            dest.writeString(userPhone);
            dest.writeString(workshopName);
            dest.writeString(equipmentName);
            dest.writeString(equipmentCode);
            dest.writeString(errorDetail);
            dest.writeString(repairResult);
            dest.writeString(repairImgUrl);
            dest.writeString(lastMaintainTime);
            dest.writeString(reportTime);
            dest.writeString(errorImgUrl);
            dest.writeInt(status);
            dest.writeInt(repairerId);
            dest.writeInt(reporterId);
            dest.writeString(repairTime);
            dest.writeString(completeTime);
            dest.writeString(repairerName);
            dest.writeString(reporterName);
            dest.writeString(equipmentModel);
        }
    }


}
