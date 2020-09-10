//package com.guyuan.dear.approve.fragment;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.appcompat.widget.AppCompatEditText;
//import androidx.appcompat.widget.AppCompatTextView;
//
//import com.google.gson.Gson;
//import com.guyuan.dear.R;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//
//
//
///**
// * @description: 掌上办公--审批--请假
// * @author:Jannonx
// * @date: 2020/6/18 15:38
// */
//public class ApplyForLeaveFragment extends BaseApproveFragment {
//
//    public static final String TAG = ApplyDetailPageFragment.class.getSimpleName();
//
////    @BindView(R.id.tv_approve_type)
////    TextView tvApproveType;
////    @BindView(R.id.tv_start_time)
////    AppCompatTextView tvStartTime;
////    @BindView(R.id.tv_end_time)
////    AppCompatTextView tvEndTime;
////    @BindView(R.id.tv_approve_duration)
////    AppCompatTextView tvDuration;
////    @BindView(R.id.et_approve_des)
////    AppCompatEditText etDescription;
//
//
//    private List<String> leaveTypeDataList;
//
//    /**
//     * 请假类型，默认值为年假(1)
//     */
//    private int leaveType = 1;
//
//    public static ApplyForLeaveFragment newInstant() {
//        Bundle bundle = new Bundle();
//        ApplyForLeaveFragment fragment = new ApplyForLeaveFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.fragment_approce_leave;
//    }
//
//    @Override
//    protected void initialization() {
//        super.initialization();
//        String[] titles = getResources().getStringArray(R.array.apply_for_leave_type);
//        leaveTypeDataList = Arrays.asList(titles);
//    }
//
//    @OnClick({R.id.rl_approve_type, R.id.rl_start_time, R.id.rl_end_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            //请假类型
//            case R.id.rl_approve_type:
//                selectApproveType();
//                break;
//            //起始时间
//            case R.id.rl_start_time:
//                setStartDate(tvStartTime, tvDuration);
//                break;
//            //终止时间
//            case R.id.rl_end_time:
//                setStopDate(tvEndTime, tvDuration);
//                break;
//            default:
//        }
//    }
//
//
//    /**
//     * 请假类型选择
//     */
//    private void selectApproveType() {
//        SimpleRecyclerViewDialog.show(getContext(), new ArrayList<>(leaveTypeDataList),
//                new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
//                    @Override
//                    public void onItemClick(String bean, int position) {
//                        tvApproveType.setText(bean);
//                        //类型从1开始
//                        leaveType = position + 1;
//                    }
//                });
//    }
//
//    @Override
//    protected RequestBody getRequestBody() {
//        if (TEXT_PLEASE_SELECT.equals(tvApproveType.getText().toString())) {
//            showToastTip("请选择请假类型！");
//            return null;
//        }
//        if (startData == null || stopDate == null) {
//            showToastTip("请选择日期！");
//            return null;
//        }
//        if (startData.getTime() >= stopDate.getTime()) {
//            showToastTip("起始日期不可大于终止日期！");
//            return null;
//        }
//
//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
//        ApplyForLeaveRequestBody requestBody = new ApplyForLeaveRequestBody();
//        requestBody.setArType(ApplyConstant.INT_LEAVE);
//        ArrayList<Long> copies = new ArrayList<>();
//        for (StaffBean staff : copyList) {
//            copies.add(staff.getId());
//        }
//        requestBody.setCopy(copies);
//        ArrayList<Long> users = new ArrayList<>();
//        for (StaffBean staff : approveList) {
//            users.add(staff.getId());
//        }
//        requestBody.setUsers(users);
//        requestBody.setDescription(etDescription.getText().toString().trim());
//        //请假类型
//        requestBody.setRbType(leaveType);
//        requestBody.setRemarks(etDescription.getText().toString().trim());
//        requestBody.setTotalId(0);
//        requestBody.setSleaveTime(tvStartTime.getText().toString());
//        requestBody.setEleaveTime(tvEndTime.getText().toString());
//
//        String str = new Gson().toJson(requestBody);
//        return RequestBody.create(MediaType.parse("application/json; " +
//                "charset=utf-8"), str);
//    }
//
//    @Override
//    public void onUnBind() {
//
//    }
//
//}
