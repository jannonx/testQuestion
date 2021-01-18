package com.guyuan.dear.base.bean;

import com.sun.jna.platform.win32.Winspool;

/**
 * @author : 唐力
 * @description :
 * @since: 2021/1/7 14:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ContractStatusBean {

    /**
     * contractNum :
     * contractStopCauseVO : {"auditName":"","dimensionality":"","stopCause":"","stopTime":""}
     * id : 0
     * stopStatus : 0
     */

    private String contractNum;
    /**
     * auditName :
     * dimensionality :
     * stopCause :
     * stopTime :
     */

    private ContractStopCauseVOBean contractStopCauseVO;
    private int id;
    private int stopStatus;
    private int contractTextID;

    public int getContractTextID() {
        return contractTextID;
    }

    public void setContractTextID(int contractTextID) {
        this.contractTextID = contractTextID;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public ContractStopCauseVOBean getContractStopCauseVO() {
        return contractStopCauseVO;
    }

    public void setContractStopCauseVO(ContractStopCauseVOBean contractStopCauseVO) {
        this.contractStopCauseVO = contractStopCauseVO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(int stopStatus) {
        this.stopStatus = stopStatus;
    }

    public static class ContractStopCauseVOBean {
        private String auditName;
        private String dimensionality;
        private String stopCause;
        private String stopTime;

        public String getAuditName() {
            return auditName;
        }

        public void setAuditName(String auditName) {
            this.auditName = auditName;
        }

        public String getDimensionality() {
            return dimensionality;
        }

        public void setDimensionality(String dimensionality) {
            this.dimensionality = dimensionality;
        }

        public String getStopCause() {
            return stopCause;
        }

        public void setStopCause(String stopCause) {
            this.stopCause = stopCause;
        }

        public String getStopTime() {
            return stopTime;
        }

        public void setStopTime(String stopTime) {
            this.stopTime = stopTime;
        }
    }
}