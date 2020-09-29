package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.AbsentAttendRecords;
import com.guyuan.dear.focus.hr.view.hrStaffAttendDetail.StaffAttendDetailViewModel;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/27 15:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendListAbsentAdapter extends BaseRecyclerAdapter<AbsentAttendRecords.AbsentIncident> {
    public StaffAttendListAbsentAdapter(Context context, List<AbsentAttendRecords.AbsentIncident> list) {
        super(context, list, R.layout.item_staff_attend_list_abnormal);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, AbsentAttendRecords.AbsentIncident item, int position) {
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_date, CalenderUtils.getInstance().toDateAndWeekDayFormat(item.getDate()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_start_work_time_comment,"无记录");
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time_comment,"无记录");
    }
}
