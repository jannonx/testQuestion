package com.guyuan.dear.focus.qc.beans.verfifyLog;

import com.google.gson.Gson;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 16:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LogTypeVerifyResult extends Vote {
    private String comment;
    private String dept;
    private long date;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public GenericQcLogBean toGenericLogBean(){
        GenericQcLogBean bean = new GenericQcLogBean();
        bean.setLogType(GenericQcLogBean.LOG_TYPE_VERIFY_RESULT);
        bean.setJsonString(new Gson().toJson(this));
        return bean;
    }
}
