package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.databinding.FragmentApproveOverTimeBinding;
import com.guyuan.dear.databinding.FragmentApprovePayBinding;
import com.guyuan.dear.utils.CalenderUtils;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--加班
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class OverTimeFragment extends BaseDataBindingFragment<FragmentApproveOverTimeBinding> implements View.OnClickListener {
    public static final String TAG = OverTimeFragment.class.getSimpleName();


//    @BindView(R.id.tv_start_time)
//    AppCompatTextView tvStartTime;
//    @BindView(R.id.tv_end_time)
//    AppCompatTextView tvEndTime;
//    @BindView(R.id.tv_duration)
//    TextView tvDuration;
//    @BindView(R.id.et_over_time_reason)
//    EditText etOverTimeReason;


    public static OverTimeFragment newInstance() {
        Bundle args = new Bundle();
        OverTimeFragment fragment = new OverTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_over_time;
    }

    protected RequestBody getRequestBody() {
        String startTime = binding.tvStartTime.getText().toString();
        String endTime = binding.tvEndTime.getText().toString();
        String reason = binding.etOverTimeReason.getText().toString();

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
        ApplyBean overTimeBody = new ApplyBean();
        overTimeBody.setArType(ApplyConstant.INT_OVERTIME);
//        overTimeBody.setCopy(getCopys());
//        overTimeBody.setUsers(getApproves());
        overTimeBody.setDescription(reason);
        overTimeBody.setSleaveTime(startTime);
        overTimeBody.setEleaveTime(endTime);

        String str = new Gson().toJson(overTimeBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }


//    protected void updateOnLeavePeriod(TextView tvDuration) {
//        long duration = stopDate.getTime() - startData.getTime();
//        String str = CalenderUtils.getInstance().getHoursByDate(duration);
//        tvDuration.setText(str);
//    }


//    @OnClick({R.id.rl_start_time, R.id.rl_end_time})
//    public void ViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_start_time:
//                setStartDate(tvStartTime, tvDuration);
//                break;
//            case R.id.rl_end_time:
//                setStopDate(tvEndTime, tvDuration);
//                break;
//        }
//    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void initialization() {

    }
}
