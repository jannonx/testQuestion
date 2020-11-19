package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/18 15:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetBaseContractInfo {

    /**
     * id : 8976
     * equipmentName : 测试
     * equipmentModel : 0122
     * contractNum : 0112
     */

    private int id;
    private String equipmentName;
    private String equipmentModel;
    private String contractNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }
}
