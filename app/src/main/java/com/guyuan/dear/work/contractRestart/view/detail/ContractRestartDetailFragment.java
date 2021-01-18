package com.guyuan.dear.work.contractRestart.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.busbean.MessagePushBusBean;
import com.guyuan.dear.busbean.UpdatePauseApplyListEvent;
import com.guyuan.dear.busbean.UpdateRestartApplyListEvent;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.databinding.FragmentContractRestartApplyDetailBinding;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.focus.contract.view.contractApplyDetail.ContractRestartApplyDetailViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractRestart.view.resubmit.ResubmitContractRestartActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/7 19:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractRestartDetailFragment extends BaseMvvmFragment<FragmentContractRestartApplyDetailBinding, ContractRestartApplyDetailViewModel> {

    private long examineId;
    private static final int REQ_CODE_RESUBMIT_RESTART = 541;

    public static ContractRestartDetailFragment getInstance(Long examineId) {
        ContractRestartDetailFragment fragment = new ContractRestartDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ConstantValue.KEY_EXAMINE_ID, examineId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contract_restart_apply_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        examineId = arguments.getLong(ConstantValue.KEY_EXAMINE_ID);
        addDisposable(getViewModel().getContractDetailFromNet(examineId));
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Object event) {
        if (event instanceof MessagePushBusBean) {
            MessageBean messageBean = ((MessagePushBusBean) event).getMessageBean();
            if (messageBean != null && messageBean.getBusinessStatus() != -100) {
                addDisposable(getViewModel().getContractDetailFromNet(examineId));
            }
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().onClickReSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResubmitContractRestartActivity.startForResult(
                        ContractRestartDetailFragment.this,
                        getViewModel().genApplyBean(),
                        REQ_CODE_RESUBMIT_RESTART);
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_restart_apply_detail;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.showLog("req=" + requestCode + " rsl=" + resultCode);
        if (requestCode == REQ_CODE_RESUBMIT_RESTART && resultCode == RESULT_OK) {
            LogUtils.showLog("onActivityResult");
//            addDisposable(getViewModel().getMyApplyDetailFromNet(examineId));
            //合同重新申请暂停成功后，退出本界面，并通过事件总线更新合同列表的合同最新状态。
            UpdateRestartApplyListEvent event = new UpdateRestartApplyListEvent();
            MyApplyBean bean = new MyApplyBean();
            bean.setExamineId((int) examineId);
            bean.setApplyState(MyApplyBean.APPLY_PROCESSING);
            event.setBean(bean);
            EventBus.getDefault().post(event);
            FragmentActivity activity = getActivity();
            activity.onBackPressed();
        }
    }


    //审批需要获取详情页数据
    public DetailContractApplyBean getContractBean() {
        return getViewModel().getDetailBean().getValue();
    }
}
