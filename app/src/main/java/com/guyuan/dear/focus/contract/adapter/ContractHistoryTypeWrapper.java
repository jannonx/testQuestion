package com.guyuan.dear.focus.contract.adapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/5 16:51
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractHistoryTypeWrapper {
    private long date;
    private int type;
    private String jsonString;
    public static final int TYPE_DEPOSIT_NOT_RECEIVED=1;
    public static final int TYPE_DEPOSIT_RECEIVED=2;
    public static final int TYPE_CLIENT_PAYMENT_RECEIVED=3;
    public static final int TYPE_CONTRACT_PAUSE=4;
    public static final int TYPE_CONTRACT_RESTART=5;
    public static final int TYPE_CLIENT_TAKE_DELIVERY=6;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
