package com.guyuan.dear.work.contractPause.views.applyDetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractPauseBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.repos.ContactPauseApplyDetailRepo;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseApplyDetailViewModel extends BaseViewModel {
    private ContactPauseApplyDetailRepo repo = new ContactPauseApplyDetailRepo();
    private MutableLiveData<DetailContractApplyBean> detailBean = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickResubmit = new MutableLiveData<>();


    public MutableLiveData<DetailContractApplyBean> getDetailBean() {
        return detailBean;
    }

    public Disposable getMyApplyDetailFromNet(int id){
        return repo.getContractDetailFromNet(id, getApplyDetailCallback);
    }
    private DearNetHelper.NetCallback<DetailContractApplyBean> getApplyDetailCallback = new DearNetHelper.NetCallback<DetailContractApplyBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(DetailContractApplyBean result) {
            detailBean.postValue(result);
            isShowLoading.postValue(false);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());
        }
    };


    public ContractPauseBean genContractPauseBean() {
        ContractPauseBean bean = new ContractPauseBean();
        if(detailBean!=null){
            DetailContractApplyBean value = detailBean.getValue();
            bean.setClientId(value.getBuyerId());
            bean.setClientName(value.getBuyer());
            bean.setContractId(value.getContractId()+"");
            bean.setContractNum(value.getContractNum());
            bean.setRemark(value.getDetailCause());
            bean.setSendList(value.getSendList());
            bean.setCopyList(value.getCopyList());
            bean.setPauseInfo(value.getPauseInfo());
//            ContractPauseInfo info = new ContractPauseInfo();
//            info.setApplyCauseType(Integer.valueOf(value.getJudgementKey()));
//            info.setDetailPauseReason(value.getDetailCause());
//            info.setPauseCauseType(value.getJudgement());
//            bean.setPauseInfo(info);
        }
        return bean;
    }
}
