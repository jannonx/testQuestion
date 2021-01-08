package com.guyuan.dear.work.contractRestart.view.apply;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.httplibrary.bean.BasePageResultBean;
import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentContractStatusListBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetBaseContractInfo;
import com.guyuan.dear.net.resultBeans.NetClientInfo;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.ContractPauseInfo;
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
 * @since: 2021/1/7 17:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractRestartFragment extends BaseMvvmFragment<FragmentContractStatusListBinding,ContractRestartApplyViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;


    public static ContractRestartFragment getInstance(){
        return new ContractRestartFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_restart_contract_vm;
    }

    @Override
    protected void initData() {
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
        ContractRestartApplyViewModel viewModel = getViewModel();

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

        viewModel.setOnClickAddSendList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ContractRestartFragment.this, REQUEST_CODE_PICK_SEND_LIST, "请选择审批人",
                        sendList, hiddenList, copyList, 10);
            }
        });

        viewModel.setOnClickAddCopyList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().sendList.getValue();
                ArrayList<StaffBean> copyList = getViewModel().copyList.getValue();
                ArrayList<StaffBean> hiddenList = getViewModel().hiddenList.getValue();
                PickStaffsActivity.startForResult(ContractRestartFragment.this, REQUEST_CODE_PICK_COPY_LIST, "请选择抄送人",
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

    private void selectBuyer() {
        Disposable disposable = DearNetHelper.getInstance().getClientList(ContractApplyBean.APPLY_TYPE_RESUME,getClientsCallback);
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

    private void selectContract() {
        Disposable disposable = DearNetHelper.getInstance().getBaseContractListByClientId(
                getViewModel().getClientId(), 1, getContractListCallback
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
                        ContractPauseInfo info = new ContractPauseInfo(bean);
                        getViewModel().contractPauseInfo.postValue(info);
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
            if (activity instanceof ContractPauseHomeActivity) {
                ContractPauseHomeViewModel viewModel = ((ContractPauseHomeActivity) activity).getViewModel();
                viewModel.refreshMyPauseApplyList.postValue(true);
            } else if (activity instanceof ContractRestartHomeActivity) {
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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_apply_contract_restart;
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
