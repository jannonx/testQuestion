package com.guyuan.dear.net.resultBeans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 14:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractStatusFlow {
    /**
     * acceptance : 0
     * collection : 0
     * completion : 0
     * contractErrors : 0
     * contractReview : 0
     * cuntTheGoods : 0
     * designInstitute : 0
     * install : 0
     * name :
     * productionPlan : 0
     * purchase : 0
     * qualityDeposit : 0
     * qualityTesting : 0
     * reason :
     * transport : 0
     */

    /**
     * 客户验收:0：未开始，1：进行中，2：已完成
     */
    private int acceptance;
    /**
     *客户回款:0：未开始，1：进行中，2：已完成
     */
    private int collection;
    /**
     *合同完成:0：未开始，1：进行中，2：已完成
     */
    private int completion;
    /**
     *合同异常状态:0：正常，1：合同暂停，2：质保金异常
     */
    private int contractErrors;
    /**
     *合同审批:0：未开始，1：进行中，2：已完成
     */
    private int contractReview;
    /**
     *清点货物:0：未开始，1：进行中，2：已完成
     */
    private int cuntTheGoods;
    /**
     *设计院:0：未开始，1：进行中，2：已完成
     */
    private int designInstitute;
    /**
     *安装调试:0：未开始，1：进行中，2：已完成
     */
    private int install;
    /**
     *质保金异常审批通过姓名
     */
    private String name;
    /**
     *生产计划:0：未开始，1：进行中，2：已完成
     */
    private int productionPlan;
    /**
     *采购:0：未开始，1：进行中，2：已完成
     */
    private int purchase;
    /**
     *质保金:0：未开始，1：进行中，2：已完成
     */
    private int qualityDeposit;
    /**
     *质检:0：未开始，1：进行中，2：已完成
     */
    private int qualityTesting;
    /**
     *质保金异常原因
     */
    private String reason;
    /**
     *运输:0：未开始，1：进行中，2：已完成
     */
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

    public int getContractErrors() {
        return contractErrors;
    }

    public void setContractErrors(int contractErrors) {
        this.contractErrors = contractErrors;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTransport() {
        return transport;
    }

    public void setTransport(int transport) {
        this.transport = transport;
    }


//    /**
//     * contractAbnormalQualityVO : {"approvedBy":"","createTime":"","remark":""}
//     * contractAbnormalSuspendVO : {"approvedBy":"","createTime":"","remark":"","remark1":""}
//     * contractExecutionVO : {"acceptance":0,"collection":0,"completion":0,"contractReview":0,"cuntTheGoods":0,"designInstitute":0,"install":0,"productionPlan":0,"purchase":0,"qualityDeposit":0,"qualityTesting":0,"transport":0}
//     */
//
//    /**
//     * 质保金
//     */
//    private ContractAbnormalQualityVOBean contractAbnormalQualityVO;
//    /**
//     * 合同暂停
//     */
//    private ContractAbnormalSuspendVOBean contractAbnormalSuspendVO;
//    /**
//     * 执行流程各个流程点
//     */
//    private ContractExecutionVOBean contractExecutionVO;
//
//    public ContractAbnormalQualityVOBean getContractAbnormalQualityVO() {
//        return contractAbnormalQualityVO;
//    }
//
//    public void setContractAbnormalQualityVO(ContractAbnormalQualityVOBean contractAbnormalQualityVO) {
//        this.contractAbnormalQualityVO = contractAbnormalQualityVO;
//    }
//
//    public ContractAbnormalSuspendVOBean getContractAbnormalSuspendVO() {
//        return contractAbnormalSuspendVO;
//    }
//
//    public void setContractAbnormalSuspendVO(ContractAbnormalSuspendVOBean contractAbnormalSuspendVO) {
//        this.contractAbnormalSuspendVO = contractAbnormalSuspendVO;
//    }
//
//    public ContractExecutionVOBean getContractExecutionVO() {
//        return contractExecutionVO;
//    }
//
//    public void setContractExecutionVO(ContractExecutionVOBean contractExecutionVO) {
//        this.contractExecutionVO = contractExecutionVO;
//    }
//
//    public static class ContractAbnormalQualityVOBean {
//        /**
//         * approvedBy :
//         * createTime :
//         * remark :
//         */
//
//        private String approvedBy;
//        private String createTime;
//        private String remark;
//
//        public String getApprovedBy() {
//            return approvedBy;
//        }
//
//        public void setApprovedBy(String approvedBy) {
//            this.approvedBy = approvedBy;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//    }
//
//    public static class ContractAbnormalSuspendVOBean {
//        /**
//         * approvedBy :
//         * createTime :
//         * remark :
//         * remark1 :
//         */
//
//        private String approvedBy;
//        private String createTime;
//        private String remark;
//        private String remark1;
//
//        public String getApprovedBy() {
//            return approvedBy;
//        }
//
//        public void setApprovedBy(String approvedBy) {
//            this.approvedBy = approvedBy;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public String getRemark1() {
//            return remark1;
//        }
//
//        public void setRemark1(String remark1) {
//            this.remark1 = remark1;
//        }
//    }
//
//    public static class ContractExecutionVOBean {
//        /**
//         * acceptance : 0
//         * collection : 0
//         * completion : 0
//         * contractReview : 0
//         * cuntTheGoods : 0
//         * designInstitute : 0
//         * install : 0
//         * productionPlan : 0
//         * purchase : 0
//         * qualityDeposit : 0
//         * qualityTesting : 0
//         * transport : 0
//         */
//
//        private int acceptance;
//        private int collection;
//        private int completion;
//        private int contractReview;
//        private int cuntTheGoods;
//        private int designInstitute;
//        private int install;
//        private int productionPlan;
//        private int purchase;
//        private int qualityDeposit;
//        private int qualityTesting;
//        private int transport;
//
//        public int getAcceptance() {
//            return acceptance;
//        }
//
//        public void setAcceptance(int acceptance) {
//            this.acceptance = acceptance;
//        }
//
//        public int getCollection() {
//            return collection;
//        }
//
//        public void setCollection(int collection) {
//            this.collection = collection;
//        }
//
//        public int getCompletion() {
//            return completion;
//        }
//
//        public void setCompletion(int completion) {
//            this.completion = completion;
//        }
//
//        public int getContractReview() {
//            return contractReview;
//        }
//
//        public void setContractReview(int contractReview) {
//            this.contractReview = contractReview;
//        }
//
//        public int getCuntTheGoods() {
//            return cuntTheGoods;
//        }
//
//        public void setCuntTheGoods(int cuntTheGoods) {
//            this.cuntTheGoods = cuntTheGoods;
//        }
//
//        public int getDesignInstitute() {
//            return designInstitute;
//        }
//
//        public void setDesignInstitute(int designInstitute) {
//            this.designInstitute = designInstitute;
//        }
//
//        public int getInstall() {
//            return install;
//        }
//
//        public void setInstall(int install) {
//            this.install = install;
//        }
//
//        public int getProductionPlan() {
//            return productionPlan;
//        }
//
//        public void setProductionPlan(int productionPlan) {
//            this.productionPlan = productionPlan;
//        }
//
//        public int getPurchase() {
//            return purchase;
//        }
//
//        public void setPurchase(int purchase) {
//            this.purchase = purchase;
//        }
//
//        public int getQualityDeposit() {
//            return qualityDeposit;
//        }
//
//        public void setQualityDeposit(int qualityDeposit) {
//            this.qualityDeposit = qualityDeposit;
//        }
//
//        public int getQualityTesting() {
//            return qualityTesting;
//        }
//
//        public void setQualityTesting(int qualityTesting) {
//            this.qualityTesting = qualityTesting;
//        }
//
//        public int getTransport() {
//            return transport;
//        }
//
//        public void setTransport(int transport) {
//            this.transport = transport;
//        }
//    }
}
