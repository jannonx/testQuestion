package com.guyuan.dear.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :DataBinding recycleView adapter基类
 * @since: 2020/10/29 14:58
 * @company : 固远（深圳）信息技术有限公司
 **/

public abstract class BaseDBRecycleAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRecycleAdapter.Holder> {

    protected List<T> listData;
    private int layoutID = 0;

    public BaseDBRecycleAdapter(List<T> listData, int layoutID) {
        this.listData = listData;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VDB binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutID,
                parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRecycleAdapter.Holder holder, int position) {
        T item = listData.get(position);
        bindDataToView(holder, item, position);
    }

    //绑定数据到视图
    protected abstract void bindDataToView(BaseDBRecycleAdapter.Holder holder, T item, int position);

    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        } else {
            return 0;
        }
    }


    public class Holder extends RecyclerView.ViewHolder {
        public VDB binding;

        public Holder(@NonNull VDB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}