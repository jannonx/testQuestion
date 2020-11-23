package com.guyuan.dear.focus.transport.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 11:53
 * @company : 固远（深圳）信息技术有限公司
 **/


public class TransportDetailBean {

    private String acceptAddress;              //收货地址
    private String acceptUnit;                 //收货单位
    private String approveByList;              //审批人ID集合
    private String arrivePlanTime;             //预计到货时间
    private String carNumber;                  //车牌号码
    private String carType;                    //车型
    private String contactsName;               //联系人名称
    private String contactsPhone;              //联系人手机
    private String fileUrl;                    //附件(发货电子单据)
    private String gpsNumber;                  //gps传感器编码号
    private int id;                            //发货清单主键ID
    private String informByList;               //通知人ID集合字符串
    private String motormanPhone;              //司机手机号码
    private int projectId;                     //项目ID
    private String projectName;                //项目名称
    private String projectNumber;              //项目编号
    private String sendGoodsNumber;            //发货编码
    private int status;                        //状态(0.待审批 1.已同意 2.已拒绝)
    /**
     * amount : 0
     * id : 0
     * model :
     * projectCode :
     * projectName :
     * realityAmount : 0
     * remark :
     * transportId : 0
     * unit :
     */

    private List<TransportDetailVOListBean> transportDetailVOList;

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public String getAcceptUnit() {
        return acceptUnit;
    }

    public void setAcceptUnit(String acceptUnit) {
        this.acceptUnit = acceptUnit;
    }

    public String getApproveByList() {
        return approveByList;
    }

    public void setApproveByList(String approveByList) {
        this.approveByList = approveByList;
    }

    public String getArrivePlanTime() {
        return arrivePlanTime;
    }

    public void setArrivePlanTime(String arrivePlanTime) {
        this.arrivePlanTime = arrivePlanTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformByList() {
        return informByList;
    }

    public void setInformByList(String informByList) {
        this.informByList = informByList;
    }

    public String getMotormanPhone() {
        return motormanPhone;
    }

    public void setMotormanPhone(String motormanPhone) {
        this.motormanPhone = motormanPhone;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getSendGoodsNumber() {
        return sendGoodsNumber;
    }

    public void setSendGoodsNumber(String sendGoodsNumber) {
        this.sendGoodsNumber = sendGoodsNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TransportDetailVOListBean> getTransportDetailVOList() {
        return transportDetailVOList;
    }

    public void setTransportDetailVOList(List<TransportDetailVOListBean> transportDetailVOList) {
        this.transportDetailVOList = transportDetailVOList;
    }

    public static class TransportDetailVOListBean {
        private int amount;                   //数量
        private int id;                       //发货清单明细主键ID
        private String model;                 //规格型号
        private String projectCode;           //产品代号
        private String projectName;           //产品名称
        private int realityAmount;            //实际发货数量
        private String remark;                //备注
        private int transportId;              //运输清单主键ID
        private String unit;                  //单位

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public int getRealityAmount() {
            return realityAmount;
        }

        public void setRealityAmount(int realityAmount) {
            this.realityAmount = realityAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getTransportId() {
            return transportId;
        }

        public void setTransportId(int transportId) {
            this.transportId = transportId;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}