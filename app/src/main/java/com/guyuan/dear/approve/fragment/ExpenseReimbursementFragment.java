package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.databinding.FragmentApproveCommonBinding;
import com.guyuan.dear.databinding.FragmentApproveExpenseBinding;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--报销
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ExpenseReimbursementFragment extends BaseDataBindingFragment<FragmentApproveExpenseBinding> implements View.OnClickListener {
    public static final String TAG = ExpenseReimbursementFragment.class.getSimpleName();


//    @BindView(R.id.et_expense_price)
//    EditText etExpensePrice;
//    @BindView(R.id.et_expense_type)
//    EditText etExpenseType;
//    @BindView(R.id.et_expense_remark)
//    EditText etExpenseRemark;

    public static ExpenseReimbursementFragment newInstance() {
        Bundle args = new Bundle();
        ExpenseReimbursementFragment fragment = new ExpenseReimbursementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_expense;
    }

    protected RequestBody getRequestBody() {
        String price = binding.etExpensePrice.getText().toString();
        String expenseType = binding.etExpenseType.getText().toString();
        String remark = binding.etExpenseRemark.getText().toString();

        if (TextUtils.isEmpty(price) || TextUtils.isEmpty(expenseType)) {
            showToastTip("请输入完整信息");
            return null;
        }

//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean expenseBody = new ApplyBean();
        expenseBody.setArType(ApplyConstant.INT_EXPENSE_REIMBURSEMENT);
        expenseBody.setMoney(Double.parseDouble(price));
        expenseBody.setAttribute1(expenseType);
        if (!TextUtils.isEmpty(remark)) {
            expenseBody.setDescription(remark);
        } else {
            expenseBody.setDescription("");
        }
//        expenseBody.setUsers(getApproves());
//        expenseBody.setCopy(getCopys());

        String str = new Gson().toJson(expenseBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void initialization() {

    }
}
