package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.view.View;


import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.base.BaseApproveFragment;
import com.guyuan.dear.approve.bean.BodyApprovalSubmit;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.databinding.FragmentApproceLeaveBinding;
import com.guyuan.dear.databinding.FragmentApproveBinding;
import com.guyuan.dear.dialog.SimpleRecyclerViewDialog;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.guyuan.dear.utils.ConstantValue.TEXT_PLEASE_SELECT;

/**
 * @description: 掌上办公--审批--请假
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyForLeaveFragment extends BaseApproveFragment<FragmentApproceLeaveBinding, ApproveViewModel> implements View.OnClickListener {

    public static final String TAG = ApplyForLeaveFragment.class.getSimpleName();

    private List<String> leaveTypeDataList;

    /**
     * 请假类型，默认值为年假(1)
     */
    private int leaveType = 1;

    public static ApplyForLeaveFragment newInstance() {
        Bundle bundle = new Bundle();
        ApplyForLeaveFragment fragment = new ApplyForLeaveFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approce_leave;
    }

    @Override
    protected void initialization() {
        String[] titles = getResources().getStringArray(R.array.apply_for_leave_type);
        leaveTypeDataList = Arrays.asList(titles);
        binding.rlApproveType.setOnClickListener(this);
        binding.rlStartTime.setOnClickListener(this);
        binding.rlEndTime.setOnClickListener(this);
    }


    /**
     * 请假类型选择
     */
    private void selectApproveType() {
        SimpleRecyclerViewDialog.show(getContext(), new ArrayList<>(leaveTypeDataList),
                new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
                    @Override
                    public void onItemClick(String bean, int position) {
                        binding.tvApproveType.setText(bean);
                        //类型从1开始
                        leaveType = position + 1;
                    }
                });
    }

    @Override
    protected RequestBody getRequestBody() {
        if (TEXT_PLEASE_SELECT.equals(binding.tvApproveType.getText().toString())) {
            showToastTip("请选择请假类型！");
            return null;
        }
        if (startData == null || stopDate == null) {
            showToastTip("请选择日期！");
            return null;
        }
        if (startData.getTime() >= stopDate.getTime()) {
            showToastTip("起始日期不可大于终止日期！");
            return null;
        }

        if (approveList.size() == 0) {
            showToastTip("审批人不可为空！");
            return null;
        }
        BodyApprovalSubmit requestBody = new BodyApprovalSubmit();
        requestBody.setArType(ApplyConstant.INT_LEAVE);
        ArrayList<String> copies = new ArrayList<>();
        for (StaffBean staff : copyList) {
//            copies.add(staff.getId());
        }
        requestBody.setCopy(copies);
        ArrayList<String> users = new ArrayList<>();
        for (StaffBean staff : approveList) {
//            users.add(staff.getId());
        }
        requestBody.setUsers(users);
        requestBody.setDescription(binding.etApproveDes.getText().toString().trim());
        //请假类型
        requestBody.setRbType(leaveType);
        requestBody.setRemarks(binding.etApproveDes.getText().toString().trim());
        requestBody.setTotalId(0);
        requestBody.setSleaveTime(binding.tvStartTime.getText().toString());
        requestBody.setEleaveTime(binding.tvEndTime.getText().toString());

        String str = new Gson().toJson(requestBody);
        return RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //请假类型
            case R.id.rl_approve_type:
                selectApproveType();
                break;
            //起始时间
            case R.id.rl_start_time:
                setStartDate(binding.tvStartTime, binding.tvApproveDuration);
                break;
            //终止时间
            case R.id.rl_end_time:
                setStopDate(binding.tvEndTime, binding.tvApproveDuration);
                break;
            default:
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
