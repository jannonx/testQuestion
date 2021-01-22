package com.example.mvvmlibrary.base.activity;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public abstract class BaseNoToolbarActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingActivity<V, VM> {

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
    }

    protected abstract void initFragment(Bundle savedInstanceState);
}
