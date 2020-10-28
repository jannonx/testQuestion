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
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.databinding.FragmentApproveCommonBinding;
import com.guyuan.dear.databinding.FragmentBusinessTripBinding;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--通用
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class CommonApproveFragment extends BaseDataBindingFragment<FragmentApproveCommonBinding, ApproveViewModel> implements View.OnClickListener {
    public static final String TAG = CommonApproveFragment.class.getSimpleName();


    public static CommonApproveFragment newInstance() {
        Bundle args = new Bundle();
        CommonApproveFragment fragment = new CommonApproveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_common;
    }

    protected RequestBody getRequestBody() {
        String content = binding.etApproveContent.getText().toString();
        String detail = binding.etApproveDetail.getText().toString();

        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(detail)) {
            showToastTip("请输入完整信息");
            return null;
        }

//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean commonBody = new ApplyBean();
        commonBody.setArType(ApplyConstant.INT_COMMON);
        commonBody.setAttribute5(content);
        commonBody.setDescription(detail);
//        commonBody.setUsers(getApproves());
//        commonBody.setCopy(getCopys());

        String str = new Gson().toJson(commonBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }


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
