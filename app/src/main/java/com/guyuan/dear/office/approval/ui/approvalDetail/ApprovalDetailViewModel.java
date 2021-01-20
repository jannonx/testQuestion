package com.guyuan.dear.office.approval.ui.approvalDetail;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.office.approval.data.bean.ApprovalTypeBean;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.repos.ContactPauseApplyDetailRepo;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/20 14:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ApprovalDetailViewModel extends BaseDearViewModel {
    private ContactPauseApplyDetailRepo repo = new ContactPauseApplyDetailRepo();
    private MutableLiveData<DetailContractApplyBean> detailBean = new MutableLiveData<>();
    /**
     * {@link ApprovalTypeBean#CONTRACT_EXAMINE_STATUS_STOP_TYPE}
     * {@link ApprovalTypeBean#CONTRACT_EXAMINE_STATUS_RESTART_TYPE}
     */
    public MutableLiveData<Integer> applyType = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> remark = new MutableLiveData<>();
    public MutableLiveData<ContractPauseInfo> pauseInfo = new MutableLiveData<>();


    public MutableLiveData<DetailContractApplyBean> getDetailBean() {
        return detailBean;
    }

    public Disposable getMyApplyDetailFromNet(int id) {
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

            pauseInfo.postValue(result.getPauseInfo());
            Integer value = applyType.getValue();
            String applier = result.getApplier();
            String title = "";
            String remark = "";
            if (value == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_RESTART_TYPE) {
                title = applier + "提交的合同重启申请";
                remark = result.getRestartRemark();
            } else if (value == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE) {
                title = applier + "提交的合同暂停申请";
                remark = result.getPauseRemark();
            }
            ApprovalDetailViewModel.this.title.postValue(title);
            ApprovalDetailViewModel.this.remark.postValue(remark);

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };


}
