package com.guyuan.dear.focus.transport.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 11:58
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportListBean {

    private int pageNum;
    private int pageSize;
    private int totalPages;
    private int totalSize;
    private String updateTime;

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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String acceptAddress;           //收货地址
        private String carNumber;               //车牌号码
        private int checkStatus;                //检查状态
        private String createTime;              //创建时间
        private int id;                         //主键ID
        private String motormanPhone;           //司机手机号码
        private String projectName;             //项目名称
        private String projectNumber;           //项目编号
        private String sendGoodsNumber;         //发货编码
        private int sumAmount;                  //物件总数
        private int transportStatus;            //运输状态

        public String getAcceptAddress() {
            return acceptAddress;
        }

        public void setAcceptAddress(String acceptAddress) {
            this.acceptAddress = acceptAddress;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMotormanPhone() {
            return motormanPhone;
        }

        public void setMotormanPhone(String motormanPhone) {
            this.motormanPhone = motormanPhone;
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

        public int getSumAmount() {
            return sumAmount;
        }

        public void setSumAmount(int sumAmount) {
            this.sumAmount = sumAmount;
        }

        public int getTransportStatus() {
            return transportStatus;
        }

        public void setTransportStatus(int transportStatus) {
            this.transportStatus = transportStatus;
        }
    }
}