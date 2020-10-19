package com.guyuan.dear.focus.sales.adapter.contractPrgLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.databinding.ItemSalesReviewVoteBinding;
import com.guyuan.dear.focus.sales.bean.contractPrgLog.Vote;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/15 15:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesVoteAdapter extends RecyclerView.Adapter<SalesVoteAdapter.ViewHolder> {

    private List<Vote> mList;
    private Context mContext;

    public SalesVoteAdapter(List<Vote> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSalesReviewVoteBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_sales_review_vote, parent, false
        );
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemSalesReviewVoteBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
