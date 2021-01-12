package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 10:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DetailContractApplyBean extends BaseContractExcptBean {
    private String detailCause;
    private String pauseRemark;
    private String restartRemark;
    private List<ContractApproveLog> comments;
    /**
     * 合同申请人，不一定是销售人员
     */
    private String applier;
    /**
     * 0 正常 1 暂停 2被激活
     */
    private int applyType;
    public static final int APPLY_TYPE_PAUSE = 1;
    public static final int APPLY_TYPE_RESTART = 2;
    public static final int APPLY_TYPE_NORMAL = 0;
    private ContractPauseInfo pauseInfo;
    /**
     * 状态 0.审批中 1.已同意 2.已拒绝
     */
    private int approveResult;
    /**
     * 合同启动/暂停的申请时间
     */
    private long applyDate;
    /**
     * 审批人
     */
    private ArrayList<StaffBean> sendList = new ArrayList<>();
    /**
     * 抄送人
     */
    private ArrayList<StaffBean> copyList = new ArrayList<>();


    public DetailContractApplyBean(NetContractStatusDetail src) {
        NetContractStatusDetail.TcontractInfoBean contract = src.getTcontractInfo();
        NetContractStatusDetail.TexamineVoBean flow = src.getTexamineVo();
        if (contract != null) {
            setBuyer(contract.getCusName());
            setBuyerId(contract.getCusId());
            setContractNum(contract.getContractNum());
            setApplyType(contract.getStopStatus());
            setContractId(contract.getId());
        }
        comments = new ArrayList<>();
        if (flow != null) {
            pauseInfo = new ContractPauseInfo(flow);
            setApproveResult(flow.getStatus());
            setDetailCause(flow.getRestartRemark());
            setRestartRemark(flow.getRestartRemark());
            setPauseRemark(flow.getRemark());
            setJudgement(flow.getRemark1());
            setApplier(flow.getCreateName());
            String createTime = flow.getCreateTime();
            if (!TextUtils.isEmpty(createTime)) {
                try {
                    setApplyDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime).getTime());
                } catch (Exception e) {
                    setApplyDate(0);
                }
            }
            List<NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean> list = flow.getTexamineFlows();
            for (NetContractStatusDetail.TexamineVoBean.TexamineFlowsBean bean : list) {
                ContractApproveLog log = new ContractApproveLog(bean);
                comments.add(log);
            }
            List<Integer> copyList = flow.getCopyList();
            if(copyList!=null){
                List<StaffEntity> staffs = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsById(copyList);
                for (StaffEntity staff : staffs) {
                    this.copyList.add(new StaffBean(staff));
                }
            }
            List<Integer> sendList = flow.getSendList();
            if(sendList!=null){
                List<StaffEntity> staffs = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsById(sendList);
                for (StaffEntity staff : staffs) {
                    this.sendList.add(new StaffBean(staff));
                }
            }
        }


    }


    public String getDetailCause() {
        return detailCause;
    }

    public void setDetailCause(String detailCause) {
        this.detailCause = detailCause;
    }

    public List<ContractApproveLog> getComments() {
        return comments;
    }

    public void setComments(List<ContractApproveLog> comments) {
        this.comments = comments;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public ContractPauseInfo getPauseInfo() {
        return pauseInfo;
    }

    public void setPauseInfo(ContractPauseInfo pauseInfo) {
        this.pauseInfo = pauseInfo;
    }

    public int getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(int approveResult) {
        this.approveResult = approveResult;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public ArrayList<StaffBean> getSendList() {
        return sendList;
    }

    public void setSendList(ArrayList<StaffBean> sendList) {
        this.sendList = sendList;
    }

    public ArrayList<StaffBean> getCopyList() {
        return copyList;
    }

    public void setCopyList(ArrayList<StaffBean> copyList) {
        this.copyList = copyList;
    }

    public String getPauseRemark() {
        return pauseRemark;
    }

    public void setPauseRemark(String pauseRemark) {
        this.pauseRemark = pauseRemark;
    }

    public String getRestartRemark() {
        return restartRemark;
    }

    public void setRestartRemark(String restartRemark) {
        this.restartRemark = restartRemark;
    }
}
