package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 11:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DepositSubmitHistory {
    private long date;
    private long amount;

    public DepositSubmitHistory(NetContractHistory.QualitySumVOBean src) {
        String timeQuality = src.getTimeQuality();
        if(!TextUtils.isEmpty(timeQuality)){
            date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(timeQuality).getTime();
        }
        amount = src.getSumQuality();
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
