package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProduceDetailSimpleBinding;
import com.guyuan.dear.dialog.RemarkDialog;
import com.guyuan.dear.dialog.SimpleConfirmViewDialog;

import com.guyuan.dear.focus.produce.bean.EventProduceListRefresh;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.OperateProduceType;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.guyuan.dear.office.approval.ui.ApprovalActivity.IS_APPROVED;

/**
 * @description: 我的关注--客户详情(NestedScrollView)
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceDetailSimpleFragment extends BaseDataBindingFragment<FragmentFocusProduceDetailSimpleBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceDetailSimpleFragment.class.getSimpleName();
    public static final String BUSINESS_ID = "businessId";
    public static final String BUSINESS_TYPE = "businessType";
    public static final String REMARKS = "remarks";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    private FollowProducePlanFragment planFragment;
    private FocusProduceBean produceBean;
    private boolean isFooterBtnShow;
    private int businessId = -1;
    private int businessType;
    private String remarks;
    private int status;
    private int type;
    private boolean isApproved = false;
    private RemarkDialog.OnDialogClickListener remarkListener;

    public static FocusProduceDetailSimpleFragment newInstance(FocusProduceBean data, boolean isFooterBtnShow) {
        Bundle bundle = new Bundle();
        FocusProduceDetailSimpleFragment fragment = new FocusProduceDetailSimpleFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        bundle.putSerializable(ConstantValue.KEY_BOOLEAN, isFooterBtnShow);
        fragment.setArguments(bundle);
        return fragment;
    }

    //审批入口
    public static FocusProduceDetailSimpleFragment newInstance(FocusProduceBean data, int businessId,
                                                               int businessType, int type, boolean isApproved) {
        Bundle bundle = new Bundle();
        FocusProduceDetailSimpleFragment fragment = new FocusProduceDetailSimpleFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        bundle.putSerializable(ConstantValue.KEY_BOOLEAN, false);
        bundle.putInt(BUSINESS_ID, businessId);
        bundle.putInt(BUSINESS_TYPE, businessType);
        bundle.putInt(TYPE, type);
        bundle.putBoolean(IS_APPROVED, isApproved);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_detail_simple;
    }

    @Override
    protected void initialization() {
        FragmentManager fragmentManager = getChildFragmentManager();
        planFragment = (FollowProducePlanFragment) fragmentManager.findFragmentById(R.id.factory_view);
        Bundle arguments = getArguments();
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        isFooterBtnShow = arguments.getBoolean(ConstantValue.KEY_BOOLEAN, false);
        setApproval();

        initDataAndListener();

    }

    //设置审批
    private void setApproval() {
        businessId = getArguments().getInt(BUSINESS_ID);
        businessType = getArguments().getInt(BUSINESS_TYPE);
        status = getArguments().getInt(STATUS);
        type = getArguments().getInt(TYPE);
        isApproved = getArguments().getBoolean(IS_APPROVED);
        if (isApproved) {
            setRemarkDialogListener();
            binding.produceApprovalLl.setVisibility(View.VISIBLE);
            binding.produceRejectTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.REJECT;
                    RemarkDialog.show(getActivity(), "请输入驳回备注", remarkListener);
                }
            });

            binding.produceAcceptTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.ACCEPT;
                    RemarkDialog.show(getActivity(), "请输入通过备注", remarkListener);
                }
            });
        }
    }


    private void setRemarkDialogListener() {
        remarkListener = new RemarkDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(ExecuteRequestBody data) {
                String remark = data.getReason();
                viewModel.approval(businessId, businessType, remark, status, type);
            }
        };
    }


    private void initDataAndListener() {
        binding.tvCommitBtn.setVisibility(isFooterBtnShow ? View.VISIBLE : View.GONE);
        viewModel.getBasicInfoById(produceBean.getPlanId());
        viewModel.getBasicInfoEvent().observe(getActivity(), new Observer<FocusProduceBean>() {
            @Override
            public void onChanged(FocusProduceBean data) {
                setProduceData(data);
            }
        });
        SimpleConfirmViewDialog.OnClickListener listener = new SimpleConfirmViewDialog.OnClickListener() {
            @Override
            public void onConfirm() {
                ExecuteRequestBody body = new ExecuteRequestBody();
                body.setEquipmentId(produceBean.getEquipmentId());
                body.setId(produceBean.getPlanId());
                body.setType(OperateProduceType.TYPE_EXECUTE_START.getCode());
                String str = GsonUtil.objectToString(body);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postExecuteProduceInfo(requestBody);
            }
        };

        binding.tvCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleConfirmViewDialog.showTitle(getContext(), "确认提交生产开始吗？", listener);
            }
        });

        viewModel.getExecuteEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
//                ToastUtils.showLong(getContext(), "提交成功!");
                EventBus.getDefault().post(new EventProduceListRefresh());
                getActivity().finish();
            }
        });
    }


    private void setProduceData(FocusProduceBean data) {
        planFragment.setProduceData(data);

        binding.tvProductName.setText(data.getProjectName());
        binding.tvProductCode.setText(data.getProjectCode());
        binding.tvDutyUnit.setText(data.getPrincipalDept());

        //设置生产状态
        binding.tvProduceStatus.setText(data.getStatusText());
        binding.tvProduceStatus.setBackgroundResource(data.getStatusTextBg());
        int color_blue_ff1b97fc = data.getStatusTextColor();
        binding.tvProduceStatus.setTextColor(getActivity().getResources().getColor(color_blue_ff1b97fc));
//        if (data.getStatusType() == ProductStatusType.TYPE_PRODUCE_DELAY_NOT_FINISH
//                || data.getStatusType() == ProductStatusType.TYPE_PRODUCE_DELAY_FINISH) {
//            binding.tvSubStatus.setVisibility(View.VISIBLE);
//        } else {
//            binding.tvSubStatus.setVisibility(View.GONE);
//        }

        binding.tvProjectName.setText(data.getProjectName());

        binding.tvActualStart.setText(data.getActualStartTime());
        binding.tvPlanStart.setText(data.getPlanStartTime());
        binding.tvActualComplete.setText(data.getActualEndTime());
        binding.tvPlanComplete.setText(data.getPlanEndTime());


    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
