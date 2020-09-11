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
import com.guyuan.dear.databinding.FragmentPurchaseBinding;
import com.guyuan.dear.dialog.PurchaseTypeDialogFragment;


import okhttp3.RequestBody;

import static com.guyuan.dear.utils.ConstantValue.TEXT_PLEASE_SELECT;


/**
 * @description: 掌上办公--审批--采购
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class PurchaseFragment extends BaseDataBindingFragment<FragmentPurchaseBinding> implements View.OnClickListener {
    public static final String TAG = PurchaseFragment.class.getSimpleName();

//    @BindView(R.id.et_purchase_reason)
//    EditText etPurchaseReason;
//    @BindView(R.id.tv_finish_time)
//    TextView tvFinishTime;
//    @BindView(R.id.et_purchase_name)
//    EditText etPurchaseName;
//    @BindView(R.id.et_purchase_type)
//    EditText etPurchaseType;
//    @BindView(R.id.et_purchase_number)
//    EditText etPurchaseNumber;
//    @BindView(R.id.et_purchase_unit)
//    EditText etPurchaseUnit;
//    @BindView(R.id.et_purchase_price)
//    EditText etPurchasePrice;
//    @BindView(R.id.et_purchase_remark)
//    EditText etPurchaseRemark;
//    @BindView(R.id.tv_purchase_type)
//    TextView tvPurchaseType;


    private PurchaseTypeDialogFragment purchaseTypeDialogFragment;
    private int purchaseType;

    public static PurchaseFragment newInstance() {
        Bundle args = new Bundle();
        PurchaseFragment fragment = new PurchaseFragment();
        fragment.setArguments(args);
        return fragment;
    }


//    @OnClick({R.id.rl_finish_time, R.id.rl_purchase_type})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_finish_time:
//                setSelectedDate(tvFinishTime);
//                break;
//            case R.id.rl_purchase_type:
//                selectPurchaseType();
//                break;
//            default:
//        }
//
//    }

    private void selectPurchaseType() {
        if (purchaseTypeDialogFragment == null) {
            purchaseTypeDialogFragment = PurchaseTypeDialogFragment.newInstance();
            purchaseTypeDialogFragment.setListener(new PurchaseTypeDialogFragment.PurchaseTypeDialogListener() {
                @Override
                public void getType(int type) {
                    purchaseType = type;
                    binding.tvPurchaseType.setText(purchaseType == 1 ? "办公用品" :
                            purchaseType == 2 ? "生产原料" : "其他");
                }
            });
            if (!purchaseTypeDialogFragment.isAdded()) {
                purchaseTypeDialogFragment.show(childFragmentManager, PurchaseTypeDialogFragment.TAG);
            }
        } else {
            if (!purchaseTypeDialogFragment.isAdded()) {
                purchaseTypeDialogFragment.show(childFragmentManager, PurchaseTypeDialogFragment.TAG);
            }
        }
    }

    protected RequestBody getRequestBody() {
        String reason = binding.etPurchaseReason.getText().toString();
        String time = binding.tvFinishTime.getText().toString();
        String name = binding.etPurchaseName.getText().toString();
        String standard = binding.etPurchaseType.getText().toString();
        String number = binding.etPurchaseNumber.getText().toString();
        String unit = binding.etPurchaseUnit.getText().toString();
        String price = binding.etPurchasePrice.getText().toString();
        String remark = binding.etPurchaseRemark.getText().toString();

        if (TEXT_PLEASE_SELECT.equals(time)) {
            showToastTip("请选择交付日期");
            return null;
        }
        if (TextUtils.isEmpty(reason) || TextUtils.isEmpty(time) || TextUtils.isEmpty(name)
                || TextUtils.isEmpty(standard) || TextUtils.isEmpty(number) ||
                TextUtils.isEmpty(unit) || TextUtils.isEmpty(price)) {
            showToastTip("请填写完整信息");
            return null;
        }
//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }

        ApplyBean purchaseBody = new ApplyBean();
        purchaseBody.setArType(ApplyConstant.INT_PROCUREMENT);
        purchaseBody.setContent(reason);
        purchaseBody.setSleaveTime(time);
        purchaseBody.setAttribute1(name);
        purchaseBody.setAttribute2(standard);
        purchaseBody.setAttribute3(number);
        purchaseBody.setAttribute4(unit);
        purchaseBody.setAttribute5(price);

        if (!TextUtils.isEmpty(remark)) {
            purchaseBody.setDescription(remark);
        } else {
            purchaseBody.setDescription("");
        }

//        purchaseBody.setUsers(getApproves());
//        purchaseBody.setCopy(getCopys());

        String str = new Gson().toJson(purchaseBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_purchase;
    }

    @Override
    protected void initialization() {

    }
}
