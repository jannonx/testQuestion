package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.NormalAttendRecords;
import com.guyuan.dear.focus.hr.view.hrStaffAttendDetail.StaffAttendDetailViewModel;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.Calendar;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/27 14:32
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendListNormalAdapter extends BaseRecyclerAdapter<NormalAttendRecords.NormalAttendance> {
    public StaffAttendListNormalAdapter(Context context, List<NormalAttendRecords.NormalAttendance> list) {
        super(context, list, R.layout.item_staff_attend_list_normal);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, NormalAttendRecords.NormalAttendance item, int position) {
        long startTime = item.getStartTime();
        String date = CalenderUtils.getInstance().toDateAndWeekDayFormat(startTime);
        holder.setText(R.id.item_staff_attend_list_normal_tv_date,date);
        holder.setText(R.id.item_staff_attend_list_normal_tv_work_start_time, CalenderUtils.getInstance().toChineseHourAndMinute(startTime));
        holder.setText(R.id.item_staff_attend_list_normal_tv_work_end_time,CalenderUtils.getInstance().toChineseHourAndMinute(item.getEndTime()));
    }
}
