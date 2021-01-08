package com.guyuan.dear.focus.contract.view.contractApplyDetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 18:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyDetailViewModel extends BaseViewModel {
    private MutableLiveData<DetailContractApplyBean> detailBean = new MutableLiveData<>();
    public MutableLiveData<ContractPauseInfo> contractPauseInfo = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickReSubmit = new MutableLiveData<>();

    public MutableLiveData<DetailContractApplyBean> getDetailBean() {
        return detailBean;
    }

    public Disposable getContractDetailFromNet(long examineId) {
        return DearNetHelper.getInstance().getContractApplyDetail(
                (int) examineId,
                getDetailCallback);
    }

    private DearNetHelper.NetCallback<DetailContractApplyBean> getDetailCallback = new DearNetHelper.NetCallback<DetailContractApplyBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);

        }

        @Override
        public void onGetResult(DetailContractApplyBean result) {
            isShowLoading.postValue(false);
            detailBean.postValue(result);
            if(result.getPauseInfo()!=null){
                contractPauseInfo.postValue(result.getPauseInfo());
            }
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };
}
