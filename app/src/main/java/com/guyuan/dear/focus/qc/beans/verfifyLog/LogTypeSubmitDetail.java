package com.guyuan.dear.focus.qc.beans.verfifyLog;

import com.google.gson.Gson;
import com.guyuan.dear.net.resultBeans.NetQcReportApproveFlow;
import com.guyuan.dear.utils.CalenderUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 16:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LogTypeSubmitDetail {
    private String qcResult;
    private long date;
    private String judgeCondition;
    private int sampleSize;
    private String applierName;
    private String applierImgUrl;
    private String applierComment;
    private String deptName;
    private String qcApproach;

    public LogTypeSubmitDetail() {
    }

    public LogTypeSubmitDetail(NetQcReportApproveFlow src) {
       try {
           this.date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getCreateTime()).getTime();
       }catch (Exception e){
           this.date = 0;
       }
       //质检判断结果：0.未质检，1.合格，2.不合格
        int qualityResult = src.getQualityResult();
       switch (qualityResult){
           case 1:
               setQcResult("合同");
               break;
           case 2:
               setQcResult("不合格");
               break;
           case 0:
           default:
               setQcResult("未开始");
               break;
       }
       setJudgeCondition(src.getQualityCondition());
       setSampleSize(src.getQualityNum());
       setApplierName(src.getCreateName());
       setApplierImgUrl(src.getImgUrl());
       //质检方式：1.全检，2.抽检，3.按标准质检文件
        int qualityType = src.getQualityType();
        switch (qualityType){
            case 1:
                setQcApproach("全检");
                break;
            case 2:
                setQcApproach("抽检");
                break;
            case 3:
                setQcApproach("按标准质检文件");
                break;
            default:
                break;
        }
        setApplierComment(src.getQualityRemark());
        setDeptName(src.getCreateDept());

    }

    public String getQcApproach() {
        return qcApproach;
    }

    public void setQcApproach(String qcApproach) {
        this.qcApproach = qcApproach;
    }

    public String getQcResult() {
        return qcResult;
    }

    public void setQcResult(String qcResult) {
        this.qcResult = qcResult;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public String getApplierImgUrl() {
        return applierImgUrl;
    }

    public void setApplierImgUrl(String applierImgUrl) {
        this.applierImgUrl = applierImgUrl;
    }

    public String getApplierComment() {
        return applierComment;
    }

    public void setApplierComment(String applierComment) {
        this.applierComment = applierComment;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public GenericQcLogBean toGenericLogBean(){
        GenericQcLogBean bean = new GenericQcLogBean();
        bean.setLogType(GenericQcLogBean.LOG_TYPE_SUBMIT_DETAIL);
        bean.setJsonString(new Gson().toJson(this));
        return bean;
    }
}
