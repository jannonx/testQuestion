package com.guyuan.dear.work.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 14:29
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessCommitBody {

    /**
     * auditItemDetailParamsList : [{"id":0,"level":0,"name":"","singleItemResult":0,"sort":0}]
     * contractNumber :
     * customerId : 0
     * id : 0
     * isDraft : 0
     * isInformTime : 0
     * meetingId : 0
     * reserveEndTime :
     * reserveId : 0
     * reserveStartTime :
     * userIdList : []
     */

    private String contractNumber;       //合同编号
    private int customerId;              //关联客户ID
    private int id;                      //评审ID主键
    private int isDraft;                 //App中有一个保存草稿保存。是否是草稿保存操作，0:保存草稿，1:提交操作
    private int isInformTime;            //提前多久进行通知(单位:秒)
    private int meetingId;               //会议室ID
    private String reserveEndTime;       //预约截止时间
    private int reserveId;               //预约单主键ID
    private String reserveStartTime;     //预约开始时间
    private List<AuditItemDetailParamsListBean> auditItemDetailParamsList;//事项细则参数集合
    private List<Integer> userIdList;    //参加会议评审人员ID集合

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(int isDraft) {
        this.isDraft = isDraft;
    }

    public int getIsInformTime() {
        return isInformTime;
    }

    public void setIsInformTime(int isInformTime) {
        this.isInformTime = isInformTime;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public String getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(String reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public List<AuditItemDetailParamsListBean> getAuditItemDetailParamsList() {
        return auditItemDetailParamsList;
    }

    public void setAuditItemDetailParamsList(List<AuditItemDetailParamsListBean> auditItemDetailParamsList) {
        this.auditItemDetailParamsList = auditItemDetailParamsList;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public static class AuditItemDetailParamsListBean {
        private int id;                  //事项细则ID主键
        private int level;               //事项细则等级(如有需要可以填写)
        private String name;             //事项细则名称
        private int singleItemResult;    //单个事项细则提交结果
        private int sort;                //事项细则排序

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSingleItemResult() {
            return singleItemResult;
        }

        public void setSingleItemResult(int singleItemResult) {
            this.singleItemResult = singleItemResult;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}