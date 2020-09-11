package com.guyuan.dear.approve.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.constant.ApplyConstant;
import com.guyuan.dear.databinding.FragmentApproveSealBinding;

import okhttp3.RequestBody;

/**
 * @description: 掌上办公--审批--用印
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class SealFragment extends BaseDataBindingFragment<FragmentApproveSealBinding> implements View.OnClickListener  {

    public static final String TAG = SealFragment.class.getSimpleName();

//    @BindView(R.id.et_seal_department)
//    EditText etSealDepartment;
//    @BindView(R.id.et_seal_person)
//    EditText etSealPerson;
//    @BindView(R.id.tv_seal_time)
//    TextView tvSealTime;
//    @BindView(R.id.et_seal_file)
//    EditText etSealFile;
//    @BindView(R.id.et_seal_file_number)
//    EditText etSealFileNumber;
//    @BindView(R.id.et_seal_file_type)
//    EditText etSealFileType;
//    @BindView(R.id.et_seal_type)
//    EditText etSealType;
//    @BindView(R.id.et_seal_remark)
//    EditText etSealRemark;


    public static SealFragment newInstance() {
        Bundle args = new Bundle();
        SealFragment fragment = new SealFragment();
        fragment.setArguments(args);
        return fragment;
    }

    protected RequestBody getRequestBody() {
        String department = binding.etSealDepartment.getText().toString();
        String person = binding.etSealPerson.getText().toString();
        String time = binding.tvSealTime.getText().toString();
        String fileName = binding.etSealFile.getText().toString();
        String fileNumber = binding.etSealFileNumber.getText().toString();
        String sealFileType = binding.etSealFileType.getText().toString();
        String sealType = binding.etSealType.getText().toString();
        String remark = binding.etSealRemark.getText().toString();

        if (TextUtils.isEmpty(department) || TextUtils.isEmpty(person) || TextUtils.isEmpty(time) ||
                TextUtils.isEmpty(fileName) || TextUtils.isEmpty(fileNumber) || TextUtils.isEmpty(sealFileType) ||
                TextUtils.isEmpty(sealType)) {
            showToastTip("请输入完整数据");
            return null;
        }

//        if (approveList.size() == 0) {
//            showToastTip("审批人不可为空！");
//            return null;
//        }
        ApplyBean sealBody = new ApplyBean();
        sealBody.setArType(ApplyConstant.INT_SEAL);
        sealBody.setDepartment(department);
        sealBody.setSleaveTime(time);
        sealBody.setFile(fileName);
        sealBody.setCharge(person);
        if (!TextUtils.isEmpty(remark)) {
            sealBody.setAttribute5(remark);
        } else {
            sealBody.setAttribute5("");
        }

//        sealBody.setUsers(getApproves());
//        sealBody.setCopy(getCopys());
        String str = new Gson().toJson(sealBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);

    }

//    @OnClick({R.id.rl_seal_time})
//    public void viewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_seal_time:
//                setSelectedDate(tvSealTime);
//                break;
//            default:
//        }
//    }



    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve_seal;
    }

    @Override
    protected void initialization() {

    }
}
