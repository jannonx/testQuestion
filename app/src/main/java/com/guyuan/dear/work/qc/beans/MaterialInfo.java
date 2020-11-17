package com.guyuan.dear.work.qc.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 14:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialInfo {
    private String materialName;
    private String materialId;
    private String materialType;
    private String comment;
    private int quantity;
    private String unit;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
