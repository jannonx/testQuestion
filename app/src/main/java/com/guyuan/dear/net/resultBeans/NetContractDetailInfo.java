package com.guyuan.dear.net.resultBeans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/20 18:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractDetailInfo extends NetContractInfo {

    /**
     * totalAmount : 10000000
     * qualityDeposit : 22222
     * receivingUnit : 测试12
     * deliveryAddress : 测试12
     * consignee : 测试12
     * stopStatus : 1
     * contactInfo : 测试12
     * createTime : 2020-11-02 07:48:01
     * signTime : 2020-10-29 16:00:00
     * tradeReceivables : null
     * balance : null
     * salesman : 许建宁
     * approvedBy : null
     * fileUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160430353609853231.docx
     * examinationTime : null
     * tcontractParts : [{"id":8970,"name":"配套1","specifications":"PT1","model":"PT1","company":"件","num":11123},{"id":8971,"name":"PT2","specifications":"ce","model":"pt2","company":"件","num":2222}]
     */

    private int totalAmount;
    private int qualityDeposit;
    private String receivingUnit;
    private String deliveryAddress;
    private String consignee;
    private int stopStatus;
    private String contactInfo;
    private String createTime;
    private String salesman;
    private String fileUrl;
    private List<TcontractPartsBean> tcontractParts;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQualityDeposit() {
        return qualityDeposit;
    }

    public void setQualityDeposit(int qualityDeposit) {
        this.qualityDeposit = qualityDeposit;
    }

    public String getReceivingUnit() {
        return receivingUnit;
    }

    public void setReceivingUnit(String receivingUnit) {
        this.receivingUnit = receivingUnit;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }


    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public List<TcontractPartsBean> getTcontractParts() {
        return tcontractParts;
    }

    public void setTcontractParts(List<TcontractPartsBean> tcontractParts) {
        this.tcontractParts = tcontractParts;
    }

    public static class TcontractPartsBean {
        /**
         * id : 8970
         * name : 配套1
         * specifications : PT1
         * model : PT1
         * company : 件
         * num : 11123
         */

        private int id;
        private String name;
        private String specifications;
        private String model;
        private String company;
        private long num;

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

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public long getNum() {
            return num;
        }

        public void setNum(long num) {
            this.num = num;
        }
    }
}
