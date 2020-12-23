package com.guyuan.dear.focus.hr.adapter;

import android.view.View;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemHrTreeChildDeptBinding;
import com.guyuan.dear.focus.hr.bean.ChildDept;

import java.util.List;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeChildDeptAdapter extends BaseDBRecycleAdapter<ChildDept, ItemHrTreeChildDeptBinding> {
    private HrTreeChildDeptListener mListener;
    public HrTreeChildDeptAdapter(List<ChildDept> listData) {
        super(listData, R.layout.item_hr_tree_child_dept);
    }

    @Override
    protected void bindDataToView(Holder holder, ChildDept item, int position) {
        holder.binding.setData(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(item,position);
                }
            }
        });
    }

    public HrTreeChildDeptListener getListener() {
        return mListener;
    }

    public void setListener(HrTreeChildDeptListener listener) {
        mListener = listener;
    }

    public interface HrTreeChildDeptListener{
        void onItemClick(ChildDept item,int pos);
    }
}
