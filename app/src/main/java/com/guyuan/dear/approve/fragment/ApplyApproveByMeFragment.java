package com.guyuan.dear.approve.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.guyuan.dear.R;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.approve.activity.ApplyApproveByMeActivity;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApproveUserBean;
import com.guyuan.dear.approve.bean.BodyApprovalSubmit;
import com.guyuan.dear.approve.bean.EventSubmitOK;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.databinding.FragmentApplyApproveByMeBinding;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * @description: 掌上办公--审批--我发起的/抄送我的--详情
 * @author:Jannonx
 * @date: 2020/6/18 15:38
 */
public class ApplyApproveByMeFragment extends BaseDataBindingFragment<FragmentApplyApproveByMeBinding> implements View.OnClickListener {

    public static final String TAG = ApplyApproveByMeFragment.class.getSimpleName();


    private ApplyBean mData;
    private EventSubmitOK busEventSubmitOK;
    private BodyApprovalSubmit body;
    private ApplyApproveByMeActivity activity;
    private ApproveViewModel viewModel;

    public static ApplyApproveByMeFragment newInstant(ApplyBean apply) {
        Bundle bundle = new Bundle();
        ApplyApproveByMeFragment fragment = new ApplyApproveByMeFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, apply);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (ApplyApproveByMeActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_apply_approve_by_me;
    }

    @Override
    protected void initialization() {
        mData = (ApplyBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        ApproveUserBean user = mData.getUser();
        String userName = user.getNickName();
        binding.tvUserName.setText(userName);
        binding.tvApplyName.setText(userName + "提交的" + mData.getApplyType());
        binding.tvStartTime.setText(mData.getSleaveTime());
        binding.tvEndTime.setText(mData.getEleaveTime());
        binding.tvRemarks.setText(mData.getDescription());
        binding.tvDeptName.setText(user.getDeptName());
        //getTimeToLong
        CalenderUtils calenderUtils = CalenderUtils.getInstance();

        if (!TextUtils.isEmpty(mData.getSleaveTime()) && !TextUtils.isEmpty(mData.getEleaveTime())) {
            long sTime = calenderUtils.getTimeToLong(mData.getSleaveTime());
            long eTime = calenderUtils.getTimeToLong(mData.getEleaveTime());
            binding.tvApproveDuration.setText(calenderUtils.getHourAndMinuteDescription(eTime - sTime));
        }

        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(binding.ivUserAvatar, user.getImgUrl());
        //请假显示具体类型
        if (mData.getArType() == 1) {
            binding.tvApplyType.setVisibility(View.VISIBLE);
            binding.tvApplyType.setText(mData.getVacationType());
        } else {
            binding.tvApplyType.setVisibility(View.GONE);
        }
        binding.tvBtnReject.setOnClickListener(this);
        binding.tvBtnPass.setOnClickListener(this);
    }


    private void submitMyApproval(boolean isApprove) {
        body = getRequestBody(isApprove);
        if (!isApprove && TextUtils.isEmpty(body.getRemark())) {
            showToastTip("请填写拒绝原因。");
        } else {
            if (!isApprove) {
                showAlertDialogDoubleCheckRejection(body);
            } else {
                postApproveByMe(body);
            }
        }
    }

    private void postApproveByMe(BodyApprovalSubmit body) {
        String str = new Gson().toJson(body);
//        mPresenter.postApproveByMe(RequestBody.create(MediaType.parse("application/json; " +
//                "charset=utf-8"), str));
    }


    public void showApplyInfo(Integer result) {
        showToastTip("提交成功！");
        busEventSubmitOK = new EventSubmitOK();
        busEventSubmitOK.setApplyId(mData.getId());
        busEventSubmitOK.setApprove(body.isToBeApproved());
        activity.finish();
    }


    private void showAlertDialogDoubleCheckRejection(BodyApprovalSubmit body) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle("审批拒绝")
                .setMessage("请问你确定要拒绝本申请吗？")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        postApproveByMe(body);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    private BodyApprovalSubmit getRequestBody(boolean isApprove) {
        BodyApprovalSubmit body = new BodyApprovalSubmit();
        body.setId(mData.getSessionId());
        body.setStatus(isApprove ? 1 : 2);
        body.setRemark(binding.etComment.getText().toString().trim());
        return body;
    }

//    @Override
//    public void onUnBind() {

//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (busEventSubmitOK != null) {
            EventBus.getDefault().post(busEventSubmitOK);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //拒绝
            case R.id.tv_btn_reject:
                submitMyApproval(false);
                break;
            //同意
            case R.id.tv_btn_pass:
                submitMyApproval(true);
                break;
            default:
        }
    }
}
