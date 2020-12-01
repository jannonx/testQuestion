package com.guyuan.dear.work.contractPause.views.applyWindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.httplibrary.bean.BasePageResultBean;
import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentPauseContractBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.net.resultBeans.NetServerParam;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeActivity;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeViewModel;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeActivity;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 11:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyFragment extends BaseMvvmFragment<FragmentPauseContractBinding, ContractApplyViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;

    /**
     * @param applyType 参考 {@link ContractApplyBean#APPLY_TYPE_RESUME} , {@link ContractApplyBean#APPLY_TYPE_PAUSE}
     * @return
     */
    public static ContractApplyFragment getInstance(int applyType) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_APPLY_TYPE, applyType);
        ContractApplyFragment fragment = new ContractApplyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_pause_contract_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int type = bundle.getInt(ConstantValue.KEY_APPLY_TYPE, ContractApplyBean.APPLY_TYPE_PAUSE);
        getViewModel().setApplyType(type);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ContractApplyViewModel viewModel = getViewModel();

        viewModel.setOnClickSelectBuyer(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBuyer();
            }
        });

        viewModel.setOnClickSelectContract(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectContract();

            }
        });

        viewModel.setOnClickSelectJudgement(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectJudgement();

            }
        });


        viewModel.setOnClickAddSendList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                PickStaffsActivity.startForResult(ContractApplyFragment.this, REQUEST_CODE_PICK_SEND_LIST, "请选择审批人",
                        sendList, null, copyList, 10);
            }
        });

        viewModel.setOnClickAddCopyList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                PickStaffsActivity.startForResult(ContractApplyFragment.this, REQUEST_CODE_PICK_COPY_LIST, "请选择抄送人",
                        copyList, null, sendList, 10);

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
            //1,重置UI
            getViewModel().resetAllViews();
            //2，更新申请列表
            FragmentActivity activity = getActivity();
            if(activity instanceof ContractPauseHomeActivity){
                ContractPauseHomeViewModel viewModel = ((ContractPauseHomeActivity) activity).getViewModel();
                viewModel.refreshMyPauseApplyList.postValue(true);
            }else if(activity instanceof ContractRestartHomeActivity){
                ContractRestartHomeViewModel viewModel = ((ContractRestartHomeActivity) activity).getViewModel();
                viewModel.refreshMyRestartApplyList.postValue(true);
            }
        }

        @Override
        public void onError(Throwable error) {
            hideLoading();
            showToastTip(error.getMessage());
        }
    };

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

    private void selectContract() {
        Disposable disposable = DearNetHelper.getInstance().getBaseContractListByClientId(
                getViewModel().getClientId(),
                getContractListCallback
        );
        addDisposable(disposable);
    }

    private DearNetHelper.NetCallback<List<NetBaseContractInfo>> getContractListCallback
            = new DearNetHelper.NetCallback<List<NetBaseContractInfo>>() {
        @Override
        public void onStart(Disposable disposable) {
            showLoading(getParentFragmentManager());
        }

        @Override
        public void onGetResult(List<NetBaseContractInfo> result) {
            showSelectContractDialog(result);
            hideLoading();
        }

        @Override
        public void onError(Throwable error) {
            hideLoading();
            showToastTip(error.getMessage());
        }
    };

    private void showSelectContractDialog(List<NetBaseContractInfo> result) {
        SelectionDialog<NetBaseContractInfo> dialog = new SelectionDialog<NetBaseContractInfo>(getContext()) {
            @Override
            public List<NetBaseContractInfo> setListData() {
                return result;
            }

            @Override
            public OnSelectItemClickListener<NetBaseContractInfo> setOnItemClick() {
                return new OnSelectItemClickListener<NetBaseContractInfo>() {
                    @Override
                    public void onItemClick(NetBaseContractInfo bean, int position) {
                        getViewModel().updateContract(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(NetBaseContractInfo item) {
                return item.getContractNum();
            }
        };
        dialog.show();
    }

    private void selectBuyer() {
        Disposable disposable = DearNetHelper.getInstance().getClientList(getClientsCallback);
        addDisposable(disposable);
    }

    private DearNetHelper.NetCallback<BasePageResultBean<NetClientInfo>> getClientsCallback =
            new DearNetHelper.NetCallback<BasePageResultBean<NetClientInfo>>() {
                @Override
                public void onStart(Disposable disposable) {
                    showLoading(getParentFragmentManager());
                }

                @Override
                public void onGetResult(BasePageResultBean<NetClientInfo> result) {
                    showSelectBuyerDialog(result);
                    hideLoading();
                }

                @Override
                public void onError(Throwable error) {
                    hideLoading();
                    showToastTip(error.getMessage());
                }
            };

    private void showSelectBuyerDialog(BasePageResultBean<NetClientInfo> result) {
        SelectionDialog<NetClientInfo> dialog = new SelectionDialog<NetClientInfo>(getContext()) {
            @Override
            public List<NetClientInfo> setListData() {
                return result.getContent();
            }

            @Override
            public OnSelectItemClickListener<NetClientInfo> setOnItemClick() {
                return new OnSelectItemClickListener<NetClientInfo>() {
                    @Override
                    public void onItemClick(NetClientInfo bean, int position) {
                        getViewModel().updateClient(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(NetClientInfo item) {
                return item.getCusName();
            }
        };
        dialog.show();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pause_contract;
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
