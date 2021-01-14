package com.guyuan.dear.work.contractPause.views.resubmit;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/11 16:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ResubmitContractPauseViewModel extends BaseDearViewModel {
    /**
     * 数据源
     */
    public MutableLiveData<String> clientName = new MutableLiveData<>();
    public MutableLiveData<String> contractNum = new MutableLiveData<>();
    public MutableLiveData<String> judgeCondition = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<ArrayList<StaffBean>> sendList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> copyList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> hiddenList = new MutableLiveData<>(new ArrayList<>());
    private int clientId;
    private int contractId;
    private String judgeConditionKey;
    /**
     * {@link ContractApplyBean#APPLY_TYPE_PAUSE} {@link ContractApplyBean#APPLY_TYPE_RESUME}
     */
    public MutableLiveData<Integer> applyType = new MutableLiveData<>(ContractApplyBean.APPLY_TYPE_PAUSE);

    /**
     * 点击事件：提交申请
     */
    public MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();
    /**
     * 点击事件：选择维度
     */
    public MutableLiveData<View.OnClickListener> onClickSelectJudgement = new MutableLiveData<>();
    /**
     * 点击事件：增加审批人
     */
    public MutableLiveData<View.OnClickListener> onClickAddSendList = new MutableLiveData<>();
    /**
     * 点击事件：增加抄送人
     */
    public MutableLiveData<View.OnClickListener> onClickAddCopyList = new MutableLiveData<>();
    private static final int MAX_LEN = 240;

    public void setOnClickSubmit(View.OnClickListener onClickSubmit) {
        this.onClickSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里检查提交内容的合法性
                if (TextUtils.isEmpty(clientName.getValue())) {
                    ToastUtils.showShort(DearApplication.getInstance(), "客户名字不能为空。");
                    return;
                }
                if (TextUtils.isEmpty(contractNum.getValue())) {
                    ToastUtils.showShort(DearApplication.getInstance(), "合同编号不能为空。");
                    return;
                }
                if (TextUtils.isEmpty(judgeCondition.getValue())) {
                    ToastUtils.showShort(DearApplication.getInstance(), "判定维度不能为空。");
                    return;
                }
                if (TextUtils.isEmpty(description.getValue())) {
                    ToastUtils.showShort(DearApplication.getInstance(), "暂停原因需要说明。");
                    return;
                } else {
                    if (description.getValue().length() > MAX_LEN) {
                        ToastUtils.showShort(DearApplication.getInstance(), "暂停原因字数不能超过240个字符串。");
                        return;
                    }
                }
                List<StaffBean> sendList = ResubmitContractPauseViewModel.this.sendList.getValue();
                if (sendList == null || sendList.isEmpty()) {
                    ToastUtils.showShort(DearApplication.getInstance(), "审批人不能为空。");
                    return;
                }
                onClickSubmit.onClick(v);
            }
        });
    }


    public void updateJudgeCondition(NetServerParam.JudgeCondition con) {
        this.judgeCondition.postValue(con.getValue());
        this.judgeConditionKey = con.getKey();
    }


    public ContractApplyBean genApplyBean() {
        ContractApplyBean bean = new ContractApplyBean();
        bean.setBuyerId(clientId);
        bean.setContractId(contractId);
        bean.setContractNum(contractNum.getValue());
        bean.setJudgementKey(judgeConditionKey);
        bean.setDetailReason(description.getValue());
        bean.setSendList(sendList.getValue());
        bean.setCopyList(copyList.getValue());
        bean.setApplyType(applyType.getValue());
        return bean;
    }

    public void showPreviousApply(ContractPauseBean apply) {
        clientId = apply.getClientId();
        clientName.postValue(apply.getClientName());
        contractId = Integer.valueOf(apply.getContractId());
        contractNum.postValue(apply.getContractNum());
        sendList.postValue(apply.getSendList());
        copyList.postValue(apply.getCopyList());
        ContractPauseInfo pauseInfo = apply.getPauseInfo();
        description.postValue(pauseInfo.getDetailPauseReason());
        judgeCondition.postValue(pauseInfo.getPauseCauseType());
        judgeConditionKey = String.valueOf(pauseInfo.getApplyCauseType());
    }
}
