package com.guyuan.dear.work.contractPause.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemMyPauseApplyListBinding;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyListAdapter extends RecyclerView.Adapter<MyPauseApplyListAdapter.ViewHolder> {

    private List<MyPauseApplyBean> list;
    private Context context;
    private MyPauseListItemClickListener myPauseListItemClickListener;

    public MyPauseApplyListAdapter(List<MyPauseApplyBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_my_pause_apply_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemMyPauseApplyListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final MyPauseApplyBean bean = list.get(position);
        binding.setData(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myPauseListItemClickListener!=null){
                    myPauseListItemClickListener.onClickMyPauseApply(bean,position);
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

    public interface MyPauseListItemClickListener {
        void onClickMyPauseApply(MyPauseApplyBean item,int pos);
    }

    public void setMyPauseListItemClickListener(MyPauseListItemClickListener myPauseListItemClickListener) {
        this.myPauseListItemClickListener = myPauseListItemClickListener;
    }
}
