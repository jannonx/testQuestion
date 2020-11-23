package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/23 18:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetQcSummaryBean {

    /**
     * equipmentData : {"totalNum":0,"qualifiedNum":0,"unqualifiedNum":0,"passRate":"0.0"}
     * materialsData : {"totalNum":0,"qualifiedNum":0,"unqualifiedNum":0,"passRate":"0.0%"}
     */

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
        /**
         * totalNum : 0
         * qualifiedNum : 0
         * unqualifiedNum : 0
         * passRate : 0.0
         */

        private int totalNum;
        private int qualifiedNum;
        private int unqualifiedNum;
        private String passRate;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getQualifiedNum() {
            return qualifiedNum;
        }

        public void setQualifiedNum(int qualifiedNum) {
            this.qualifiedNum = qualifiedNum;
        }

        public int getUnqualifiedNum() {
            return unqualifiedNum;
        }

        public void setUnqualifiedNum(int unqualifiedNum) {
            this.unqualifiedNum = unqualifiedNum;
        }

        public String getPassRate() {
            return passRate;
        }

        public void setPassRate(String passRate) {
            this.passRate = passRate;
        }
    }

    public static class MaterialsDataBean {
        /**
         * totalNum : 0
         * qualifiedNum : 0
         * unqualifiedNum : 0
         * passRate : 0.0%
         */

        private int totalNum;
        private int qualifiedNum;
        private int unqualifiedNum;
        private String passRate;

        public MaterialsDataBean() {
        }


        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getQualifiedNum() {
            return qualifiedNum;
        }

        public void setQualifiedNum(int qualifiedNum) {
            this.qualifiedNum = qualifiedNum;
        }

        public int getUnqualifiedNum() {
            return unqualifiedNum;
        }

        public void setUnqualifiedNum(int unqualifiedNum) {
            this.unqualifiedNum = unqualifiedNum;
        }

        public String getPassRate() {
            return passRate;
        }

        public void setPassRate(String passRate) {
            this.passRate = passRate;
        }
    }
}
