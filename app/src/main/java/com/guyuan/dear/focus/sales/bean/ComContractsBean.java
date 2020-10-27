package com.guyuan.dear.focus.sales.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 17:52
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsBean {
    /**
     * 客户总数
     */
    private int clientTotal;
    /**
     * 合同订单总数
     */
    private int contractsTotal;
    /**
     * 上年交付
     */
    private int preAnnualDelivers;
    /**
     * 今年新签
     */
    private int newContracts;
    /**
     * 已经完成
     */
    private int finishedContracts;
    /**
     * 正在执行
     */
    private int executingContracts;
    /**
     * 未完成
     */
    private int unfinishedContracts;
    /**
     * 执行异常
     */
    private int exceptionContracts;

    public int getClientTotal() {
        return clientTotal;
    }

    public void setClientTotal(int clientTotal) {
        this.clientTotal = clientTotal;
    }

    public int getContractsTotal() {
        return contractsTotal;
    }

    public void setContractsTotal(int contractsTotal) {
        this.contractsTotal = contractsTotal;
    }

    public int getPreAnnualDelivers() {
        return preAnnualDelivers;
    }

    public void setPreAnnualDelivers(int preAnnualDelivers) {
        this.preAnnualDelivers = preAnnualDelivers;
    }

    public int getNewContracts() {
        return newContracts;
    }

    public void setNewContracts(int newContracts) {
        this.newContracts = newContracts;
    }

    public int getFinishedContracts() {
        return finishedContracts;
    }

    public void setFinishedContracts(int finishedContracts) {
        this.finishedContracts = finishedContracts;
    }

    public int getExecutingContracts() {
        return executingContracts;
    }

    public void setExecutingContracts(int executingContracts) {
        this.executingContracts = executingContracts;
    }

    public int getUnfinishedContracts() {
        return unfinishedContracts;
    }

    public void setUnfinishedContracts(int unfinishedContracts) {
        this.unfinishedContracts = unfinishedContracts;
    }

    public int getExceptionContracts() {
        return exceptionContracts;
    }

    public void setExceptionContracts(int exceptionContracts) {
        this.exceptionContracts = exceptionContracts;
    }
}
