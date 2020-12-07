package com.guyuan.dear.focus.contract.view.contractDetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.repos.ContractDetailRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 10:24
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailViewModel extends BaseViewModel {
    private ContractDetailRepo repo = new ContractDetailRepo();
    private MutableLiveData<DetailContractBean> contractBean = new MutableLiveData<>();
    private DetailContractBean temp;
    private MutableLiveData<View.OnClickListener> onClickCheckProgress = new MutableLiveData<>();

    public MutableLiveData<DetailContractBean> getContractBean() {
        return contractBean;
    }

    public MutableLiveData<View.OnClickListener> getOnClickCheckProgress() {
        return onClickCheckProgress;
    }

    public void setOnClickCheckProgress(View.OnClickListener listener) {
        onClickCheckProgress.postValue(listener);
    }

    public Disposable loadContractDetail(int contractId) {
        return repo.loadContractDetailById(contractId, callbackGetDetail);
    }

    private DearNetHelper.NetCallback<DetailContractBean> callbackGetDetail = new DearNetHelper.NetCallback<DetailContractBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);

        }

        @Override
        public void onGetResult(DetailContractBean result) {
            //进一步获取评论列表
            temp = result;
            repo.getVerifyFlowByContractId((int) result.getContractId(), callbackGetComments);

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };

    private DearNetHelper.NetCallback<List<ContractComment>> callbackGetComments = new DearNetHelper.NetCallback<List<ContractComment>>() {
        @Override
        public void onStart(Disposable disposable) {

        }

        @Override
        public void onGetResult(List<ContractComment> result) {
            temp.setCommentList(result);
            contractBean.postValue(temp);
            isShowLoading.postValue(false);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };
}
