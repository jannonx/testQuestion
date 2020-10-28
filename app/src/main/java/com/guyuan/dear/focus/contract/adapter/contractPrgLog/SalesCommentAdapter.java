package com.guyuan.dear.focus.contract.adapter.contractPrgLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemSalesReviewCommentBinding;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/15 18:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesCommentAdapter extends RecyclerView.Adapter<SalesCommentAdapter.ViewHolder> {
    private Context mContext;
    private List<MeetingComment> mList;


    public SalesCommentAdapter(Context mContext, List<MeetingComment> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSalesReviewCommentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_sales_review_comment, parent, false
        );
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemSalesReviewCommentBinding binding = DataBindingUtil.getBinding(holder.itemView);
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
