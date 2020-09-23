package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.StaffBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 15:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStaffAdapter extends BaseRecyclerAdapter<StaffBean> {
    private int grpType;
    private static final int VIEW_TYPE_LOAD_MORE = 1;
    private HrStaffAdapterCallback callback;

    public HrStaffAdapter(Context context, @NonNull List<StaffBean> listData, int grpType) {
        super(context, listData, R.layout.item_hr_staff);
        this.grpType = grpType;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < listData.size()) {
            return super.getItemViewType(position);
        } else {
            return VIEW_TYPE_LOAD_MORE;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size() + 1;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != VIEW_TYPE_LOAD_MORE) {
            return super.onCreateViewHolder(parent, viewType);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_hr_load_more, parent, false);
            return new HrStaffLoadMoreVh(context, view);
        }
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType != VIEW_TYPE_LOAD_MORE) {
            super.onBindViewHolder(holder, position);
        } else {
            bindDataToView(holder, null, position);
        }
    }


    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, StaffBean item, int position) {
        int viewType = getItemViewType(position);
        if (viewType != VIEW_TYPE_LOAD_MORE) {
            holder.setText(R.id.item_hr_staff_tv_name, item.getName());
        } else {
            holder.setOnClickListener(R.id.item_hr_load_more_iv_add, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.onClickLoadMore(grpType, listData.size(), 50);
                    }
                }
            });
        }
    }


    public static class HrStaffLoadMoreVh extends BaseRecyclerViewHolder {

        public HrStaffLoadMoreVh(Context context, View itemView) {
            super(context, itemView);
        }
    }


    public interface HrStaffAdapterCallback {
        void onClickLoadMore(int grpType, int index, int size);
    }

    public void setCallback(HrStaffAdapterCallback callback) {
        this.callback = callback;
    }
}
