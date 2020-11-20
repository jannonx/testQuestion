package com.guyuan.dear.focus.purchase.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusPurchaseDetailBinding;
import com.guyuan.dear.focus.purchase.adapter.FocusPurchaseBindingAdapter;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:43
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusPurchaseDetailFragment extends BaseDataBindingFragment<FragmentFocusPurchaseDetailBinding, FocusPurchaseViewModel> {

    public static final String TAG = "FocusPurchaseDetailFragment";

    public static FocusPurchaseDetailFragment newInstance(int purchaseID, int status) {
        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_ID, purchaseID);
        args.putInt(ConstantValue.KEY_STATUS_TYPE, status);
        FocusPurchaseDetailFragment fragment = new FocusPurchaseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return BR.focusPurchaseVM;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_purchase_detail;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            int id = getArguments().getInt(ConstantValue.KEY_ID);
            int status = getArguments().getInt(ConstantValue.KEY_STATUS_TYPE);
            int returnType;
            if (status > 2) {   //除了退换货其他类型参数为3
                returnType = 3;
            } else {
                returnType = status;
            }
            FocusPurchaseBindingAdapter.getFocusPurchaseStatus(binding.purchaseTagTv, status);
            if (viewModel != null) {
                viewModel.setStatus(status);
                viewModel.getDetail(id, returnType);
            }
        }
    }
}