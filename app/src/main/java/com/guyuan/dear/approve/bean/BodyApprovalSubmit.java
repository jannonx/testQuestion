package com.guyuan.dear.approve.bean;


import java.util.ArrayList;

/**
 * @description: 申请提交带的body
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class BodyApprovalSubmit {
    private long id;
    private int status;
    private int type;
    private String remark;

    private int rbType;
    private int arType;
    private long totalId;
    private String description;
    private String sleaveTime;
    private String eleaveTime;
    private String remarks;
    private ArrayList<Long> users;
    private ArrayList<Long> copy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isToBeApproved() {
        return status == 1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRbType() {
        return rbType;
    }

    public void setRbType(int rbType) {
        this.rbType = rbType;
    }

    public int getArType() {
        return arType;
    }

    public void setArType(int arType) {
        this.arType = arType;
    }

    public long getTotalId() {
        return totalId;
    }

    public void setTotalId(long totalId) {
        this.totalId = totalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSleaveTime() {
        return sleaveTime;
    }

    public void setSleaveTime(String sleaveTime) {
        this.sleaveTime = sleaveTime;
    }

    public String getEleaveTime() {
        return eleaveTime;
    }

    public void setEleaveTime(String eleaveTime) {
        this.eleaveTime = eleaveTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ArrayList<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
    }

    public ArrayList<Long> getCopy() {
        return copy;
    }

    public void setCopy(ArrayList<Long> copy) {
        this.copy = copy;
    }
}
