package com.guyuan.dear.work.contractRestart.view.resubmit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentResubmitContractRestartBinding;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeActivity;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeViewModel;
import com.guyuan.dear.work.contractRestart.bean.ContractRestartBean;
import com.guyuan.dear.work.contractRestart.view.apply.ContractRestartApplyViewModel;
import com.guyuan.dear.work.contractRestart.view.apply.ContractRestartFragment;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeActivity;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/8 17:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ResubmitContractRestartFragment extends BaseMvvmFragment<FragmentResubmitContractRestartBinding,ResubmitContractRestartViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;
    private ContractRestartBean oldApply;

    public static ResubmitContractRestartFragment getInstance(ContractRestartBean oldApply){
        ResubmitContractRestartFragment fragment = new ResubmitContractRestartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_RESTART_APPLY,oldApply);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_resubmit_contract_restart_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        oldApply = bundle.getParcelable(ConstantValue.KEY_RESTART_APPLY);
        getViewModel().showPreviousApply(oldApply);

        //选人时把自己过滤掉
        StaffBean me = new StaffBean();
        me.setId(CommonUtils.getCurrentUserId());
        getViewModel().hiddenList.getValue().add(me);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ResubmitContractRestartViewModel viewModel = getViewModel();

        viewModel.getOnClickAddSendList().postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ResubmitContractRestartFragment.this, REQUEST_CODE_PICK_SEND_LIST, "请选择审批人",
                        sendList, hiddenList, copyList, 10);
            }
        });

        viewModel.getOnClickAddCopyList().postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ResubmitContractRestartFragment.this, REQUEST_CODE_PICK_COPY_LIST, "请选择抄送人",
                        copyList, hiddenList, sendList, 10);

            }
        });

        viewModel.setOnClickSubmit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {
        DearNetHelper.getInstance().submitContractApply(getViewModel().genApplyBean(), submitCallback);
    }

    private DearNetHelper.NetCallback<Integer> submitCallback = new DearNetHelper.NetCallback<Integer>() {
        @Override
        public void onStart(Disposable disposable) {
            showLoading(getParentFragmentManager());
        }

        @Override
        public void onGetResult(Integer result) {
            hideLoading();
            showToastTip("提交成功。");
            //跳回上一个界面
            getActivity().setResult(RESULT_OK);
            getActivity().onBackPressed();
        }

        @Override
        public void onError(Throwable error) {
            hideLoading();
            showToastTip(error.getMessage());
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_resubmit_contract_restart;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_PICK_SEND_LIST) {
            if (resultCode == RESULT_OK) {
                ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
                getViewModel().sendList.postValue(list);
            }
        } else if (requestCode == REQUEST_CODE_PICK_COPY_LIST) {
            if (resultCode == RESULT_OK) {
                ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
                getViewModel().copyList.postValue(list);
            }
        }
    }
}
