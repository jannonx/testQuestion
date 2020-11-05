package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemPickStaffsHistoryStaffsBinding;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 14:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsHistoryStaffsAdapter extends RecyclerView.Adapter<PickStaffsHistoryStaffsAdapter.ViewHolder> {
    private List<PickStaffBean> list;
    private Context context;
    private PickStaffsHistoryItemClickListener itemClickListener;

    public PickStaffsHistoryStaffsAdapter(List<PickStaffBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_pick_staffs_history_staffs, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PickStaffBean bean = list.get(position);
        ItemPickStaffsHistoryStaffsBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setPick(!bean.isPick());
                notifyItemChanged(position);
                if(itemClickListener !=null){
                    itemClickListener.onItemClick(bean,position);
                }
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

    public interface PickStaffsHistoryItemClickListener{
        void onItemClick(PickStaffBean bean,int pos);
    }

    public void setItemClickListener(PickStaffsHistoryItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public List<PickStaffBean> getList() {
        return list;
    }

    public void setList(List<PickStaffBean> list) {
        this.list = list;
    }
}
