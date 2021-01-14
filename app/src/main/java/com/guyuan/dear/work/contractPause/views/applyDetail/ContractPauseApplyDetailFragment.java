package com.guyuan.dear.work.contractPause.views.applyDetail;

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
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.databinding.FragmentContractPauseApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.views.resubmit.ResubmitContractPauseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:17
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPauseApplyDetailFragment extends BaseMvvmFragment<FragmentContractPauseApplyDetailBinding, ContractPauseApplyDetailViewModel> {

    private int examineId;
    private static final int REQ_CODE_RESUBMIT_PAUSE_APPLY = 123;

    public static ContractPauseApplyDetailFragment getInstance(int examineId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_ID, examineId);
        ContractPauseApplyDetailFragment fragment = new ContractPauseApplyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_my_apply_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        examineId = bundle.getInt(ConstantValue.KEY_ID);
        addDisposable(getViewModel().getMyApplyDetailFromNet(examineId));
        getViewDataBinding().fragmentMyPauseApplyDetailNestedScrollerView.fullScroll(View.FOCUS_UP);
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
                addDisposable(getViewModel().getMyApplyDetailFromNet(examineId));
            }
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().onClickResubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResubmitContractPauseActivity.startForResult(
                        ContractPauseApplyDetailFragment.this,
                        getViewModel().genContractPauseBean(),
                        REQ_CODE_RESUBMIT_PAUSE_APPLY);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.showLog("req=" + requestCode + " rsl=" + resultCode);
        if (requestCode == REQ_CODE_RESUBMIT_PAUSE_APPLY && resultCode == RESULT_OK) {
            LogUtils.showLog("onActivityResult");
//            addDisposable(getViewModel().getMyApplyDetailFromNet(examineId));
            //合同重新申请暂停成功后，退出本界面，并通过事件总线更新合同列表的合同最新状态。
            UpdatePauseApplyListEvent event = new UpdatePauseApplyListEvent();
            MyApplyBean bean = new MyApplyBean();
            bean.setExamineId(examineId);
            bean.setApplyState(MyApplyBean.APPLY_PROCESSING);
            event.setBean(bean);
            EventBus.getDefault().post(event);
            FragmentActivity activity = getActivity();
            activity.onBackPressed();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_pause_apply_detail;
    }
}
