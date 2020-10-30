package com.guyuan.dear.work.contractPause.views.applyWindow;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.PauseContractBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseContractViewModel extends BaseViewModel {
    private MutableLiveData<PauseContractBean> pauseBean =new MutableLiveData<PauseContractBean>(){
        {
            setValue(new PauseContractBean());
        }
    };
    private MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSelectBuyer = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSelectContract = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSelectJudgement = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickAddSendList = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickAddCopyList = new MutableLiveData<>();


    public MutableLiveData<PauseContractBean> getPauseBean() {
        return pauseBean;
    }

    public MutableLiveData<View.OnClickListener> getOnClickSubmit() {
        return onClickSubmit;
    }

    private static final int MAX_LEN=240;
    public void setOnClickSubmit(View.OnClickListener onClickSubmit) {
        this.onClickSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseContractBean value = pauseBean.getValue();
                String buyer = value.getBuyer();
                if(TextUtils.isEmpty(buyer)){
                    ToastUtils.showShort(DearApplication.getInstance(),"客户名字不能为空。");
                    return;
                }
                String contractId = value.getContractId();
                if(TextUtils.isEmpty(contractId)){
                    ToastUtils.showShort(DearApplication.getInstance(),"合同编号不能为空。");
                    return;
                }
                String judgement = value.getJudgement();
                if(TextUtils.isEmpty(judgement)){
                    ToastUtils.showShort(DearApplication.getInstance(),"判定维度不能为空。");
                    return;
                }
                String reason = value.getDetailPauseReason();
                if(TextUtils.isEmpty(reason)){
                    ToastUtils.showShort(DearApplication.getInstance(),"暂停原因需要说明。");
                    return;
                }else {
                    if(reason.length()>=MAX_LEN){
                        ToastUtils.showShort(DearApplication.getInstance(),"暂停原因字数不能超过240个字符串。");
                        return;
                    }
                }
                List<StaffBean> copyList = value.getCopyList();
                if(copyList == null ||copyList.isEmpty()){
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
                PauseContractBean value = pauseBean.getValue();
                String buyer = value.getBuyer();
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
}
