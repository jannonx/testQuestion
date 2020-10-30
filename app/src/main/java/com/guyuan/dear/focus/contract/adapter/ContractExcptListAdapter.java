package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemExcptContractListBinding;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptListAdapter extends RecyclerView.Adapter<ContractExcptListAdapter.ViewHolder> {
    private List<BaseContractExcptBean> list;
    private Context context;
    private ItemClickListener itemClickListener;

    public ContractExcptListAdapter(List<BaseContractExcptBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExcptContractListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_excpt_contract_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaseContractExcptBean bean = list.get(position);
        ItemExcptContractListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(bean,position);
                }
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

    public interface ItemClickListener {
        void onItemClick(BaseContractExcptBean item, int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
