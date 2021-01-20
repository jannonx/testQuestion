package com.guyuan.dear.office.approval.ui.approvalDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.busbean.ApprovalBusBean;
import com.guyuan.dear.databinding.ActivityApprovalDetailBinding;
import com.guyuan.dear.dialog.RemarkDialog;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment;
import com.guyuan.dear.office.approval.adapter.ApprovalBindingAdapter;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;
import com.guyuan.dear.office.approval.data.bean.ApprovalTypeBean;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.office.approval.ui.ApprovalFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.views.applyDetail.ContractPauseApplyDetailFragment;
import com.guyuan.dear.work.contractRestart.view.detail.ContractRestartDetailFragment;

import org.greenrobot.eventbus.EventBus;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/1 16:27
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class ApprovalDetailActivity extends BaseToolbarActivity<
        ActivityApprovalDetailBinding, ApprovalViewModel> implements ApprovalViewModel.ApprovalViewModelListener {

    public static final String TAG = "ApprovalDetailActivity";
    public static final String APPROVAL = "approval";

    private Fragment fragment;
    private int type;//审批类型
    private int approvalType;//审批状态：已审批，未审批
    private View.OnClickListener commitListener;
    private View.OnClickListener rejectListener;
    private ApprovalListBean.ContentBean approvalBean;

    public static void start(Context context, int type, ApprovalListBean.ContentBean bean) {
        Intent starter = new Intent(context, ApprovalDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TYPE, type);
        starter.putExtra(APPROVAL, bean);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setViewModelToFragment(false);
        approvalType = getIntent().getIntExtra(ConstantValue.KEY_TYPE, 0);
        approvalBean = getIntent().getParcelableExtra(APPROVAL);
        if (approvalBean != null) {
            setTitleCenter(approvalBean.getBusinessName() + "详情");
            type = approvalBean.getArType();
            if (approvalType == ApprovalFragment.APPROVAL) {
                binding.approvalLl.setVisibility(View.VISIBLE);
                ApprovalBindingAdapter.setApprovalStatus(binding.approvalTagTv, approvalBean.getStatus(), true);
            } else {
                ApprovalBindingAdapter.setApprovalStatus(binding.approvalTagTv, approvalBean.getStatus(), false);
            }
            setContent();
        }

    }

    private void setContent() {
        binding.approvalTitleTv.setText(approvalBean.getCreateName() + "提交的" + approvalBean.getBusinessName());
        binding.approvalTimeTv.setText(approvalBean.getCreateTime());
        viewModel.setListener(this);
        switch (type) {
            //合同审批
            //case ApprovalTypeBean.CONTRACT_EXAMINE_MASTER_TYPE:
            case ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE:
            case ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_RESTART_TYPE:
                int id = approvalBean.getId();
//                fragment = getContractDetailFragment(type, id);
                fragment = ApprovalDetailFragment.getInstance(type, id);
                String tip = getContractTip(type);
                binding.approvalAcceptTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RemarkDialog.show(ApprovalDetailActivity.this, "通过原因", "", new RemarkDialog.OnDialogClickListener() {
                            @Override
                            public void onCommitInfo(ExecuteRequestBody data) {
                                DetailContractApplyBean detailContractApplyBean = null;
                                if (fragment instanceof ContractPauseApplyDetailFragment) {
                                    detailContractApplyBean = ((ContractPauseApplyDetailFragment) fragment).getContractBean();
                                } else if (fragment instanceof ContractRestartDetailFragment) {
                                    detailContractApplyBean = ((ContractRestartDetailFragment) fragment).getContractBean();
                                }
                                if (detailContractApplyBean != null) {
                                    viewModel.contractApproval(type, (int) detailContractApplyBean.getContractId(), data.getReason(), ApprovalActivity.ACCEPT);
                                }
                            }
                        });

                    }
                });


                binding.approvalRejectTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemarkDialog.show(ApprovalDetailActivity.this, "驳回原因", tip, new RemarkDialog.OnDialogClickListener() {
                            @Override
                            public void onCommitInfo(ExecuteRequestBody data) {
                                DetailContractApplyBean detailContractApplyBean = null;
                                if (fragment instanceof ContractPauseApplyDetailFragment) {
                                    detailContractApplyBean = ((ContractPauseApplyDetailFragment) fragment).getContractBean();
                                } else if (fragment instanceof ContractRestartDetailFragment) {
                                    detailContractApplyBean = ((ContractRestartDetailFragment) fragment).getContractBean();
                                }
                                if (detailContractApplyBean != null) {
                                    viewModel.contractApproval(type, (int) detailContractApplyBean.getContractId(), data.getReason(), ApprovalActivity.REJECT);
                                } else {
                                    showToastTip("获取数据失败,无法提交");
                                }
                            }
                        });
                    }
                });

                break;

            //回款审批
            case ApprovalTypeBean.PAYMENT_COLLECTION:

                break;
            //质保金审批
            case ApprovalTypeBean.QUALITY_DEPOSIT:

                break;
            //运输审批
            case ApprovalTypeBean.TRANSPORT:

                break;

            //设备一览审批
            case ApprovalTypeBean.EQUIPMENT_EDIT:
            case ApprovalTypeBean.EQUIPMENT_CHECK:

                break;

            //生产审批
            case ApprovalTypeBean.MAKING_PLAN:
            case ApprovalTypeBean.PAUSE_PLAN:
            case ApprovalTypeBean.ACTIVATE_PLAN:
                FocusProduceBean data = new FocusProduceBean();
                int businessID = approvalBean.getBusinessId();
                data.setPlanId(businessID);
                data.setStopStatus(0);
                fragment = FocusProduceDetailFragment.newInstance(data, false);
                binding.approvalAcceptTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        FocusProduceBean focusProduceBean = ((FocusProduceDetailFragment) fragment).getProduceBean();
