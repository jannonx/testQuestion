package com.guyuan.dear.focus.produce.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 提交操作生产信息体
 * @author: 许建宁
 * @since: 2020/11/11 15:43
 * @company: 固远（深圳）信息技术有限公司
 */
public class ExecuteRequestBody implements Serializable {
    /**
     * 标识id
     */
    private long id;
    /**
     * 审批人id的集合
     */
    private List<Long> approvers;
    /**
     * 配套设备id
     */
    private long equipmentId;
    /**
     * 抄送人id的集合
     */
    private List<Long> noticedPeople;
    /**
     * 申请原因
     */
    private String reason;
    /**
     * 操作类型：1.点击开始，2.点击完成，3.点击暂停，4.点击激活
     */
    private long type;

    public List<Long> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<Long> approvers) {
        this.approvers = approvers;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public List<Long> getNoticedPeople() {
        return noticedPeople;
    }

    public void setNoticedPeople(List<Long> noticedPeople) {
        this.noticedPeople = noticedPeople;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
