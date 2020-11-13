package com.guyuan.dear.focus.qc.beans.verfifyLog;

import com.google.gson.Gson;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 16:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LogTypeFirstCreateDate {
    private long date;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public GenericQcLogBean toGenericLogBean(){
        GenericQcLogBean bean = new GenericQcLogBean();
        bean.setLogType(GenericQcLogBean.LOG_TYPE_FIRST_CREATE_DATE);
        String json = new Gson().toJson(this);
        bean.setJsonString(json);
        return bean;
    }
}
