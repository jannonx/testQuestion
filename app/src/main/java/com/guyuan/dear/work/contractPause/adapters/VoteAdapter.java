package com.guyuan.dear.work.contractPause.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemVoteAdpaterBinding;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 19:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {

    private List<Vote> list;
    private Context context;

    public VoteAdapter(List<Vote> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_vote_adpater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemVoteAdpaterBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(list.get(position));
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

}
