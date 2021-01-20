package com.guyuan.dear.office.approval.ui.approvalDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentApprovalDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.tencent.bugly.proguard.A;
import com.umeng.message.common.Const;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/20 12:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ApprovalDetailFragment extends BaseMvvmFragment<FragmentApprovalDetailBinding,ApprovalDetailViewModel> {

    /**
     *
     * @param applyType {@link com.guyuan.dear.office.approval.data.bean.ApprovalTypeBean#CONTRACT_EXAMINE_STATUS_RESTART_TYPE}
     * {@link com.guyuan.dear.office.approval.data.bean.ApprovalTypeBean#CONTRACT_EXAMINE_STATUS_STOP_TYPE}
     * @param examineId 也就是biz id
     * @return
     */
    public static ApprovalDetailFragment getInstance(int applyType,int examineId){
        ApprovalDetailFragment fragment = new ApprovalDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_EXAMINE_ID,examineId);
        bundle.putInt(ConstantValue.KEY_APPLY_TYPE,applyType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_approval_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int examineId = arguments.getInt(ConstantValue.KEY_EXAMINE_ID);
        int applyType = arguments.getInt(ConstantValue.KEY_APPLY_TYPE);
        getViewModel().applyType.postValue(applyType);
        getViewModel().getMyApplyDetailFromNet(examineId);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approval_detail;
    }
}
