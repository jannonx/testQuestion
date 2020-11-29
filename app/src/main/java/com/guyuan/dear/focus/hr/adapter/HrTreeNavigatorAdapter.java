package com.guyuan.dear.focus.hr.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemHrTreeTopBinding;
import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeNavigatorAdapter extends BaseDBRecycleAdapter<DeptBean, ItemHrTreeTopBinding> {
    private TopItemClickListener mListener;

    public HrTreeNavigatorAdapter(List<DeptBean> listData) {
        super(listData, R.layout.item_hr_tree_top);
    }

    @Override
    protected void bindDataToView(Holder holder, DeptBean item, int position) {
        holder.binding.setData(item);
        AppCompatImageView view = holder.binding.itemHrTreeTopIvRightArrow;
        if (position != listData.size() - 1) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        holder.binding.itemHrTreeTopTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(item, position);
                }
            }
        });

    }

    public TopItemClickListener getListener() {
        return mListener;
    }

    public void setListener(TopItemClickListener listener) {
        mListener = listener;
    }

    public interface TopItemClickListener {
        void onItemClick(DeptBean item, int pos);
    }
}
