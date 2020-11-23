package com.guyuan.dear.work.contractPause.views.applyDetail;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMyApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:17
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyDetailFragment extends BaseMvvmFragment<FragmentMyApplyDetailBinding, MyApplyDetailViewModel> {

    private int examineId;

    public static MyApplyDetailFragment getInstance(int examineId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_EXAMINE_ID, examineId);
        MyApplyDetailFragment fragment = new MyApplyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_my_apply_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        examineId = bundle.getInt(ConstantValue.KEY_EXAMINE_ID);
        getViewModel().getMyApplyDetailFromNet(examineId);
        getViewDataBinding().fragmentMyPauseApplyDetailNestedScrollerView.fullScroll(View.FOCUS_UP);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_my_apply_detail;
    }
}
