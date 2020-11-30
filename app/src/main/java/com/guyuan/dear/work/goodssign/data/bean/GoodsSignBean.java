package com.guyuan.dear.work.goodssign.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/18 11:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignBean {
    private String code;                      //商品代号
    private int commodityId;                  //商品id
    private int contractId;                   //采购合同id
    private int createBy;                     //创建人
    private String createTime;                //创建时间
    private int delFlag;                      //逻辑删除标记：-1.已删除，0.未删除
    private String drawingDesignerName;       //图纸设计者
    private int exchangeNum;                  //换货数量
    private int exchangeSum;                  //换货总数
    private int id;                           //主键id
    private String material;                  //商品材质
    private String modelCode;                 //商品规格型号
    private String name;                      //商品名称
    private int number;                       //商品数量
    private String outsourcingTime;           //外购资料提供时间
    private double price;                        //商品单价
    private int productId;                    //父级配套设备id
    private int purchaseNum;                  //商品采购数量
    private int qualityResult;                //质检结果：1.合格，2.不合格
    private int qualityStatus;                //质检状态：1.已完成质检，2.未完成质检
    private int receiveNum;                   //到货数量（不另外计算退换货再到货的数量）
    private int receiveStatus;                //签收状态：1.待签收，2.已签收
    private String remark;                    //备注
    private int returnFlag;                   //有无退换货标记：0.无，1.有
    private int returnNum;                    //退货数量
    private int returnSum;                    //退货总数
    private String specication;               //规格（暂时不用）
    private int status;                       //即时状态：1.退货，2.换货，3.待签收，4.已到货，5.拖期
    private int taskType;                     //任务类型：1.自制生产2.外购生产3.部分外协4.总体设计
    private double totalPrice;                   //单条列表总价
    private int type;                         //商品类型：1.原材料，2.配套设备
    private String unit;                      //商品单位
    private int updateBy;                     //最后更新人
    private String updateTime;                //最后更新时间

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDrawingDesignerName() {
        return drawingDesignerName;
    }

    public void setDrawingDesignerName(String drawingDesignerName) {
        this.drawingDesignerName = drawingDesignerName;
    }

    public int getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(int exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

    public int getExchangeSum() {
        return exchangeSum;
    }

    public void setExchangeSum(int exchangeSum) {
        this.exchangeSum = exchangeSum;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOutsourcingTime() {
        return outsourcingTime;
    }

    public void setOutsourcingTime(String outsourcingTime) {
        this.outsourcingTime = outsourcingTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public int getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(int qualityResult) {
        this.qualityResult = qualityResult;
    }

    public int getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(int qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(int returnFlag) {
        this.returnFlag = returnFlag;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    public int getReturnSum() {
        return returnSum;
    }

    public void setReturnSum(int returnSum) {
        this.returnSum = returnSum;
    }

    public String getSpecication() {
        return specication;
    }

    public void setSpecication(String specication) {
        this.specication = specication;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}