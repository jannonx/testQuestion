package com.guyuan.dear.net.resultBeans;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/4 18:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractHistory {

    /**
     * contractRestartVOS : [{"approvName":"","createTime":"","id":0,"remark":""}]
     * contractStopVOS : [{"approvName":"","createTime":"","id":0,"remark":"","remark1":""}]
     * customerAcceptanceVO : [{"checkName":"","checkRemark":"","checkTime":"","checkUrl":""}]
     * id : 0
     * paymentSumVO : {"sumTrade":0,"timeTrade":""}
     * qualityAbnormaVO : {"qualityAbnormalBy":"","qualityAbnormalCauses":"","qualityAbnormalTime":""}
     * qualitySumVO : {"sumQuality":0,"timeQuality":""}
     */

    private int id;
    /**
     * 回款历史
     */
    private PaymentSumVOBean paymentSumVO;
    /**
     * 质保金异常
     */
    private QualityAbnormaVOBean qualityAbnormaVO;
    /**
     * 质保金历史
     */
    private QualitySumVOBean qualitySumVO;
    /**
     *
     * 重启记录
     */
    private List<ContractRestartVOSBean> contractRestartVOS;
    /**
     * 暂停记录
     */
    private List<ContractStopVOSBean> contractStopVOS;
    /**
     * 客户验收
     */
    private List<CustomerAcceptanceVOBean> customerAcceptanceVO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentSumVOBean getPaymentSumVO() {
        return paymentSumVO;
    }

    public void setPaymentSumVO(PaymentSumVOBean paymentSumVO) {
        this.paymentSumVO = paymentSumVO;
    }

    public QualityAbnormaVOBean getQualityAbnormaVO() {
        return qualityAbnormaVO;
    }

    public void setQualityAbnormaVO(QualityAbnormaVOBean qualityAbnormaVO) {
        this.qualityAbnormaVO = qualityAbnormaVO;
    }

    public QualitySumVOBean getQualitySumVO() {
        return qualitySumVO;
    }

    public void setQualitySumVO(QualitySumVOBean qualitySumVO) {
        this.qualitySumVO = qualitySumVO;
    }

    public List<ContractRestartVOSBean> getContractRestartVOS() {
        return contractRestartVOS;
    }

    public void setContractRestartVOS(List<ContractRestartVOSBean> contractRestartVOS) {
        this.contractRestartVOS = contractRestartVOS;
    }

    public List<ContractStopVOSBean> getContractStopVOS() {
        return contractStopVOS;
    }

    public void setContractStopVOS(List<ContractStopVOSBean> contractStopVOS) {
        this.contractStopVOS = contractStopVOS;
    }

    public List<CustomerAcceptanceVOBean> getCustomerAcceptanceVO() {
        return customerAcceptanceVO;
    }

    public void setCustomerAcceptanceVO(List<CustomerAcceptanceVOBean> customerAcceptanceVO) {
        this.customerAcceptanceVO = customerAcceptanceVO;
    }

    public static class PaymentSumVOBean {
        /**
         * sumTrade : 0
         * timeTrade :
         */

        /**
         * 回款总额
         */
        private int sumTrade;
        /**
         * 回款最新时间
         */
        private String timeTrade;

        public int getSumTrade() {
            return sumTrade;
        }

        public void setSumTrade(int sumTrade) {
            this.sumTrade = sumTrade;
        }

        public String getTimeTrade() {
            return timeTrade;
        }

        public void setTimeTrade(String timeTrade) {
            this.timeTrade = timeTrade;
        }
    }

    public static class QualityAbnormaVOBean {
        /**
         * qualityAbnormalBy :
         * qualityAbnormalCauses :
         * qualityAbnormalTime :
         */

        /**
         * 质保金异常审批人
         */
        private String qualityAbnormalBy;
        /**
         *质保金异常原因
         */
        private String qualityAbnormalCauses;
        /**
         *质保金异常时间
         */
        private String qualityAbnormalTime;

        public String getQualityAbnormalBy() {
            return qualityAbnormalBy;
        }

        public void setQualityAbnormalBy(String qualityAbnormalBy) {
            this.qualityAbnormalBy = qualityAbnormalBy;
        }

        public String getQualityAbnormalCauses() {
            return qualityAbnormalCauses;
        }

        public void setQualityAbnormalCauses(String qualityAbnormalCauses) {
            this.qualityAbnormalCauses = qualityAbnormalCauses;
        }

        public String getQualityAbnormalTime() {
            return qualityAbnormalTime;
        }

        public void setQualityAbnormalTime(String qualityAbnormalTime) {
            this.qualityAbnormalTime = qualityAbnormalTime;
        }
    }

    public static class QualitySumVOBean {
        /**
         * sumQuality : 0
         * timeQuality :
         */

        /**
         * 质保金总额
         */
        private int sumQuality;
        /**
         * 质保金最新时间
         */
        private String timeQuality;

        public int getSumQuality() {
            return sumQuality;
        }

        public void setSumQuality(int sumQuality) {
            this.sumQuality = sumQuality;
        }

        public String getTimeQuality() {
            return timeQuality;
        }

        public void setTimeQuality(String timeQuality) {
            this.timeQuality = timeQuality;
        }
    }

    public static class ContractRestartVOSBean {
        /**
         * approvName :
         * createTime :
         * id : 0
         * remark :
         */

        /**
         * 审批人
         */
        private String approvName;
        private String createTime;
        private int id;
        /**
         * 重启原因
         */
        private String remark;

        public String getApprovName() {
            return approvName;
        }

        public void setApprovName(String approvName) {
            this.approvName = approvName;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class ContractStopVOSBean {
        /**
         * approvName :
         * createTime :
         * id : 0
         * remark :
         * remark1 :
         */

        /**
         * 审批人
         */
        private String approvName;
        private String createTime;
        private int id;
        /**
         * 暂停原因
         */
        private String remark;
        /**
         * 暂停维度
         */
        private String remark1;

        public String getApprovName() {
            return approvName;
        }

        public void setApprovName(String approvName) {
            this.approvName = approvName;
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

    public static class CustomerAcceptanceVOBean {
        /**
         * checkName :
         * checkRemark :
         * checkTime :
         * checkUrl :
         */

        /**
         * 客户验收人员
         */
        private String checkName;
        /**
         * 验收备注
         */
        private String checkRemark;
        private String checkTime;
        /**
         * 客户验收图片，可以多张，以逗号分开
         */
        private String checkUrl;
        /**
         * 验收状态，0:待验收，10合格，20不合格
         */
        private int checkStatus;

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }

        public String getCheckRemark() {
            return checkRemark;
        }

        public void setCheckRemark(String checkRemark) {
            this.checkRemark = checkRemark;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckUrl() {
            return checkUrl;
        }

        public void setCheckUrl(String checkUrl) {
            this.checkUrl = checkUrl;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }
    }
}
