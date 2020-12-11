package com.guyuan.dear.focus.contract.bean;

import com.guyuan.dear.net.resultBeans.NetContractInfo;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 16:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractBean extends BaseContractBean{
    private String restartCause;
    private String restartStateTag;
    private int examineId;
    private String approveComments;

    public RestartedContractBean() {
    }

    public RestartedContractBean(NetContractInfo info) {
        setRestartCause(info.getListJudgeCondition());
        //状态标签 10002：表示暂停 10003：表示激活
        String tag = null;
        switch (info.getChangeType()){
            case 10002:
                tag="暂停";
                break;
            case 10003:
                tag="重启";
                break;
            default:
                break;
        }
        if(tag!=null){
            setRestartStateTag(tag);
        }
        setContractNum(info.getContractNum());
        setBuyer(info.getCusName());
        setExamineId(info.getExamineId());
        setApproveComments(info.getApproveComment());
    }

    public String getRestartCause() {
        return restartCause;
    }

    public void setRestartCause(String restartCause) {
        this.restartCause = restartCause;
    }

    public String getRestartStateTag() {
        return restartStateTag;
    }

    public void setRestartStateTag(String restartStateTag) {
        this.restartStateTag = restartStateTag;
    }

    public int getExamineId() {
        return examineId;
    }

    public void setExamineId(int examineId) {
        this.examineId = examineId;
    }

    public String getApproveComments() {
        return approveComments;
    }

    public void setApproveComments(String approveComments) {
        this.approveComments = approveComments;
    }
}
