package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.databinding.FragmentApproveEmployBinding;
import com.guyuan.dear.databinding.FragmentBusinessTripBinding;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--招聘
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class EmployFragment extends BaseDataBindingFragment<FragmentApproveEmployBinding> implements View.OnClickListener  {
    public static final String TAG = EmployFragment.class.getSimpleName();


    public static EmployFragment newInstance() {
        Bundle args = new Bundle();
        EmployFragment fragment = new EmployFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_employ;
    }

    protected RequestBody getRequestBody() {
        String position = binding.etEmployPosition.getText().toString();
        String needNumber = binding.etEmployPersonNumber.getText().toString();
        String readyNumber = binding.etCurrentPersonNumber.getText().toString();
        String duty = binding.etEmployDuty.getText().toString();
        String time = binding.tvEmployTime.getText().toString();
        if (TextUtils.isEmpty(position) || TextUtils.isEmpty(needNumber)
                || TextUtils.isEmpty(readyNumber) || TextUtils.isEmpty(duty)) {
            showToastTip("请填写完整信息");
            return null;
        }

//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean employBody = new ApplyBean();
        employBody.setArType(ApplyConstant.INT_EMPLOY);
        employBody.setDemandPost(position);
        employBody.setDemandNum(Integer.parseInt(needNumber));
        employBody.setExistingNum(Integer.parseInt(readyNumber));
        employBody.setDescription(duty);
        employBody.setSleaveTime(time);
//        employBody.setUsers(getApproves());
//        employBody.setCopy(getCopys());
        String str = new Gson().toJson(employBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }


//    @OnClick({R.id.rl_employ_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_employ_time:
//                setSelectedDate(tvEmployTime);
//                break;
//            default:
//
//        }
//    }
//
//
//
    @Override
    public void onClick(View v) {

    }



    @Override
    protected void initialization() {

    }
}
