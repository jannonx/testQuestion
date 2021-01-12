package com.guyuan.dear.work.contractPause.views.resubmit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentResubmitPauseContractApplyBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractPauseBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/11 16:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ResubmitContractPauseFragment extends BaseMvvmFragment<FragmentResubmitPauseContractApplyBinding, ResubmitContractPauseViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;

    public static ResubmitContractPauseFragment getInstance(ContractPauseBean bean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN, bean);
        ResubmitContractPauseFragment fragment = new ResubmitContractPauseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return com.guyuan.dear.BR.fragment_resubmit_pause_contract_vm;
    }

    @Override
    protected void initData() {
        //选人时把自己过滤掉
        StaffBean me = new StaffBean();
        me.setId(CommonUtils.getCurrentUserId());
        getViewModel().hiddenList.getValue().add(me);

        Bundle bundle = getArguments();
        ContractPauseBean bean = bundle.getParcelable(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN);
        getViewModel().showPreviousApply(bean);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ResubmitContractPauseViewModel viewModel = getViewModel();

        viewModel.onClickSelectJudgement.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectJudgement();
            }
        });


        viewModel.onClickAddCopyList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ResubmitContractPauseFragment.this, REQUEST_CODE_PICK_SEND_LIST, "请选择审批人",
                        sendList, hiddenList, copyList, 10);
            }
        });

        viewModel.onClickAddCopyList.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ResubmitContractPauseFragment.this, REQUEST_CODE_PICK_COPY_LIST, "请选择抄送人",
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

    private void selectJudgement() {
        Disposable disposable = DearNetHelper.getInstance().getJudgeConditions(getJudgeConCallback);
        addDisposable(disposable);
    }

    private DearNetHelper.NetCallback<List<NetServerParam.JudgeCondition>> getJudgeConCallback
            = new DearNetHelper.NetCallback<List<NetServerParam.JudgeCondition>>() {
        @Override
        public void onStart(Disposable disposable) {
            showLoading(getParentFragmentManager());

        }

        @Override
        public void onGetResult(List<NetServerParam.JudgeCondition> result) {
            showSelectJudgeConditionDialog(result);
            hideLoading();
        }

        @Override
        public void onError(Throwable error) {
            hideLoading();
            showToastTip(error.getMessage());
        }
    };

    private void showSelectJudgeConditionDialog(List<NetServerParam.JudgeCondition> result) {
        SelectionDialog<NetServerParam.JudgeCondition> dialog = new SelectionDialog<NetServerParam.JudgeCondition>(getContext()) {
            @Override
            public List<NetServerParam.JudgeCondition> setListData() {
                return result;
            }

            @Override
            public OnSelectItemClickListener<NetServerParam.JudgeCondition> setOnItemClick() {
                return new OnSelectItemClickListener<NetServerParam.JudgeCondition>() {
                    @Override
                    public void onItemClick(NetServerParam.JudgeCondition bean, int position) {
                        getViewModel().updateJudgeCondition(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(NetServerParam.JudgeCondition item) {
                return item.getValue();
            }
        };
        dialog.show();
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
//            //1,重置UI
//            getViewModel().resetAllViews();
//            //2，更新申请列表
//            FragmentActivity activity = getActivity();
//            if (activity instanceof ContractPauseHomeActivity) {
//                ContractPauseHomeViewModel viewModel = ((ContractPauseHomeActivity) activity).getViewModel();
//                viewModel.refreshMyPauseApplyList.postValue(true);
//            } else if (activity instanceof ContractRestartHomeActivity) {
//                ContractRestartHomeViewModel viewModel = ((ContractRestartHomeActivity) activity).getViewModel();
//                viewModel.refreshMyRestartApplyList.postValue(true);
//            }
            FragmentActivity activity = getActivity();
            activity.setResult(RESULT_OK);
            activity.onBackPressed();
        }

        @Override
        public void onError(Throwable error) {
            hideLoading();
            showToastTip(error.getMessage());
        }
    };


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_resubmit_pause_contract_apply;
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
