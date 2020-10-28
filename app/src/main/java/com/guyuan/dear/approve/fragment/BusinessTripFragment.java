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
import com.guyuan.dear.databinding.FragmentBusinessTripBinding;
import com.guyuan.dear.utils.CalenderUtils;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--出差
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class BusinessTripFragment extends BaseDataBindingFragment<FragmentBusinessTripBinding, ApproveViewModel> implements View.OnClickListener {

    public static final String TAG = BusinessTripFragment.class.getSimpleName();


    public static BusinessTripFragment newInstance() {
        Bundle args = new Bundle();
        BusinessTripFragment fragment = new BusinessTripFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_business_trip;
    }

    @Override
    protected void initialization() {
//        setTflPartner(tflPartner);
    }


//    @OnClick({R.id.iv_partner, R.id.rl_start_time, R.id.rl_end_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.iv_partner:
//                ArrayList<StaffBean> disableListP = new ArrayList<>();
//                disableListP.addAll(approveList);
//                disableListP.addAll(copyList);
//                SelectStaffsActivity.startForResult(REQUEST_PARTNER, this,
//                        "请选择同行人", partnerList, disableListP, true, ConstantValue.CONST_MAX_STAFF_COUNT);
//                break;
//
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

    protected RequestBody getRequestBody() {
        String reason = binding.etBusinessReason.getText().toString();
        String remark = binding.etRemark.getText().toString();
        String startCity = binding.etStartCity.getText().toString();
        String endCity = binding.etEndCity.getText().toString();
        String startTime = binding.tvStartTime.getText().toString();
        String endTime = binding.tvEndTime.getText().toString();

        if (TextUtils.isEmpty(reason) || TextUtils.isEmpty(startCity) || TextUtils.isEmpty(endCity)
                || TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            showToastTip("请填写完整出差信息");
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
        ApplyBean businessApproveBody = new ApplyBean();
        businessApproveBody.setArType(ApplyConstant.INT_BUSINESS);
        businessApproveBody.setDescription(reason);
        businessApproveBody.setAttribute5(remark);
        businessApproveBody.setSetOut(startCity);
        businessApproveBody.setObjective(endCity);
        businessApproveBody.setSleaveTime(startTime);
        businessApproveBody.setEleaveTime(endTime);
        businessApproveBody.setTime(1);
        businessApproveBody.setColleague("");
//        businessApproveBody.setUsers(getApproves());
//        businessApproveBody.setCopy(getCopys());

        String str = new Gson().toJson(businessApproveBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
