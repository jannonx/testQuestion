package com.guyuan.dear.work.contractPause.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseBean implements Parcelable {
    private int clientId;
    private String clientName;
    private String contractId;
    private String contractNum;
    private ContractPauseInfo pauseInfo;
    private String remark;
    private ArrayList<StaffBean> sendList;
    private ArrayList<StaffBean> copyList;

    public ContractPauseBean() {
    }

    protected ContractPauseBean(Parcel in) {
        clientId = in.readInt();
        clientName = in.readString();
        contractId = in.readString();
        contractNum = in.readString();
        pauseInfo = in.readParcelable(ContractPauseInfo.class.getClassLoader());
        remark = in.readString();
        sendList = in.createTypedArrayList(StaffBean.CREATOR);
        copyList = in.createTypedArrayList(StaffBean.CREATOR);
    }

    public static final Creator<ContractPauseBean> CREATOR = new Creator<ContractPauseBean>() {
        @Override
        public ContractPauseBean createFromParcel(Parcel in) {
            return new ContractPauseBean(in);
        }

        @Override
        public ContractPauseBean[] newArray(int size) {
            return new ContractPauseBean[size];
        }
    };

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public ContractPauseInfo getPauseInfo() {
        return pauseInfo;
    }

    public void setPauseInfo(ContractPauseInfo pauseInfo) {
        this.pauseInfo = pauseInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<StaffBean> getSendList() {
        return sendList;
    }

    public void setSendList(ArrayList<StaffBean> sendList) {
        this.sendList = sendList;
    }

    public ArrayList<StaffBean> getCopyList() {
        return copyList;
    }

    public void setCopyList(ArrayList<StaffBean> copyList) {
        this.copyList = copyList;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(clientId);
        dest.writeString(clientName);
        dest.writeString(contractId);
        dest.writeString(contractNum);
        dest.writeParcelable(pauseInfo, flags);
        dest.writeString(remark);
        dest.writeTypedList(sendList);
        dest.writeTypedList(copyList);
    }
}
