package com.guyuan.dear.work.qc.beans;

import com.guyuan.dear.net.resultBeans.NetMaterialBean;

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
    private long quantity;
    private String unit;
    private int id;
    private String spec;

    public MaterialInfo() {
    }

    public MaterialInfo(NetMaterialBean bean) {
        setMaterialName(bean.getProductName());
        setMaterialType(bean.getMaterial());
        setMaterialId(bean.getProductCode());
        setId(bean.getId());
        setComment(bean.getRemarks());
        setQuantity(bean.getProductNum());
        setUnit(bean.getProductUnit());
        setSpec(bean.getModel());
    }


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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
