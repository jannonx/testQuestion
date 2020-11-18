package com.guyuan.dear.focus.purchase.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/16 14:09
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PurchaseOverviewBean {

    private EquipmentDataBean equipmentData;

    private MaterialsDataBean materialsData;

    public EquipmentDataBean getEquipmentData() {
        return equipmentData;
    }

    public void setEquipmentData(EquipmentDataBean equipmentData) {
        this.equipmentData = equipmentData;
    }

    public MaterialsDataBean getMaterialsData() {
        return materialsData;
    }

    public void setMaterialsData(MaterialsDataBean materialsData) {
        this.materialsData = materialsData;
    }

    public static class EquipmentDataBean {
        private int exchangeNum;           //采购原材料换货数
        private int exchangeRate;          //采购原材料换货率
        private int returnNum;             //采购原材料退货数
        private int returnRate;            //采购原材料退货率
        private int totalNum;              //采购原材料总数

        public int getExchangeNum() {
            return exchangeNum;
        }

        public void setExchangeNum(int exchangeNum) {
            this.exchangeNum = exchangeNum;
        }

        public int getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(int exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public int getReturnNum() {
            return returnNum;
        }

        public void setReturnNum(int returnNum) {
            this.returnNum = returnNum;
        }

        public int getReturnRate() {
            return returnRate;
        }

        public void setReturnRate(int returnRate) {
            this.returnRate = returnRate;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }
    }

    public static class MaterialsDataBean {
        private int exchangeNum;            //采购原材料换货数
        private int exchangeRate;           //采购原材料换货率
        private int returnNum;              //采购原材料退货数
        private int returnRate;             //采购原材料退货率
        private int totalNum;               //采购原材料总数

        public int getExchangeNum() {
            return exchangeNum;
        }

        public void setExchangeNum(int exchangeNum) {
            this.exchangeNum = exchangeNum;
        }

        public int getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(int exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public int getReturnNum() {
            return returnNum;
        }

        public void setReturnNum(int returnNum) {
            this.returnNum = returnNum;
        }

        public int getReturnRate() {
            return returnRate;
        }

        public void setReturnRate(int returnRate) {
            this.returnRate = returnRate;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }
    }
}