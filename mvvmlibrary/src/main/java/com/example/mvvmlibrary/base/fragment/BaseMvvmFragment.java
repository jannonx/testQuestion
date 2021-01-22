package com.example.mvvmlibrary.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmlibrary.base.data.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public abstract class BaseMvvmFragment<VDB extends ViewDataBinding, VM extends BaseViewModel> extends BaseViewModelFragment<VDB> {
    private VM viewModel;
    private List<Disposable> disposableList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Class<VM> cls = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
            viewModel = new ViewModelProvider(getActivity()).get(cls);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getViewDataBinding().setVariable(getViewModelBrId(), viewModel);
        return view;
    }

    /**
     * 全称为：get view model binding resource id。如果在xml中设置了view model作为数据提供者，这里要把view model的
     * 绑定资源id(即Binding Resource Id，由android studio自动生成)返回来。
     *
     * @return
     */
    protected abstract int getViewModelBrId();


    protected VM getViewModel() {
        return viewModel;
    }

    @Override
    protected void initialization() {
        initData();
        initViews();
        initListeners();
        //根据数据源的改变，显示或者消除“装载中”的图标
        getViewModel().isShowLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading(getParentFragmentManager());
                } else {
                    hideLoading();
                }
            }
        });

    }

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void initListeners();

    public void addDisposable(@Nullable Disposable disposable) {
        if (disposable != null) {
            disposableList.add(disposable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Disposable disposable : disposableList) {
            if (disposable.isDisposed()) {
                continue;
            }
            disposable.dispose();
        }
        if (getViewModel() != null) {
            getViewModel().onDestroy();
        }
    }
}
