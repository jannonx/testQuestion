package com.guyuan.dear.focus.hr.adapter;

import android.view.View;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemHrTreeParentDeptBinding;
import com.guyuan.dear.focus.hr.bean.ParentDept;

import java.util.List;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeParentDeptAdapter extends BaseDBRecycleAdapter<ParentDept, ItemHrTreeParentDeptBinding> {
    private ItemClickListener mItemClickListener;

    public HrTreeParentDeptAdapter(List<ParentDept> listData) {
        super(listData, R.layout.item_hr_tree_parent_dept);
    }

    @Override
    protected void bindDataToView(Holder holder, ParentDept item, int position) {
        holder.binding.setData(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClickItem(item, position);
                }
            }
        });
    }

    public ItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClickItem(ParentDept item, int pos);
    }
}
