package com.guyuan.dear.work.contractPause.beans;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 14:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseContractBean extends BaseContractExcptBean {
    private String detailPauseReason;
    private List<StaffBean> sendList;
    private List<StaffBean> copyList;

    public String getDetailPauseReason() {
        return detailPauseReason;
    }

    public void setDetailPauseReason(String detailPauseReason) {
        this.detailPauseReason = detailPauseReason;
    }

    public List<StaffBean> getSendList() {
        return sendList;
    }

    public void setSendList(List<StaffBean> sendList) {
        this.sendList = sendList;
    }

    public List<StaffBean> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<StaffBean> copyList) {
        this.copyList = copyList;
    }
}
