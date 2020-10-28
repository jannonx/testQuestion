package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.databinding.FragmentLeaveOfficeBinding;
import com.guyuan.dear.utils.CalenderUtils;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--离职
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class LeaveOfficeFragment extends BaseDataBindingFragment<FragmentLeaveOfficeBinding, ApproveViewModel> implements View.OnClickListener {
    public static final String TAG = LeaveOfficeFragment.class.getSimpleName();



    public static LeaveOfficeFragment newInstance() {
        Bundle args = new Bundle();
        LeaveOfficeFragment fragment = new LeaveOfficeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_leave_office;
    }

    protected RequestBody getRequestBody() {
        String startTime = binding.tvStartTime.getText().toString();
        String endTime = binding.tvEndTime.getText().toString();
        String reason = binding.etLeaveReason.getText().toString();
        String remark = binding.etLeaveRemark.getText().toString();
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)
                || TextUtils.isEmpty(reason)) {
            showToastTip("请输入完整信息");
            return null;
        }

        long startTimeL = CalenderUtils.getInstance().getTimeToLong(startTime);
        long endTimeL = CalenderUtils.getInstance().getTimeToLong(endTime);

        if (endTimeL < startTimeL) {
            showToastTip("最后工作日期不能小于入职日期");
            return null;
        }
//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean leaveOfiiceBody = new ApplyBean();
        leaveOfiiceBody.setArType(ApplyConstant.INT_LEAVE_OFFICE);
        leaveOfiiceBody.setSleaveTime(startTime);
        leaveOfiiceBody.setEleaveTime(endTime);
        leaveOfiiceBody.setDescription(reason);
        if (!TextUtils.isEmpty(remark)) {
            leaveOfiiceBody.setAttribute5(remark);
        } else {
            leaveOfiiceBody.setAttribute5("");
        }
//        leaveOfiiceBody.setUsers(getApproves());
//        leaveOfiiceBody.setCopy(getCopys());

        String str = new Gson().toJson(leaveOfiiceBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }


//    @OnClick({R.id.rl_start_time, R.id.rl_end_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_start_time:
//                setStartDate(tvStartTime, null);
//                break;
//
//            case R.id.rl_end_time:
//                setStopDate(tvEndTime, null);
//                break;
//            default:
//        }
//    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
