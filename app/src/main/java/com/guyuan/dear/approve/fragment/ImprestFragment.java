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
import com.guyuan.dear.databinding.FragmentApproveImprestBinding;
import com.guyuan.dear.databinding.FragmentApproveOutBinding;
import com.guyuan.dear.utils.CalenderUtils;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--备用金
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ImprestFragment extends BaseDataBindingFragment<FragmentApproveImprestBinding> implements View.OnClickListener {
    public static final String TAG = ImprestFragment.class.getSimpleName();
//    @BindView(R.id.et_person)
//    EditText etPerson;
//    @BindView(R.id.et_department)
//    EditText etDepartment;
//    @BindView(R.id.et_reason)
//    EditText etReason;
//    @BindView(R.id.et_price)
//    EditText etPrice;
//
//    @BindView(R.id.tv_use_time)
//    TextView tvUseTime;
//    @BindView(R.id.tv_back_time)
//    TextView tvBackTime;
//
//    @BindView(R.id.et_out_person)
//    EditText etOutPerson;
//    @BindView(R.id.et_remark)
//    EditText etRemark;


    public static ImprestFragment newInstance() {

        Bundle args = new Bundle();

        ImprestFragment fragment = new ImprestFragment();
        fragment.setArguments(args);
        return fragment;
    }


    protected RequestBody getRequestBody() {
        String applyPerson = binding.etPerson.getText().toString();
        String department = binding.etDepartment.getText().toString();
        String reason = binding.etReason.getText().toString();
        String price = binding.etPrice.getText().toString();
        String useTime = binding.tvUseTime.getText().toString();
        String backTime = binding.tvBackTime.getText().toString();
        String outPerson = binding.etOutPerson.getText().toString();
        String remark = binding.etRemark.getText().toString();
        if (TextUtils.isEmpty(applyPerson) || TextUtils.isEmpty(department)
                || TextUtils.isEmpty(reason) || TextUtils.isEmpty(price) || TextUtils.isEmpty(outPerson)) {
            showToastTip("请输入完整信息");
            return null;
        }

        long startTimeL = CalenderUtils.getInstance().getTimeToLong(useTime);
        long endTimeL = CalenderUtils.getInstance().getTimeToLong(backTime);

        if (endTimeL < startTimeL) {
            showToastTip("使用日期不能小于归还日期");
            return null;
        }


//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean body = new ApplyBean();
        body.setArType(ApplyConstant.INT_PRETTY_CASH);
        body.setAttribute2(applyPerson);
        body.setDepartment(department);
        body.setMoney(Double.parseDouble(price));
        body.setCashier(outPerson);
        if (!TextUtils.isEmpty(remark)) {
            body.setAttribute5(remark);
        } else {
            body.setAttribute5("");
        }

        body.setSleaveTime(useTime);
        body.setEleaveTime(backTime);
//        body.setUsers(getApproves());
//        body.setCopy(getCopys());
        String str = new Gson().toJson(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_imprest;
    }

    @Override
    protected void initialization() {

    }


//    @OnClick({R.id.rl_use_time, R.id.rl_back_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_use_time:
//                setStartDate(tvUseTime, null);
//                break;
//
//            case R.id.rl_back_time:
//                setStopDate(tvBackTime, null);
//                break;
//
//            default:
//        }
}



