package com.guyuan.dear.work.contractRestart.view.resubmit;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractRestart.bean.ContractRestartBean;
import com.guyuan.dear.work.contractRestart.view.apply.ContractRestartApplyViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/8 17:39
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ResubmitContractRestartViewModel extends BaseDearViewModel {
    /**
     * 数据源
     */
    public MutableLiveData<String> clientName =new MutableLiveData<>();
    public MutableLiveData<String> contractNum = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<ArrayList<StaffBean>> sendList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> copyList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> hiddenList =new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ContractPauseInfo> contractPauseInfo = new MutableLiveData<>();
    private int clientId;
    private int contractId;
    public MutableLiveData<Integer> applyType = new MutableLiveData<>(ContractApplyBean.APPLY_TYPE_RESUME);


    /**
     * 点击事件：提交申请
     */
    private MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();

    /**
     * 点击事件：增加审批人
     */
    private MutableLiveData<View.OnClickListener> onClickAddSendList = new MutableLiveData<>();
    /**
     * 点击事件：增加抄送人
     */
    private MutableLiveData<View.OnClickListener> onClickAddCopyList = new MutableLiveData<>();


    public MutableLiveData<View.OnClickListener> getOnClickSubmit() {
        return onClickSubmit;
    }


    public MutableLiveData<String> getClientName() {
        return clientName;
    }


    public MutableLiveData<String> getContractNum() {
        return contractNum;
    }



    private static final int MAX_LEN=240;

    public void setOnClickSubmit(View.OnClickListener onClickSubmit) {
        this.onClickSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里检查提交内容的合法性
                if(TextUtils.isEmpty(clientName.getValue())){
                    ToastUtils.showShort(DearApplication.getInstance(),"客户名字不能为空。");
                    return;
                }
                if(TextUtils.isEmpty(contractNum.getValue())){
                    ToastUtils.showShort(DearApplication.getInstance(),"合同编号不能为空。");
                    return;
                }
                if(TextUtils.isEmpty(description.getValue())){
                    ToastUtils.showShort(DearApplication.getInstance(),"暂停原因需要说明。");
                    return;
                }else {
                    if(description.getValue().length()>=MAX_LEN){
                        ToastUtils.showShort(DearApplication.getInstance(),"暂停原因字数不能超过240个字符串。");
                        return;
                    }
                }
                List<StaffBean> sendList = ResubmitContractRestartViewModel.this.sendList.getValue();
                if(sendList == null ||sendList.isEmpty()){
                    ToastUtils.showShort(DearApplication.getInstance(),"审批人不能为空。");
                    return;
                }
                onClickSubmit.onClick(v);
            }
        });
    }


    public MutableLiveData<View.OnClickListener> getOnClickAddSendList() {
        return onClickAddSendList;
    }



    public MutableLiveData<View.OnClickListener> getOnClickAddCopyList() {
        return onClickAddCopyList;
    }



    public ContractApplyBean genApplyBean() {
        ContractApplyBean bean = new ContractApplyBean();
        bean.setBuyerId(clientId);
        bean.setContractNum(contractNum.getValue());
        bean.setContractId(contractId);
        bean.setDetailReason(description.getValue());
        bean.setSendList(sendList.getValue());
        bean.setCopyList(copyList.getValue());
        bean.setApplyType(applyType.getValue());
        bean.setJudgementKey(contractPauseInfo.getValue().getApplyCauseType()+"");
        return bean;
    }

    public void showPreviousApply(ContractRestartBean apply) {
        clientName.postValue(apply.getClientName());
        clientId = apply.getClientId();
        copyList.postValue(apply.getCopyList());
        sendList.postValue(apply.getSendList());
        description.postValue(apply.getRemark());
        contractPauseInfo.postValue(apply.getPauseInfo());
        contractId = Integer.valueOf(apply.getContractId());
        contractNum.postValue(apply.getContractNum());

    }
}
