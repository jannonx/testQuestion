package com.guyuan.dear.work.contractPause.views.applyWindow;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 合同暂停的申请界面的视图模型
 * @since: 2020/10/30 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyViewModel extends BaseViewModel {
    /**
     * 数据源
     */
    private MutableLiveData<String> clientName =new MutableLiveData<>();
    private MutableLiveData<String> contractNum = new MutableLiveData<>();
    private MutableLiveData<String> judgeCondition = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<ArrayList<StaffBean>> sendList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> copyList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> hiddenList =new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ContractPauseInfo> contractPauseInfo = new MutableLiveData<>();
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
    private MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();
    /**
     * 点击事件：选择买家
     */
    private MutableLiveData<View.OnClickListener> onClickSelectBuyer = new MutableLiveData<>();
    /**
     * 点击事件：选择合同
     */
    private MutableLiveData<View.OnClickListener> onClickSelectContract = new MutableLiveData<>();
    /**
     * 点击事件：选择维度
     */
    private MutableLiveData<View.OnClickListener> onClickSelectJudgement = new MutableLiveData<>();
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



    public MutableLiveData<String> getJudgeCondition() {
        return judgeCondition;
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
                if(TextUtils.isEmpty(judgeCondition.getValue())){
                    ToastUtils.showShort(DearApplication.getInstance(),"判定维度不能为空。");
                    return;
                }
                if(TextUtils.isEmpty(description.getValue())){
                    ToastUtils.showShort(DearApplication.getInstance(),"暂停原因需要说明。");
                    return;
                }else {
                    if(description.getValue().length()>MAX_LEN){
                        ToastUtils.showShort(DearApplication.getInstance(),"暂停原因字数不能超过240个字符串。");
                        return;
                    }
                }
                List<StaffBean> sendList = ContractApplyViewModel.this.sendList.getValue();
                if(sendList == null ||sendList.isEmpty()){
                    ToastUtils.showShort(DearApplication.getInstance(),"审批人不能为空。");
                    return;
                }
                onClickSubmit.onClick(v);
            }
        });
    }

    public MutableLiveData<View.OnClickListener> getOnClickSelectBuyer() {
        return onClickSelectBuyer;
    }

    public void setOnClickSelectBuyer(View.OnClickListener onClickSelectBuyer) {
        this.onClickSelectBuyer.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSelectBuyer.onClick(v);
            }
        });
    }

    public MutableLiveData<View.OnClickListener> getOnClickSelectContract() {
        return onClickSelectContract;
    }

    public void setOnClickSelectContract(View.OnClickListener onClickSelectContract) {
        this.onClickSelectContract.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必须先选择好客户才能选择合同
                String buyer = clientName.getValue();
                if(TextUtils.isEmpty(buyer)){
                    ToastUtils.showShort(DearApplication.getInstance(),"请先选择客户");
                    return;
                }
                onClickSelectContract.onClick(v);
            }
        });
    }

    public MutableLiveData<View.OnClickListener> getOnClickSelectJudgement() {
        return onClickSelectJudgement;
    }

    public int getClientId() {
        return clientId;
    }

    public void setOnClickSelectJudgement(View.OnClickListener onClickSelectJudgement) {
        this.onClickSelectJudgement.postValue(onClickSelectJudgement);
    }

    public MutableLiveData<View.OnClickListener> getOnClickAddSendList() {
        return onClickAddSendList;
    }

    public void setOnClickAddSendList(View.OnClickListener onClickAddSendList) {
        this.onClickAddSendList.postValue(onClickAddSendList);
    }

    public MutableLiveData<View.OnClickListener> getOnClickAddCopyList() {
        return onClickAddCopyList;
    }

    public void setOnClickAddCopyList(View.OnClickListener onClickAddCopyList) {
        this.onClickAddCopyList.postValue(onClickAddCopyList);
    }

    public void updateClient(NetClientInfo clientInfo){
        this.clientName.postValue(clientInfo.getCusName());
        this.clientId = clientInfo.getId();
    }

    public void updateContract(NetBaseContractInfo contractInfo){
        this.contractNum.postValue(contractInfo.getContractNum());
        this.contractId = contractInfo.getId();
    }

    public void updateJudgeCondition(NetServerParam.JudgeCondition con){
        this.judgeCondition.postValue(con.getValue());
        this.judgeConditionKey = con.getKey();
    }


    public ContractApplyBean genApplyBean() {
        ContractApplyBean bean = new ContractApplyBean();
        bean.setBuyerId(getClientId());
        bean.setContractNum(contractNum.getValue());
        bean.setContractId(contractId);
        bean.setJudgementKey(judgeConditionKey);
        bean.setDetailReason(description.getValue());
        bean.setSendList(sendList.getValue());
        bean.setCopyList(copyList.getValue());
        bean.setApplyType(applyType.getValue());
        return bean;
    }


    public void resetAllViews() {
        clientName.postValue("");
        contractNum.postValue("");
        judgeCondition.postValue("");
        description.postValue("");
        sendList.postValue(new ArrayList<>());
        copyList.postValue(new ArrayList<>());
    }
}
