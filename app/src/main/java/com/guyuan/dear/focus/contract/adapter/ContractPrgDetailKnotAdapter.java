package com.guyuan.dear.focus.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.ContractPrgKnot;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/13 17:11
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgDetailKnotAdapter extends RecyclerView.Adapter<ContractPrgDetailKnotAdapter.ViewHolder> {

    private List<ContractPrgKnot> list;
    private Context context;

    public ContractPrgDetailKnotAdapter(List<ContractPrgKnot> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contract_prg_detail_round_knot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTag.setText(list.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView ivSphere;
        private AppCompatTextView tvTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSphere = itemView.findViewById(R.id.item_contract_prg_detail_round_knot_iv_sphere);
            tvTag = itemView.findViewById(R.id.item_contract_prg_detail_round_knot_tv_tag);
        }
    }
}
