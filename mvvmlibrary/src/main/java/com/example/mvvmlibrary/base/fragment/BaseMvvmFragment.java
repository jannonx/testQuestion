package com.example.mvvmlibrary.base.fragment;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 10:31
 * @company: 固远（深圳）信息技术有限公司
 **/
public abstract class BaseMvvmFragment<VDB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingFragment<VDB> {
    private VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Class<VM> cls = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
            viewModel = getDefaultViewModelProviderFactory().create(cls);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        SparseArray<Object> array = new SparseArray<>();
        int viewModeDbId = getViewModelBrId();
        if (viewModeDbId > 0) {
            array.put(viewModeDbId, viewModel);
        }
        int size = array.size();
        for (int i = 0; i < size; i++) {
            int key = array.keyAt(i);
            Object value = array.valueAt(i);
            getViewDataBinding().setVariable(key, value);
        }

        return view;
    }

    /**
     * 全称为：get view model binding resource id。如果在xml中设置了view model作为数据提供者，这里要把view model的
     * 绑定资源id(即Binding Resource Id，由android studio自动生成的唯一ID)返回来。
     * @return
     */
    protected abstract int getViewModelBrId();


    protected VM getViewModel() {
        return viewModel;
    }
}
