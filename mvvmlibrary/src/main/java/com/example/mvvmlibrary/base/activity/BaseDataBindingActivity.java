package com.example.mvvmlibrary.base.activity;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.data.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;


/**
 * created by tl
 * created at 2020/8/12
 */
public abstract class BaseDataBindingActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {
    protected V binding;
    protected VM viewModel;

    @Override
    protected void init(Bundle savedInstanceState) {
        initDataBinding();
        initData(savedInstanceState);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutID());
        try {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types.length > 1) {
                    modelClass = (Class<VM>) ((ParameterizedType) type).getActualTypeArguments()[1];
                } else {//如果没有指定viewmodel泛型,默认使用BaseViewModel
                    modelClass = BaseViewModel.class;
                }
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) getDefaultViewModelProviderFactory().create(modelClass);
            initLiveData();
        } catch (Exception e) {
            showToastTip(e.getMessage());
        }

        if (getVariableId() != 0) {
            binding.setVariable(getVariableId(), viewModel);
            binding.setLifecycleOwner(this);
        }
    }

    protected void initLiveData() {
        if (viewModel != null) {
            viewModel.getShowLoading().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    showLoadingWithStatus(fragmentManager, s);
                }
            });

            viewModel.getHideLoading().observe(this, new Observer<Void>() {
                @Override
                public void onChanged(Void aVoid) {
                    hideLoading();
                }
            });

            viewModel.getTip().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    showToastTip(s);
                }
            });

            viewModel.getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
                @Override
                public void onChanged(Map<String, Object> params) {
                    Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                    Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                    startActivity(clz, bundle);
                }
            });

            viewModel.getFinishEvent().observe(this, new Observer<Void>() {
                @Override
                public void onChanged(Void aVoid) {
                    finish();
                }
            });
        }
    }


    protected abstract void initData(Bundle savedInstanceState);

    protected int getVariableId() {
        return 0;
    }

    ;

    public VM getViewModel() {
        return viewModel;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
        }
        if (viewModel != null) {
            viewModel.onUnSubscribe();
        }
    }
}
