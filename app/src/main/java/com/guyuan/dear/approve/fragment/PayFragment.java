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
import com.guyuan.dear.databinding.FragmentApprovePayBinding;
import com.guyuan.dear.dialog.ApprovePayTypeDialogFragment;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--付款
 * @author:Jannonx
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class PayFragment extends BaseDataBindingFragment<FragmentApprovePayBinding> implements View.OnClickListener {
    public static final String TAG = PayFragment.class.getSimpleName();

    private ApprovePayTypeDialogFragment approvePayTypeDialogFragment;
    private int currentType;

    public static PayFragment newInstance() {
        Bundle args = new Bundle();
        PayFragment fragment = new PayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_approve_pay;
    }

    protected RequestBody getRequestBody() {
        String reason = binding.etPayReason.getText().toString();
        String price = binding.etPayPrice.getText().toString();
        String payType = binding.tvPayType.getText().toString();
        String payTime = binding.tvPayTime.getText().toString();
        String person = binding.etPayPerson.getText().toString();
        String bank = binding.etPayBank.getText().toString();
        String account = binding.etPayBankAccount.getText().toString();

        if (TextUtils.isEmpty(reason) || TextUtils.isEmpty(price) || TextUtils.isEmpty(payType) ||
                TextUtils.isEmpty(payTime) || TextUtils.isEmpty(person) || TextUtils.isEmpty(bank) ||
                TextUtils.isEmpty(account)) {
            showToastTip("请填写完整信息");
            return null;
        }
//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }

        ApplyBean payBody = new ApplyBean();
        payBody.setArType(ApplyConstant.INT_PAY);
        payBody.setDescription(reason);
        payBody.setMoney(Double.parseDouble(price));
        payBody.setRbType(currentType);
        payBody.setSleaveTime(payTime);
        payBody.setPayObj(person);
        payBody.setOpening(bank);
        payBody.setAccount(account);
//            payBody.setUsers(getApproves());
//            payBody.setCopy(getCopys());
        String str = new Gson().toJson(payBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);


    }

//    @OnClick({R.id.rl_pay_type, R.id.rl_pay_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_pay_type:
//                selectPayType();
//                break;
//
//            case R.id.rl_pay_time:
//                setSelectedDate(tvPayTime);
//                break;
//            default:
//        }
//    }

    private void selectPayType() {
        if (approvePayTypeDialogFragment == null) {
            approvePayTypeDialogFragment = ApprovePayTypeDialogFragment.newInstance();
            approvePayTypeDialogFragment.setListener(new ApprovePayTypeDialogFragment.ApprovePayTypeDialogListener() {
                @Override
                public void getType(int type) {
                    currentType = type;
                    switch (type) {
                        case 1:
                            binding.tvPayType.setText("支票");
                            break;
                        case 2:
                            binding.tvPayType.setText("贷记");
                            break;
                        case 3:
                            binding.tvPayType.setText("电汇");
                            break;
                        case 4:
                            binding.tvPayType.setText("汇票");
                            break;
                        case 5:
                            binding.tvPayType.setText("现金");
                            break;
                        case 6:
                            binding.tvPayType.setText("银行卡");
                            break;
                        case 7:
                            binding.tvPayType.setText("其他");
                            break;
                        default:
                    }
                }
            });

            if (!approvePayTypeDialogFragment.isAdded()) {
                approvePayTypeDialogFragment.show(childFragmentManager, ApprovePayTypeDialogFragment.TAG);
            }
        } else {
            if (!approvePayTypeDialogFragment.isAdded()) {
                approvePayTypeDialogFragment.show(childFragmentManager, ApprovePayTypeDialogFragment.TAG);
            }
        }

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void initialization() {

    }
}
