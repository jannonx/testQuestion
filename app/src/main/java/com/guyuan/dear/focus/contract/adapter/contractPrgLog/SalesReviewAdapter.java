package com.guyuan.dear.focus.contract.adapter.contractPrgLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemContractPrgLogSalesReviewMeetingBinding;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.SalesReviewMeeting;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/15 15:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesReviewAdapter extends RecyclerView.Adapter<SalesReviewAdapter.ViewHolder> {
    private List<SalesReviewMeeting> list;
    private Context mContext;

    public SalesReviewAdapter(List<SalesReviewMeeting> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContractPrgLogSalesReviewMeetingBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(mContext),
                        R.layout.item_contract_prg_log_sales_review_meeting,
                        parent,
                        false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemContractPrgLogSalesReviewMeetingBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setData(list.get(position));
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
}
