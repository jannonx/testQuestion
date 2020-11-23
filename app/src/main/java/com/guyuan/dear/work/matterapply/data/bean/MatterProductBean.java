package com.guyuan.dear.work.matterapply.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 20:36
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterProductBean {

    /**
     * code :
     * id : 0
     * material :
     * modelCode :
     * name :
     * purchaseNum : 0
     * remark :
     * unit :
     */

    private String code;             //商品代号
    private int id;                  //商品id
    private String material;         //商品材质
    private String modelCode;        //商品规格型号
    private String name;             //商品名称
    private int purchaseNum;         //商品采购数量
    private String remark;           //备注
    private String unit;             //商品单位

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}