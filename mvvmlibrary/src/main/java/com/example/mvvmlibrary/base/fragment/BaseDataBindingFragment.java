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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmlibrary.base.activity.BaseDataBindingActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */

public abstract class BaseDataBindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {
    protected VB binding;
    protected VM viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
        if (fragmentActivity != null && fragmentActivity instanceof BaseDataBindingActivity) {
            BaseDataBindingActivity activity = (BaseDataBindingActivity) fragmentActivity;
            if (activity.isSetViewModelToFragment()) {
                viewModel = (VM) activity.getViewModel();//使用activity的viewModel
            } else {
                try {
                    Type genericSuperclass = getClass().getGenericSuperclass();
                    if (genericSuperclass instanceof ParameterizedType) {
                        Class<VM> cls;
                        if (((ParameterizedType) genericSuperclass).getActualTypeArguments().length == 3) {
                            cls = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[2];
                        } else {
                            cls = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
                        }

                        viewModel = new ViewModelProvider(getActivity()).get(cls);//使用自身的viewModel
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        if (viewModel != null && getVariableId() != 0) {
            binding.setVariable(getVariableId(), viewModel);
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

    //绑定viewModel
    protected abstract int getVariableId();

}
