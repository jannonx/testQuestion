package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemRestartedContractListBinding;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 16:16
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractListAdapter extends RecyclerView.Adapter<RestartedContractListAdapter.ViewHolder> {
    private List<RestartedContractBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RestartedContractListAdapter(List<RestartedContractBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRestartedContractListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_restarted_contract_list,
                parent,false
        );

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemRestartedContractListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        RestartedContractBean bean = list.get(position);
        binding.setData(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(bean,position);
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

    public interface OnItemClickListener{
        void onItemClick(RestartedContractBean item,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
