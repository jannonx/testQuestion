package com.guyuan.dear.work.contractPause.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemAddSendListBinding;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 10:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AddCopyListAdapter extends RecyclerView.Adapter<AddCopyListAdapter.ViewHolder> {
    private List<StaffBean> list;
    private Context context;

    public AddCopyListAdapter(List<StaffBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_add_send_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemAddSendListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(list.get(position));
        binding.setOnClickDelete(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setDataList(List<StaffBean> dataList, boolean isAdd) {
        if (isAdd) list.clear();
        list.addAll(dataList);
        notifyDataSetChanged();
    }
    public ArrayList<StaffBean> getList() {
        return (ArrayList<StaffBean>) list;
    }
}
