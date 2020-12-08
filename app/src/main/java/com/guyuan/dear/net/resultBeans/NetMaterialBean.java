package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 17:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetMaterialBean {


    /**
     * approvalStatus : 0
     * equipmentId : 0
     * id : 0
     * material :
     * model :
     * productCode :
     * productName :
     * productNum : 0
     * productType : 0
     * productUnit :
     * qualityBy : 0
     * qualityName :
     * qualityNum : 0
     * qualityResult : 0
     * remarks :
     * serialNumber :
     */

    private int id;//只需要提交id
    private String material;//
    private String model;//型号
    private String productCode;//
    private String productName;//
    private long productNum;//
    private String productUnit;//
    private String remarks;//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
