package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 11:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientAcceptanceHistory {
    private String tag;
    private int status;
    private String recorder;
    private String remark;
    private long date;
    private List<String> attachUrls = new ArrayList<>();

    public ClientAcceptanceHistory(NetContractHistory.CustomerAcceptanceVOBean src) {
        recorder = src.getCheckName();
        remark = src.getCheckRemark();
        String checkTime = src.getCheckTime();
        if(!TextUtils.isEmpty(checkTime)){
            date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(checkTime).getTime();
        }
        status = src.getCheckStatus();
        //验收状态，0:待验收，10合格，20不合格
        switch (status){
            case 10:
                tag="验收合格";
                break;
            case 20:
                tag="验收不合格";
                break;
            default:
                tag="待验收";
                break;
        }
        String checkUrl = src.getCheckUrl();
        if(!TextUtils.isEmpty(checkUrl)){
            String[] strings = checkUrl.split(",");
            for (String string : strings) {
                attachUrls.add(string);
            }
        }

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<String> getAttachUrls() {
        return attachUrls;
    }

    public void setAttachUrls(List<String> attachUrls) {
        this.attachUrls = attachUrls;
    }
}
