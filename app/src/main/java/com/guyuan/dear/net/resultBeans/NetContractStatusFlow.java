package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 14:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractStatusFlow {

    /**
     * contractAbnormalQualityVO : {"approvedBy":"","createTime":"","remark":""}
     * contractAbnormalSuspendVO : {"approvedBy":"","createTime":"","remark":"","remark1":""}
     * contractExecutionVO : {"acceptance":0,"collection":0,"completion":0,"contractReview":0,"cuntTheGoods":0,"designInstitute":0,"install":0,"productionPlan":0,"purchase":0,"qualityDeposit":0,"qualityTesting":0,"transport":0}
     */

    /**
     * 质保金
     */
    private ContractAbnormalQualityVOBean contractAbnormalQualityVO;
    /**
     * 合同暂停
     */
    private ContractAbnormalSuspendVOBean contractAbnormalSuspendVO;
    /**
     * 执行流程各个流程点
     */
    private ContractExecutionVOBean contractExecutionVO;

    public ContractAbnormalQualityVOBean getContractAbnormalQualityVO() {
        return contractAbnormalQualityVO;
    }

    public void setContractAbnormalQualityVO(ContractAbnormalQualityVOBean contractAbnormalQualityVO) {
        this.contractAbnormalQualityVO = contractAbnormalQualityVO;
    }

    public ContractAbnormalSuspendVOBean getContractAbnormalSuspendVO() {
        return contractAbnormalSuspendVO;
    }

    public void setContractAbnormalSuspendVO(ContractAbnormalSuspendVOBean contractAbnormalSuspendVO) {
        this.contractAbnormalSuspendVO = contractAbnormalSuspendVO;
    }

    public ContractExecutionVOBean getContractExecutionVO() {
        return contractExecutionVO;
    }

    public void setContractExecutionVO(ContractExecutionVOBean contractExecutionVO) {
        this.contractExecutionVO = contractExecutionVO;
    }

    public static class ContractAbnormalQualityVOBean {
        /**
         * approvedBy :
         * createTime :
         * remark :
         */

        private String approvedBy;
        private String createTime;
        private String remark;

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class ContractAbnormalSuspendVOBean {
        /**
         * approvedBy :
         * createTime :
         * remark :
         * remark1 :
         */

        private String approvedBy;
        private String createTime;
        private String remark;
        private String remark1;

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark1() {
            return remark1;
        }

        public void setRemark1(String remark1) {
            this.remark1 = remark1;
        }
    }

    public static class ContractExecutionVOBean {
        /**
         * acceptance : 0
         * collection : 0
         * completion : 0
         * contractReview : 0
         * cuntTheGoods : 0
         * designInstitute : 0
         * install : 0
         * productionPlan : 0
         * purchase : 0
         * qualityDeposit : 0
         * qualityTesting : 0
         * transport : 0
         */

        private int acceptance;
        private int collection;
        private int completion;
        private int contractReview;
        private int cuntTheGoods;
        private int designInstitute;
        private int install;
        private int productionPlan;
        private int purchase;
        private int qualityDeposit;
        private int qualityTesting;
        private int transport;

        public int getAcceptance() {
            return acceptance;
        }

        public void setAcceptance(int acceptance) {
            this.acceptance = acceptance;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getCompletion() {
            return completion;
        }

        public void setCompletion(int completion) {
            this.completion = completion;
        }

        public int getContractReview() {
            return contractReview;
        }

        public void setContractReview(int contractReview) {
            this.contractReview = contractReview;
        }

        public int getCuntTheGoods() {
            return cuntTheGoods;
        }

        public void setCuntTheGoods(int cuntTheGoods) {
            this.cuntTheGoods = cuntTheGoods;
        }

        public int getDesignInstitute() {
            return designInstitute;
        }

        public void setDesignInstitute(int designInstitute) {
            this.designInstitute = designInstitute;
        }

        public int getInstall() {
            return install;
        }

        public void setInstall(int install) {
            this.install = install;
        }

        public int getProductionPlan() {
            return productionPlan;
        }

        public void setProductionPlan(int productionPlan) {
            this.productionPlan = productionPlan;
        }

        public int getPurchase() {
            return purchase;
        }

        public void setPurchase(int purchase) {
            this.purchase = purchase;
        }

        public int getQualityDeposit() {
            return qualityDeposit;
        }

        public void setQualityDeposit(int qualityDeposit) {
            this.qualityDeposit = qualityDeposit;
        }

        public int getQualityTesting() {
            return qualityTesting;
        }

        public void setQualityTesting(int qualityTesting) {
            this.qualityTesting = qualityTesting;
        }

        public int getTransport() {
            return transport;
        }

        public void setTransport(int transport) {
            this.transport = transport;
        }
    }
}
