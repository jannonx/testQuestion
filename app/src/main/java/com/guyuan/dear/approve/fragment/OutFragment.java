package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.databinding.FragmentApproveOutBinding;
import com.guyuan.dear.databinding.FragmentOfficeBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.CalenderUtils;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--外出
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class OutFragment extends BaseDataBindingFragment<FragmentApproveOutBinding> implements View.OnClickListener {
    public static final String TAG = OutFragment.class.getSimpleName();


    public static OutFragment newInstance() {
        Bundle args = new Bundle();
        OutFragment fragment = new OutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    protected RequestBody getRequestBody() {
        String startTime = binding.tvStartTime.getText().toString();
        String endTime = binding.tvEndTime.getText().toString();
        String reason = binding.etOutReason.getText().toString();

        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)
                || TextUtils.isEmpty(reason)) {
            showToastTip("请填写完整信息");
            return null;
        }

        long startTimeL = CalenderUtils.getInstance().getTimeToLong(startTime);
        long endTimeL = CalenderUtils.getInstance().getTimeToLong(endTime);

        if (endTimeL < startTimeL) {
            showToastTip("开始时间不能小于结束时间");
            return null;
        }

//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }

        ApplyBean outBody = new ApplyBean();
        outBody.setArType(ApplyConstant.INT_OUTING);
//        outBody.setCopy(getCopys());
//        outBody.setUsers(getApproves());
        outBody.setDescription(reason);
        outBody.setSleaveTime(startTime);
        outBody.setEleaveTime(endTime);

        String str = new Gson().toJson(outBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);


    }


//    protected void updateOnLeavePeriod(TextView tvDuration) {
//        long duration = stopDate.getTime() - startData.getTime();
//        String str = CalenderUtils.getInstance().getHoursByDate(duration);
//        tvDuration.setText(str);
//    }


//    @OnClick({R.id.rl_start_time, R.id.rl_end_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_start_time:
//                setStartDate(tvStartTime, tvDuration);
//                break;
//
//            case R.id.rl_end_time:
//                setStopDate(tvEndTime, tvDuration);
//                break;
//            default:
//        }
//    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_out;
    }

    @Override
    protected void initialization() {

    }
}
