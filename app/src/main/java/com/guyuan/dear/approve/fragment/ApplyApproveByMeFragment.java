//package com.guyuan.dear.approve.fragment;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.widget.AppCompatEditText;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.appcompat.widget.AppCompatTextView;
//
//import com.google.gson.Gson;
//
//
//import org.greenrobot.eventbus.EventBus;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//
//
///**
// * @description: 掌上办公--审批--我发起的/抄送我的--详情
// * @author:Jannonx
// * @date: 2020/6/18 15:38
// */
//public class ApplyApproveByMeFragment extends BaseFragment {
//
//    public static final String TAG = ApplyApproveByMeFragment.class.getSimpleName();
//
//    @BindView(R.id.iv_user_avatar)
//    AppCompatImageView ivUserAvatar;
//    @BindView(R.id.tv_user_name)
//    AppCompatTextView tvUserName;
//    @BindView(R.id.tv_dept_name)
//    AppCompatTextView tvDeptName;
//
//    @BindView(R.id.tv_apply_name)
//    AppCompatTextView tvApplyName;
//    @BindView(R.id.tv_apply_type)
//    AppCompatTextView tvApplyType;
//    @BindView(R.id.tv_start_time)
//    AppCompatTextView tvStartTime;
//    @BindView(R.id.tv_end_time)
//    AppCompatTextView tvEndTime;
//    @BindView(R.id.tv_approve_duration)
//    AppCompatTextView tvApproveDuration;
//    @BindView(R.id.tv_remarks)
//    AppCompatTextView tvRemarks;
//
//    @BindView(R.id.et_comment)
//    AppCompatEditText etComments;
//
//    private ApplyBean mData;
//    private BusEventSubmitOK busEventSubmitOK;
//    private ApplyApproveByMeActivity activity;
//    private ApprovePresenter mPresenter;
//    private SubmitMyApprovalRequestBody body;
//
//    public static ApplyApproveByMeFragment newInstant(ApplyBean apply) {
//        Bundle bundle = new Bundle();
//        ApplyApproveByMeFragment fragment = new ApplyApproveByMeFragment();
//        bundle.putSerializable(ConstantValue.KEY_CONTENT, apply);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.fragment_apply_approve_by_me;
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        activity = (ApplyApproveByMeActivity) context;
//        mPresenter = activity.getPresenter();
//    }
//
//    @Override
//    protected void initialization() {
//        mData = (ApplyBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
//        ApproveUserBean user = mData.getUser();
//        String userName = user.getNickName();
//        tvUserName.setText(userName);
//        tvApplyName.setText(userName + "提交的" + mData.getApplyType());
//        tvStartTime.setText(mData.getSleaveTime());
//        tvEndTime.setText(mData.getEleaveTime());
//        tvRemarks.setText(mData.getDescription());
//        tvDeptName.setText(user.getDeptName());
//        //getTimeToLong
//        CalenderUtils calenderUtils = CalenderUtils.getInstance();
//
//        if (!TextUtils.isEmpty(mData.getSleaveTime()) && !TextUtils.isEmpty(mData.getEleaveTime())) {
//            long sTime = calenderUtils.getTimeToLong(mData.getSleaveTime());
//            long eTime = calenderUtils.getTimeToLong(mData.getEleaveTime());
//            tvApproveDuration.setText(calenderUtils.getHourAndMinuteDescription(eTime - sTime));
//        }
//
//        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(ivUserAvatar, user.getImgUrl());
//        //请假显示具体类型
//        if (mData.getArType() == 1) {
//            tvApplyType.setVisibility(View.VISIBLE);
//            tvApplyType.setText(mData.getVacationType());
//        } else {
//            tvApplyType.setVisibility(View.GONE);
//        }
//    }
//
//    @OnClick({R.id.tv_btn_reject, R.id.tv_btn_pass})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            //拒绝
//            case R.id.tv_btn_reject:
//                submitMyApproval(false);
//                break;
//            //同意
//            case R.id.tv_btn_pass:
//                submitMyApproval(true);
//                break;
//            default:
//        }
//    }
//
//
//    private void submitMyApproval(boolean isApprove) {
//        body = getRequestBody(isApprove);
//        if (!isApprove && TextUtils.isEmpty(body.getRemark())) {
//            showToastTip("请填写拒绝原因。");
//        } else {
//            if (!isApprove) {
//                showAlertDialogDoubleCheckRejection(body);
//            } else {
//                postApproveByMe(body);
//            }
//        }
//    }
//
//    private void postApproveByMe(SubmitMyApprovalRequestBody body) {
//        String str = new Gson().toJson(body);
//        mPresenter.postApproveByMe(RequestBody.create(MediaType.parse("application/json; " +
//                "charset=utf-8"), str));
//    }
//
//
//    public void showApplyInfo(Integer result) {
//        showToastTip("提交成功！");
//        busEventSubmitOK = new BusEventSubmitOK();
//        busEventSubmitOK.setApplyId(mData.getId());
//        busEventSubmitOK.setApprove(body.isToBeApproved());
//        activity.finish();
//    }
//
//
//    private void showAlertDialogDoubleCheckRejection(SubmitMyApprovalRequestBody body) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        AlertDialog dialog = builder.setTitle("审批拒绝")
//                .setMessage("请问你确定要拒绝本申请吗？")
//                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        postApproveByMe(body);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        dialog.show();
//    }
//
//    private SubmitMyApprovalRequestBody getRequestBody(boolean isApprove) {
//        SubmitMyApprovalRequestBody body = new SubmitMyApprovalRequestBody();
//        body.setId(mData.getSessionId());
//        body.setStatus(isApprove ? 1 : 2);
//        body.setRemark(etComments.getText().toString().trim());
//        return body;
//    }
//
//    @Override
//    public void onUnBind() {
//        if (busEventSubmitOK != null) {
//            EventBus.getDefault().post(busEventSubmitOK);
//        }
//    }
//
//}
