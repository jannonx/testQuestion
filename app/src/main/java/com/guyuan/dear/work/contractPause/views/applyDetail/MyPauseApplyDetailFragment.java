package com.guyuan.dear.work.contractPause.views.applyDetail;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMyPauseApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 15:17
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyDetailFragment extends BaseMvvmFragment<FragmentMyPauseApplyDetailBinding,MyPauseApplyDetailViewModel> {

    private MyPauseApplyBean bean;

    public static MyPauseApplyDetailFragment getInstance(MyPauseApplyBean bean){
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN,bean);
        MyPauseApplyDetailFragment fragment = new MyPauseApplyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_my_pause_apply_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        bean = bundle.getParcelable(ConstantValue.KEY_MY_PAUSE_APPLY_BEAN);
        getViewModel().loadApplyDetailDataFromNet(bean);
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
        return R.layout.fragment_my_pause_apply_detail;
    }
}
