package com.guyuan.dear.work.contractPause.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemPauseApplyCommentsBinding;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.MeetingComment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 20:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseApplyCommentAdapter extends RecyclerView.Adapter<PauseApplyCommentAdapter.ViewHolder> {
    private List<MeetingComment> list;
    private Context context;

    public PauseApplyCommentAdapter(List<MeetingComment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<MeetingComment> getList() {
        return list;
    }

    public void setList(List<MeetingComment> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_pause_apply_comments, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPauseApplyCommentsBinding binding = DataBindingUtil.getBinding(holder.itemView);
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
