package com.guyuan.dear.work.contractPause.beans;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseContractBean extends BaseContractExcptBean {
    private String detailPauseReason;
    private ArrayList<StaffBean> sendList = new ArrayList<>();
    private ArrayList<StaffBean> copyList = new ArrayList<>();

    public String getDetailPauseReason() {
        return detailPauseReason;
    }

    public void setDetailPauseReason(String detailPauseReason) {
        this.detailPauseReason = detailPauseReason;
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
}
