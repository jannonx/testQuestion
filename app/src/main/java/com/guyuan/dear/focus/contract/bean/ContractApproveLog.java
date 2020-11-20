package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.utils.CalenderUtils;

import java.text.ParseException;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 11:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApproveLog extends Vote {
    private String dept;
    private String comment;
    private long date;

    public ContractApproveLog() {
    }

    public ContractApproveLog(NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean src){
        setName(src.getApproveName());
        setDept(src.getDepartmentName());
        setImgUrl(src.getImgUrl());
        String approvalTime = src.getApprovalTime();
        if(!TextUtils.isEmpty(approvalTime)){
            try {
                setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(approvalTime).getTime());
            }catch (Exception e){
                setDate(0);
            }
        }
        setComment(src.getRemarks());
        int status = src.getStatus();
        switch (status){
            case 0:
                setResult(Vote.VOTE_RESULT_PENDING);
                break;
            case 1:
                setResult(Vote.VOTE_RESULT_PASS);
                break;
            case 2:
                setResult(Vote.VOTE_RESULT_REJECT);
                break;
            case -1:
            default:
                setResult(Vote.VOTE_RESULT_DEFAULT);
                break;
        }

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
