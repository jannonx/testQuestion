package com.guyuan.dear.focus.hr.adapter;

import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemHrTreeStaffBinding;
import com.guyuan.dear.focus.hr.bean.StaffWorkStatusInfo;

import java.util.List;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeStaffAdapter extends BaseDBRecycleAdapter<StaffWorkStatusInfo, ItemHrTreeStaffBinding> {
    private HrTreeStaffAdapterListener mListener;

    public HrTreeStaffAdapter(List<StaffWorkStatusInfo> listData) {
        super(listData, R.layout.item_hr_tree_staff);
    }

    @Override
    protected void bindDataToView(Holder holder, StaffWorkStatusInfo item, int position) {
        holder.binding.setData(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(item, position);
                }
            }
        });
    }

    public HrTreeStaffAdapterListener getListener() {
        return mListener;
    }

    public void setListener(HrTreeStaffAdapterListener listener) {
        mListener = listener;
    }

    public interface HrTreeStaffAdapterListener{
        void onItemClick( StaffWorkStatusInfo item, int position);
    }

}
