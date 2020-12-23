package com.guyuan.dear.focus.hr.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemHrGroupByAttendanceBinding;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 14:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStaffGrpAdapter extends BaseDBRecycleAdapter<HrStaffsByDept, ItemHrGroupByAttendanceBinding> {
    private HrStaffAdapter.HrStaffAdapterItemClickListener itemClickListener;

    public HrStaffGrpAdapter(List<HrStaffsByDept> listData) {
        super(listData, R.layout.item_hr_group_by_attendance);
    }

    @Override
    protected void bindDataToView(Holder holder, HrStaffsByDept item, int position) {
        holder.binding.setData(item);
        if (itemClickListener != null) {
            holder.binding.setItemClickListener(itemClickListener);
        }

    }

    public HrStaffAdapter.HrStaffAdapterItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(HrStaffAdapter.HrStaffAdapterItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
