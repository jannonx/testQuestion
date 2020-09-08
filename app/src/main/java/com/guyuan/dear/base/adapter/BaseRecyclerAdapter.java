package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context context;
    protected List<T> listData;
    private int layoutID = 0;


    public BaseRecyclerAdapter(Context context, @NonNull List<T> listData, int layoutID) {
        this.context = context;
        this.listData = listData;
        this.layoutID = layoutID;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(context, LayoutInflater.from
                (context).inflate(layoutID, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        T item = listData.get(position);
        bindDataToView(holder, item, position);
    }

    //绑定数据到视图
    protected abstract void bindDataToView(BaseRecyclerViewHolder holder, T item, int position);


    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        } else {
            return 0;
        }
    }

    protected List<T> getListData() {
        return listData;
    }
}
