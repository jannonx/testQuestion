package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 11:16
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetProductInfo {

    /**
     * approvalStatus : 0
     * createBy : 0
     * createTime :
     * delFlag : 0
     * equipmentId : 0
     * id : 0
     * mainId : 0
     * material :
     * model :
     * productCode :
     * productName :
     * productNum : 0
     * productType : 0
     * productUnit :
     * projectId : 0
     * qualityBy : 0
     * qualityCondition :
     * qualityNum : 0
     * qualityRemark :
     * qualityResult : 0
     * qualityType : 0
     * serialNumber :
     * updateBy : 0
     * updateTime :
     */

    private int id;
    private String productCode;
    private String productName;
    private long productNum;
    private String productUnit;
    private String serialNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductNum() {
        return productNum;
    }

    public void setProductNum(long productNum) {
        this.productNum = productNum;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
