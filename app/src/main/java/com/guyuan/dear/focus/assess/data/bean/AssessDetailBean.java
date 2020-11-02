package com.guyuan.dear.focus.assess.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/27 10:44
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessDetailBean implements Parcelable {

    /**
     * auditContentList : [{"id":0,"name":"","sort":0}]
     * auditFormResultVOList : [{"auditExplain":"","auditImgUrl":"","auditResult":"","auditUserName":""}]
     * auditType : 0
     * compereName :
     * contractNumber :
     * customerName :
     * id : 0
     * meetingName :
     * reserveEndTime :
     * reserveStartTime :
     * status : 0
     */

    private int auditType;                 //评审类型
    private String compereName;            //会议主持人
    private String contractNumber;         //销售合同编号
    private String customerName;           //客户名称
    private int id;                        //评审历史主键ID
    private String meetingName;            //会议室
    private String reserveEndTime;         //预约评审截止时间
    private String reserveStartTime;       //预约评审开始时间
    private int status;                    //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)
    /**
     * id : 0
     * name :
     * sort : 0
     */

    private List<AuditContentBean> auditContentList;
    /**
     * auditExplain :
     * auditImgUrl :
     * auditResult :
     * auditUserName :
     */

    private List<AuditFormResultBean> auditFormResultVOList;

    protected AssessDetailBean(Parcel in) {
        auditType = in.readInt();
        compereName = in.readString();
        contractNumber = in.readString();
        customerName = in.readString();
        id = in.readInt();
        meetingName = in.readString();
        reserveEndTime = in.readString();
        reserveStartTime = in.readString();
        status = in.readInt();
        auditContentList = in.createTypedArrayList(AuditContentBean.CREATOR);
        auditFormResultVOList = in.createTypedArrayList(AuditFormResultBean.CREATOR);
    }

    public static final Creator<AssessDetailBean> CREATOR = new Creator<AssessDetailBean>() {
        @Override
        public AssessDetailBean createFromParcel(Parcel in) {
            return new AssessDetailBean(in);
        }

        @Override
        public AssessDetailBean[] newArray(int size) {
            return new AssessDetailBean[size];
        }
    };

    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    public String getCompereName() {
        return compereName;
    }

    public void setCompereName(String compereName) {
        this.compereName = compereName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public String getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(String reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AuditContentBean> getAuditContentList() {
        return auditContentList;
    }

    public void setAuditContentList(List<AuditContentBean> auditContentList) {
        this.auditContentList = auditContentList;
    }

    public List<AuditFormResultBean> getAuditFormResultVOList() {
        return auditFormResultVOList;
    }

    public void setAuditFormResultVOList(List<AuditFormResultBean> auditFormResultVOList) {
        this.auditFormResultVOList = auditFormResultVOList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(auditType);
        dest.writeString(compereName);
        dest.writeString(contractNumber);
        dest.writeString(customerName);
        dest.writeInt(id);
        dest.writeString(meetingName);
        dest.writeString(reserveEndTime);
        dest.writeString(reserveStartTime);
        dest.writeInt(status);
        dest.writeTypedList(auditContentList);
        dest.writeTypedList(auditFormResultVOList);
    }
}