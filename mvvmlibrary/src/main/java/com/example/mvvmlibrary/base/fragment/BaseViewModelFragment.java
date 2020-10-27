package com.example.mvvmlibrary.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 15:37
 * @company: 固远（深圳）信息技术有限公司
 **/
public abstract class BaseViewModelFragment<VB extends ViewDataBinding> extends BaseFragment {
    protected VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        rootView = binding.getRoot();
        binding.setLifecycleOwner(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

    protected VB getViewDataBinding() {
        return binding;
    }
}
