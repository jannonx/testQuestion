package com.guyuan.dear.focus.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :我的关注-评审概览bean
 * @since: 2020/10/27 10:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessOverviewBean {

    /**
     * auditYearInfoVOList : [{"date":"","noPass":0,"pass":0}]
     * noPassSumNumber : 0
     * passRate :
     * passSumNumber : 0
     * sellOrderSumNumber : 0
     */

    private int noPassSumNumber;            //不通过数量
    private String passRate;                //通过率
    private int passSumNumber;              //通过数量
    private int sellOrderSumNumber;         //评审次数
    /**
     * date :
     * noPass : 0
     * pass : 0
     */

    private List<AuditYearInfoVOListBean> auditYearInfoVOList;

    public int getNoPassSumNumber() {
        return noPassSumNumber;
    }

    public void setNoPassSumNumber(int noPassSumNumber) {
        this.noPassSumNumber = noPassSumNumber;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public int getPassSumNumber() {
        return passSumNumber;
    }

    public void setPassSumNumber(int passSumNumber) {
        this.passSumNumber = passSumNumber;
    }

    public int getSellOrderSumNumber() {
        return sellOrderSumNumber;
    }

    public void setSellOrderSumNumber(int sellOrderSumNumber) {
        this.sellOrderSumNumber = sellOrderSumNumber;
    }

    public List<AuditYearInfoVOListBean> getAuditYearInfoVOList() {
        return auditYearInfoVOList;
    }

    public void setAuditYearInfoVOList(List<AuditYearInfoVOListBean> auditYearInfoVOList) {
        this.auditYearInfoVOList = auditYearInfoVOList;
    }

    public static class AuditYearInfoVOListBean {
        private String date;       //年度
        private int noPass;        //不通过
        private int pass;          //通过

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getNoPass() {
            return noPass;
        }

        public void setNoPass(int noPass) {
            this.noPass = noPass;
        }

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }
    }
}