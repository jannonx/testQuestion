package com.example.mvvmlibrary.base.activity;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
public abstract class BaseNoToolbarActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingActivity<V, VM> {

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
    }

    protected abstract void initFragment(Bundle savedInstanceState);
}
