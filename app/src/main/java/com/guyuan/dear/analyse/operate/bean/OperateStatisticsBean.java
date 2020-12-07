package com.guyuan.dear.analyse.operate.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/30 19:59
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateStatisticsBean implements Serializable {

    private List<PaymentCollectionVOSBean> paymentCollectionVOS;
    private List<CostVOSBean> costVOS;

    public List<PaymentCollectionVOSBean> getPaymentCollectionVOS() {
        return paymentCollectionVOS;
    }

    public void setPaymentCollectionVOS(List<PaymentCollectionVOSBean> paymentCollectionVOS) {
        this.paymentCollectionVOS = paymentCollectionVOS;
    }

    public List<CostVOSBean> getCostVOS() {
        return costVOS;
    }

    public void setCostVOS(List<CostVOSBean> costVOS) {
        this.costVOS = costVOS;
    }

    public static class PaymentCollectionVOSBean implements Serializable {
        /**
         * paymentCollection : 11111112
         * month : 2020-11
         */

        private float paymentCollection;
        private String month;

        public float getPaymentCollection() {
            return paymentCollection;
        }

        public void setPaymentCollection(float paymentCollection) {
            this.paymentCollection = paymentCollection;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }

    public static class CostVOSBean implements Serializable {
        /**
         * cost : 6333333
         * month : 2020-11
         */

        private float cost;
        private String month;

        public float getCost() {
            return cost;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }
}
