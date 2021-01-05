package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;
import android.view.TextureView;

import com.guyuan.dear.net.resultBeans.NetContractHistory;
import com.guyuan.dear.utils.CalenderUtils;

import org.w3c.dom.Text;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 11:20
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientPaymentHistory {
    private long date;
    private long amount;
    private String clientName;

    public ClientPaymentHistory(NetContractHistory.PaymentSumVOBean src) {
        String timeTrade = src.getTimeTrade();
        if(!TextUtils.isEmpty(timeTrade)){
            date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(timeTrade).getTime();
        }
        amount = src.getSumTrade();
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
