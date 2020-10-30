package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemContractExcptCommentBinding;
import com.guyuan.dear.databinding.ItemContractExcptCommentTailBinding;
import com.guyuan.dear.focus.contract.bean.ContractApproveLog;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 16:40
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentAdapter extends RecyclerView.Adapter<ContractCommentAdapter.ViewHolder> {
    private List<ContractApproveLog> list;
    private Context context;
    private long contractCreateDate;
    private String contractApplier;
    private static final int VIEW_TYPE_TAIL = 1;

    public ContractCommentAdapter(List<ContractApproveLog> list, long contractCreateDate, String applier, Context context) {
        this.list = list;
        this.context = context;
        this.contractCreateDate = contractCreateDate;
        this.contractApplier = applier;
    }

    @Override
    public int getItemViewType(int position) {
        if (list == null || list.isEmpty() || position == list.size()) {
            return VIEW_TYPE_TAIL;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == VIEW_TYPE_TAIL) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_contract_excpt_comment_tail, parent, false);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_contract_excpt_comment, parent, false);
        }
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType==VIEW_TYPE_TAIL){
            ItemContractExcptCommentTailBinding  binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setApplier(contractApplier);
            binding.setApplyDate(contractCreateDate);
        }else {
            ItemContractExcptCommentBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setData(list.get(position));
            if(position == 0){
                binding.itemContractExcptCommentLinkLineTop.setVisibility(View.INVISIBLE);
            }else {
                binding.itemContractExcptCommentLinkLineTop.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
