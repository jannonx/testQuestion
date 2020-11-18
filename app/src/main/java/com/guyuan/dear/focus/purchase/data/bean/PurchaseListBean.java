package com.guyuan.dear.focus.purchase.data.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/16 14:41
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PurchaseListBean {

    /**
     * content : [{"abnormalSum":0,"abnormalType":0,"arriveTime":"","code":"","createName":"","createTime":"","id":0,"material":"","modelCode":"","name":"","suppName":"","type":0}]
     * pageNum : 0
     * pageSize : 0
     * totalPages : 0
     * totalSize : 0
     * updateTime :
     */

    private int pageNum;
    private int pageSize;
    private int totalPages;
    private int totalSize;
    private String updateTime;
    /**
     * abnormalSum : 0
     * abnormalType : 0
     * arriveTime :
     * code :
     * createName :
     * createTime :
     * id : 0
     * material :
     * modelCode :
     * name :
     * suppName :
     * type : 0
     */

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
        private int abnormalSum;         //退换货总数
        private int abnormalType;        //异常类型：1.退货，2.换货，3.拖期
        private String arriveTime;       //采购合同到货时间
        private String code;             //商品代号
        private String createName;       //操作人（采购合同创建人）
        private String createTime;       //采购合同创建时间
        private int id;                  //主键id
        private String material;         //商品材质
        private String modelCode;        //商品规格型号
        private String name;             //商品名称
        private String suppName;         //供应商名称
        private int type;                //商品类型：1.原材料，2.配套设备
        private String qualityResult;    //质检结果

        public String getQualityResult() {
            return qualityResult;
        }

        public void setQualityResult(String qualityResult) {
            this.qualityResult = qualityResult;
        }

        public int getAbnormalSum() {
            return abnormalSum;
        }

        public void setAbnormalSum(int abnormalSum) {
            this.abnormalSum = abnormalSum;
        }

        public int getAbnormalType() {
            return abnormalType;
        }

        public void setAbnormalType(int abnormalType) {
            this.abnormalType = abnormalType;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
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

        public String getSuppName() {
            return suppName;
        }

        public void setSuppName(String suppName) {
            this.suppName = suppName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}