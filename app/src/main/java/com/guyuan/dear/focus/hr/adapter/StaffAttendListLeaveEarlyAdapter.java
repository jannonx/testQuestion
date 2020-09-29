package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.LeaveEarlyAttendRecords;
import com.guyuan.dear.focus.hr.view.hrStaffAttendDetail.StaffAttendDetailViewModel;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/27 15:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendListLeaveEarlyAdapter extends BaseRecyclerAdapter<LeaveEarlyAttendRecords.LeaveEarlyIncident> {
    public StaffAttendListLeaveEarlyAdapter(Context context, List<LeaveEarlyAttendRecords.LeaveEarlyIncident> list) {
        super(context, list, R.layout.item_staff_attend_list_abnormal);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, LeaveEarlyAttendRecords.LeaveEarlyIncident item, int position) {
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_date, CalenderUtils.getInstance().toDateAndWeekDayFormat(item.getStartTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_start_work_time,CalenderUtils.getInstance().toChineseHourAndMinute(item.getStartTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time,CalenderUtils.getInstance().toChineseHourAndMinute(item.getEndTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time_comment,"早退了"+item.getDescription());
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time_comment,"");

    }
}
