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
import com.guyuan.dear.dialog.TipDialogFragment;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailFragment;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalTypeBean;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.office.approval.ui.ApprovalFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.views.applyDetail.ContractPauseApplyDetailFragment;
import com.guyuan.dear.work.contractRestart.view.detail.ContractRestartDetailFragment;
import com.taobao.accs.IAliyunAppReceiver;

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
    public static final String APPROVAL_TYPE = "approvalType";
    public static final String BUSINESS_ID = "businessId";

    private Fragment fragment;
    private int type;
    private int approvalType;
    private View.OnClickListener commitListener;
    private View.OnClickListener rejectListener;


    public static void start(Context context, String title, int type, int approvalType, int id, int businessId) {
        Intent starter = new Intent(context, ApprovalDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_TYPE, type);
        starter.putExtra(APPROVAL_TYPE, approvalType);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(BUSINESS_ID, businessId);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setViewModelToFragment(false);
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        type = getIntent().getIntExtra(ConstantValue.KEY_TYPE, 0);
        approvalType = getIntent().getIntExtra(APPROVAL_TYPE, approvalType);
        if (approvalType == ApprovalFragment.APPROVAL) {
            binding.approvalLl.setVisibility(View.VISIBLE);
        }
        setContent();
    }

    private void setContent() {
        viewModel.setListener(this);
        switch (type) {
            //合同审批
            //case ApprovalTypeBean.CONTRACT_EXAMINE_MASTER_TYPE:
            case ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE:
            case ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_RESTART_TYPE:
                int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
                if (type == ApprovalTypeBean.CONTRACT_EXAMINE_STATUS_STOP_TYPE) {
                    fragment = ContractPauseApplyDetailFragment.getInstance(id);
                } else {
                    fragment = ContractRestartDetailFragment.getInstance((long) id);
                }
                binding.approvalAcceptTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailContractBean detailContractBean = ((ContractDetailFragment) fragment).getContractBean();
                        TipDialogFragment tipDialogFragment = TipDialogFragment.newInstance("", "确定通过吗?");
                        tipDialogFragment.setOnSureListener(new TipDialogFragment.OnSure() {
                            @Override
                            public void sure() {
                                viewModel.contractApproval(type, (int) detailContractBean.getContractId(), "", ApprovalActivity.ACCEPT);
                                tipDialogFragment.dismiss();
                            }
                        })
                                .setOnCancelListener(new TipDialogFragment.OnCancel() {
                                    @Override
                                    public void cancel() {
                                        tipDialogFragment.dismiss();
                                    }
                                }).show(fragmentManager, TipDialogFragment.TAG);

//                        if (bean != null) {
//                            RemarkDialog.show(ApprovalDetailActivity.this, "请输入驳回备注", new RemarkDialog.OnDialogClickListener() {
//                                @Override
//                                public void onCommitInfo(ExecuteRequestBody data) {
//                                    viewModel.contractApproval(type, (int) bean.getContractId(), remarks, ApprovalActivity.ACCEPT);
//                                }
//                            });
//                        } else {
//                            showToastTip("获取数据失败,无法提交");
//                        }
                    }
                });


                binding.approvalRejectTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailContractBean bean = ((ContractDetailFragment) fragment).getContractBean();
                        if (bean != null) {
                            RemarkDialog.show(ApprovalDetailActivity.this, "请输入驳回备注", new RemarkDialog.OnDialogClickListener() {
                                @Override
                                public void onCommitInfo(ExecuteRequestBody data) {
                                    viewModel.contractApproval(type, (int) bean.getContractId(), data.getReason(), ApprovalActivity.REJECT);
                                }
                            });
                        } else {
                            showToastTip("获取数据失败,无法提交");
                        }
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
                int businessID = getIntent().getIntExtra(BUSINESS_ID, 0);
                data.setPlanId(businessID);
               if(type==ApprovalTypeBean.ACTIVATE_PLAN){
                    data.setStopStatus(1);
                }else {
                    data.setStopStatus(0);
                }
                fragment = FocusProduceDetailFragment.newInstance(data, false);
                binding.approvalAcceptTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FocusProduceBean focusProduceBean = ((FocusProduceDetailFragment) fragment).getProduceBean();
                        TipDialogFragment tipDialogFragment = TipDialogFragment.newInstance("", "确定通过吗?");
                        tipDialogFragment.setOnSureListener(new TipDialogFragment.OnSure() {
                            @Override
                            public void sure() {
                                viewModel.produceApproval((int) focusProduceBean.getPlanId(), 2, "", ApprovalActivity.ACCEPT, getProduceApprovalType(type));
                                tipDialogFragment.dismiss();
                            }
                        })
                                .setOnCancelListener(new TipDialogFragment.OnCancel() {
                                    @Override
                                    public void cancel() {
                                        tipDialogFragment.dismiss();
                                    }
                                }).show(fragmentManager, TipDialogFragment.TAG);
                    }
                });


                binding.approvalRejectTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FocusProduceBean bean = ((FocusProduceDetailFragment) fragment).getProduceBean();
                        if (bean != null) {
                            RemarkDialog.show(ApprovalDetailActivity.this, "请输入驳回备注", new RemarkDialog.OnDialogClickListener() {
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