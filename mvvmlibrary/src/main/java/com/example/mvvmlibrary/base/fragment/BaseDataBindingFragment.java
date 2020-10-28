package com.example.mvvmlibrary.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.activity.BaseDataBindingActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;


/**
 * created by tl
 * created at 2020/8/25
 */

public abstract class BaseDataBindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {
    protected VB binding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        if (getVariableId() != 0) {
            BaseDataBindingActivity activity = (BaseDataBindingActivity) getActivity();
            if (activity != null) {
                viewModel = (VM) activity.getViewModel();
                binding.setVariable(getVariableId(), viewModel);
            }
        }
        rootView = binding.getRoot();
        binding.setLifecycleOwner(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

    protected abstract int getVariableId();

    protected VB getViewDataBinding() {
        return binding;
    }
}
