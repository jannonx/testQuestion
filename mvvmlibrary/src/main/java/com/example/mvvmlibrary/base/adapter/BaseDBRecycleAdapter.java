package com.example.mvvmlibrary.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */

public abstract class BaseDBRecycleAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRecycleAdapter.Holder> {

    protected List<T> listData;
    private int layoutID = 0;
    private Context context;

    public BaseDBRecycleAdapter(List<T> listData, int layoutID) {
        this.listData = listData;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        VDB binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutID,
                parent, false);
        return new Holder(binding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull BaseDBRecycleAdapter.Holder holder, int position) {
        T item = listData.get(position);
        bindDataToView(holder, item, position);
    }

    //绑定数据到视图
    protected abstract void bindDataToView(Holder holder, T item, int position);

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

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public Context getContext(){
        return context;
    }
}