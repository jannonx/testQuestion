package com.guyuan.dear.work.contractPause.adapters;

import android.content.Context;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemAddSendListBinding;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 10:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AddSendListAdapter extends RecyclerView.Adapter<AddSendListAdapter.ViewHolder> {
    private List<StaffBean> list = new ArrayList<>();
    private Context context;

    public AddSendListAdapter(List<StaffBean> list, Context context) {
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

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setDataList(List<StaffBean> dataList, boolean isNewAdd) {
        LogUtils.showLog("setDataList1100=" + dataList.size());
        LogUtils.showLog("setDataList1102123=" + list.size());
        if (isNewAdd) {
            LogUtils.showLog("isNewAdd000=" + list.size());
            list.clear();
            LogUtils.showLog("isNewAdd111=" + list.size());
        }
        list.addAll(dataList);
        LogUtils.showLog("setDataList1111=" + list.size());
        LogUtils.showLog("setDataList1111=" + dataList.size());
        notifyDataSetChanged();
    }

    public ArrayList<StaffBean> getList() {
        return (ArrayList<StaffBean>) list;
    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }
}