//                        TipDialogFragment tipDialogFragment = TipDialogFragment.newInstance("", "确定通过吗?");
//                        tipDialogFragment.setOnSureListener(new TipDialogFragment.OnSure() {
//                            @Override
//                            public void sure() {
//                                viewModel.produceApproval((int) focusProduceBean.getPlanId(), 2, "", ApprovalActivity.ACCEPT, getProduceApprovalType(type));
//                                tipDialogFragment.dismiss();
//                            }
//                        })
//                                .setOnCancelListener(new TipDialogFragment.OnCancel() {
//                                    @Override
//                                    public void cancel() {
//                                        tipDialogFragment.dismiss();
//                                    }
//                                }).show(fragmentManager, TipDialogFragment.TAG);

                        FocusProduceBean bean = ((FocusProduceDetailFragment) fragment).getProduceBean();
                        if (bean != null) {
                            RemarkDialog.show(ApprovalDetailActivity.this, "通过原因", "", new RemarkDialog.OnDialogClickListener() {
                                @Override
                                public void onCommitInfo(ExecuteRequestBody data) {
                                    //businessType传2:子生产计划
                                    viewModel.produceApproval((int) bean.getPlanId(), 2, data.getReason(), ApprovalActivity.REJECT, getProduceApprovalType(type));
                                }
                            });
                        } else {
                            showToastTip("获取数据失败,无法提交");
                        }

                    }
                });


                binding.approvalRejectTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FocusProduceBean bean = ((FocusProduceDetailFragment) fragment).getProduceBean();
                        if (bean != null) {
                            RemarkDialog.show(ApprovalDetailActivity.this, "驳回原因", "", new RemarkDialog.OnDialogClickListener() {
                                @Override
                                public void onCommitInfo(ExecuteRequestBody data) {
                                    //businessType传2:子生产计划
                                    viewModel.produceApproval((int) bean.getPlanId(), 2, data.getReason(), ApprovalActivity.REJECT, getProduceApprovalType(type));
                                }
                            });
                        } else {
                            showToastTip("获取数据失败,无法提交");
                        }
                    }
                });
                break;

            //设备任务分解
            case ApprovalTypeBean.PROJECT_TASK_SPLIT:

                break;
            //运输管理模块
            case ApprovalTypeBean.TRANSPORT_PROJECT:

                break;
            //采购审批
            case ApprovalTypeBean.MAKING_CONTRACT:

                break;
            //质检审批
            case ApprovalTypeBean.MAKING_CODE:
            case ApprovalTypeBean.MAKING_REPORT:

                break;
            //成本核算
//            case ApprovalTypeBean.COST_ACCOUNTING:
//
//                break;

            //库存审批
            case ApprovalTypeBean.MAKING_REQUEST:

                break;
        }
        if (fragment != null) {
            ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.approval_detail_container, TAG);
        } else {
            showToastTip("无该审批类型");
        }
    }


    private int getProduceApprovalType(int type) {
        switch (type) {
            case ApprovalTypeBean.MAKING_PLAN:

                return 1;

            case ApprovalTypeBean.PAUSE_PLAN:

                return 2;

            case ApprovalTypeBean.ACTIVATE_PLAN:

                return 3;

            default:

                return 0;
        }
    }

    private Fragment getContractDetailFragment(int type, int id) {
        Fragment contractDetailFragment;
        if (type == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE) {
            contractDetailFragment = ContractPauseApplyDetailFragment.getInstance(id);
        } else {
            contractDetailFragment = ContractRestartDetailFragment.getInstance((long) id);
        }

        return contractDetailFragment;
    }


    private String getContractTip(int type) {

        if (type == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE) {
            return getString(R.string.contract_pause_tip);
        } else if (type == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_RESTART_TYPE) {
            return getString(R.string.contract_restart_tip);
        } else {
            return "";
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_approval_detail;
    }

    @Override
    public void handled() {
        showToastTip("提交成功");
        EventBus.getDefault().post(new ApprovalBusBean());
        finish();
    }
}