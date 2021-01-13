package com.guyuan.dear.net.reqBean;

import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/18 16:22
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyBody {

    /**
     * ccBy : []
     * id : 0
     * remark :
     * sendBy : []
     * stopType : 0
     * type : 0
     */

    private int id;
    private String remark;
    /**
     * 暂停维度 参考：初始化接口
     */
    private int judgeCondition;
    /**
     * 操作类型 1.暂停 2.重启
     */
    private int type;
    private List<Integer> ccBy;
    private List<Integer> sendBy;

    public ContractApplyBody() {
    }

    public ContractApplyBody(ContractApplyBean bean) {
        this.id =(int) bean.getContractId();
        List<Integer> ccBy = new ArrayList<>();
        ArrayList<StaffBean> beanCopyList = bean.getCopyList();
        for (StaffBean staffBean : beanCopyList) {
            ccBy.add(staffBean.getId().intValue());
        }
        this.ccBy = ccBy;
        List<Integer> sendBy = new ArrayList<>();
        ArrayList<StaffBean> beanSendList = bean.getSendList();
        for (StaffBean staffBean : beanSendList) {
            sendBy.add(staffBean.getId().intValue());
        }
        this.sendBy = sendBy;
        this.remark = bean.getDetailReason();
        String key = bean.getJudgementKey();
        int judgeCondition = 0;
        try {
            judgeCondition = Integer.valueOf(key);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        this.judgeCondition = judgeCondition;
        int applyType = bean.getApplyType();
        switch (applyType){
            case ContractApplyBean.APPLY_TYPE_PAUSE:
                this.type=1;
                break;
                case ContractApplyBean.APPLY_TYPE_RESUME:
            default:
                this.type=2;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(int judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCcBy() {
        return ccBy;
    }

    public void setCcBy(List<Integer> ccBy) {
        this.ccBy = ccBy;
    }

    public List<Integer> getSendBy() {
        return sendBy;
    }

    public void setSendBy(List<Integer> sendBy) {
        this.sendBy = sendBy;
    }
}
