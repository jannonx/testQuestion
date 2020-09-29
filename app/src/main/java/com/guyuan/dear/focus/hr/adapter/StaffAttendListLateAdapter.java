package com.guyuan.dear.focus.hr.adapter;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.hr.bean.LateAttendRecords;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/27 14:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffAttendListLateAdapter extends BaseRecyclerAdapter<LateAttendRecords.LateIncident> {
    public StaffAttendListLateAdapter(Fragment context, List<LateAttendRecords.LateIncident> list) {
        super(context.getContext(), list, R.layout.item_staff_attend_list_abnormal);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, LateAttendRecords.LateIncident item, int position) {
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_date, CalenderUtils.getInstance().toDateAndWeekDayFormat(item.getBeginTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_start_work_time, CalenderUtils.getInstance().toChineseHourAndMinute(item.getBeginTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time, CalenderUtils.getInstance().toChineseHourAndMinute(item.getEndTime()));
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_start_work_time_comment, "迟到了" + item.getDescription());
        holder.setText(R.id.item_staff_attend_list_abnormal_tv_stop_work_time_comment, "");
    }
}
