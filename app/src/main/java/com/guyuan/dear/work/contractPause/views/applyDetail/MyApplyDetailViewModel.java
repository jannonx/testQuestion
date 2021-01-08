package com.guyuan.dear.work.contractPause.views.applyDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.google.gson.Gson;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogApproved;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogFirstCreateDate;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogProcessing;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogReject;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.ApproveLogToBePrc;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.GenericApproveLog;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.MyApplyDetailBean;
import com.guyuan.dear.work.contractPause.repos.MyApplyDetailRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:08
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyDetailViewModel extends BaseViewModel {
    private MyApplyDetailRepo repo = new MyApplyDetailRepo();
    private MutableLiveData<MyApplyDetailBean> applyBean = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowDetailPauseInfo = new MutableLiveData<>(false);
    public MutableLiveData<ContractPauseInfo> contractPauseInfo = new MutableLiveData<>();

    public MutableLiveData<MyApplyDetailBean> getApplyBean() {
        return applyBean;
    }

    public Disposable getMyApplyDetailFromNet(int id){
        return repo.getMyApplyDetail(id, getApplyDetailCallback);
    }
    private DearNetHelper.NetCallback<MyApplyDetailBean> getApplyDetailCallback = new DearNetHelper.NetCallback<MyApplyDetailBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(MyApplyDetailBean result) {
            applyBean.postValue(result);
            isShowLoading.postValue(false);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());
        }
    };


}
