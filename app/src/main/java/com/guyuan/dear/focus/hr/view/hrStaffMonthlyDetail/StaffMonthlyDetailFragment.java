package com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 按月显示员工的出勤记录
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 16:32
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailFragment extends BaseMvvmFragment<com.guyuan.dear.databinding.FragmentStaffMonthlyDetailBinding, StaffMonthlyDetailViewModel> {

    private int staffId;
    private CompactCalendarView calendar;

    public static StaffMonthlyDetailFragment getInstance(int staffId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_STAFF_ID, staffId);
        StaffMonthlyDetailFragment fragment = new StaffMonthlyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getViewModelBrId() {
        return BR.fragment_staff_monthly_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        staffId = bundle.getInt(ConstantValue.KEY_STAFF_ID);

        addDisposable(getViewModel().getDataFromNet(staffId));

    }

    @Override
    protected void initViews() {
        CompactCalendarView calendar = getViewDataBinding().fragmentStaffMonthlyDetailCalendar;
        calendar.setLocale(TimeZone.getDefault(), Locale.CHINESE);
        calendar.setUseThreeLetterAbbreviation(true);
        calendar.shouldDrawIndicatorsBelowSelectedDays(true);

    }

    @Override
    protected void initListeners() {
        calendar = getViewDataBinding().fragmentStaffMonthlyDetailCalendar;
        //点击上一个月
        getViewModel().onClickToPreMonth.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.scrollLeft();
            }
        });

        //点击下一个月
        getViewModel().onClickToNextMonth.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.scrollRight();

            }
        });

        //监听日历滑动事件
        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                getViewModel().currentSelectDate.postValue(dateClicked.getTime());
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getViewModel().calendarDisplayDate.postValue(firstDayOfNewMonth.getTime());
                getViewModel().updateAttendRecordsByMonth(staffId, firstDayOfNewMonth);
            }
        });

        //监听当前选择日期，如果改变，在下方显示该日出勤记录
        getViewModel().currentSelectDate.observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                List<Event> events = calendar.getEvents(aLong);
                if (events != null && !events.isEmpty()) {
                    NetStaffAttendRecord data = (NetStaffAttendRecord) events.get(0).getData();
                    getViewModel().currentSelectRecord.postValue(data);
                } else {
                    getViewModel().currentSelectRecord.postValue(null);
                }
            }
        });

        //获得最新用户该月每天的出勤数据后，更新日历显示的状况点
        getViewModel().attendRecords.observe(getViewLifecycleOwner(), new Observer<List<NetStaffAttendRecord>>() {
            @Override
            public void onChanged(List<NetStaffAttendRecord> netStaffAttendRecords) {
                calendar.removeAllEvents();
                List<Event> list = new ArrayList<>();
                for (NetStaffAttendRecord record : netStaffAttendRecords) {
                    list.addAll(convertRecordToEvents(record));
                }
                calendar.addEvents(list);
            }
        });

        //点击右上边角打电话
        getViewModel().onClickTelNumber.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactNo = getViewModel().contactNo;
                if (TextUtils.isEmpty(contactNo)) {
                    showToastTip("无联系方式");
                    return;
                }
                CommonUtils.makePhoneCall(getActivity(), contactNo);
            }
        });

    }

    private int colorAbsent = Color.parseColor("#F04864");
    private int colorNormal = Color.TRANSPARENT;
    private int colorLate = Color.parseColor("#FA8C16");
    private int colorEarlyLeave = Color.parseColor("#3436C7");
    private int colorBeforeArrangeTime = Color.TRANSPARENT;

    /**
     * 根据每日考勤状况生成每日事件，显示在日历上
     *
     * @param record
     * @return
     */
    private List<Event> convertRecordToEvents(NetStaffAttendRecord record) {
        List<Event> eventList = new ArrayList<>();
        boolean isAttendDay = record.isAttendDay() > 0;
        if (isAttendDay) {
            //上午打卡状态：0 缺席 1.正常 2.迟到
            int amStatus = record.getAmStatus();
            int color = 0;
            if (amStatus == 0) {
                color = colorAbsent;
            } else if (amStatus == 1) {
                color = colorNormal;
            } else if (amStatus == 2) {
                color = colorLate;
            } else if (amStatus == -1) {
                color = colorBeforeArrangeTime;
            }
            long time = CalenderUtils.getInstance().parseSmartFactoryDateFormatByDay(record.getTodayDate()).getTime();
            eventList.add(new Event(color, time, record));
        } else {
            long time = CalenderUtils.getInstance().parseSmartFactoryDateFormatByDay(record.getTodayDate()).getTime();
            eventList.add(new Event(colorNormal, time, record));
        }

        if (isAttendDay) {
            //下午打卡状态：0 缺席 1.正常 2.早退
            int pmStatus = record.getPmStatus();
            int color = 0;
            if (pmStatus == 0) {
                color = colorAbsent;
            } else if (pmStatus == 1) {
                color = colorNormal;
            } else if (pmStatus == 2) {
                color = colorEarlyLeave;
            } else if (pmStatus == -1) {
                color = colorBeforeArrangeTime;
            }
            long time = CalenderUtils.getInstance().parseSmartFactoryDateFormatByDay(record.getTodayDate()).getTime();
            eventList.add(new Event(color, time, record));
        } else {
            long time = CalenderUtils.getInstance().parseSmartFactoryDateFormatByDay(record.getTodayDate()).getTime();
            eventList.add(new Event(colorNormal, time, record));
        }

        return eventList;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_staff_monthly_detail;
    }
}
