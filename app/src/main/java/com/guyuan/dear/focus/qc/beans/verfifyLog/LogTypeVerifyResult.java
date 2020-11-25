package com.guyuan.dear.focus.qc.beans.verfifyLog;

import com.google.gson.Gson;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;
import com.guyuan.dear.utils.CalenderUtils;

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

    public LogTypeVerifyResult() {
    }

    public LogTypeVerifyResult(NetQcReportApproveFlow src) {
        setComment(src.getQualityRemark());
        setDept(src.getCreateDept());
        try {
            setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getCreateTime()).getTime());
        }catch (Exception e){
            setDate(0);
        }
        int status = src.getStatus();
        if(status==1){
            setResult(VOTE_RESULT_PASS);
        }else {
            setResult(VOTE_RESULT_REJECT);
        }
        setName(src.getCreateName());
        setImgUrl(src.getImgUrl());
    }

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
