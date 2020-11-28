package com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentStaffMonthlyDetailBinding;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.utils.CalenderUtils;
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
public class StaffMonthlyDetailFragment extends BaseMvvmFragment<FragmentStaffMonthlyDetailBinding, StaffMonthlyDetailViewModel> {

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
                List<Event> events = calendar.getEvents(dateClicked);
                if (!events.isEmpty()) {
                    NetStaffAttendRecord data = (NetStaffAttendRecord) events.get(0).getData();
                    getViewModel().currentSelectRecord.postValue(data);
                }else {
                    getViewModel().currentSelectRecord.postValue(null);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getViewModel().calendarDisplayDate.postValue(firstDayOfNewMonth.getTime());
            }
        });

        //获得最新用户该月每天的出勤数据后，更新日历显示的状况点
        getViewModel().attendRecords.observe(getViewLifecycleOwner(), new Observer<List<NetStaffAttendRecord>>() {
            @Override
            public void onChanged(List<NetStaffAttendRecord> netStaffAttendRecords) {
                calendar.removeAllEvents();
                List<Event> list = new ArrayList<>();
                for (NetStaffAttendRecord record : netStaffAttendRecords) {
                    Event event = convertRecordToEvent(record);
                    if (event != null) {
                        list.add(event);
                    }
                }
                calendar.addEvents(list);
            }
        });

    }

    private Event convertRecordToEvent(NetStaffAttendRecord record) {
        String todayDate = record.getAmStartTime();
        long timeMillis = 0;
        try {
            timeMillis = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(todayDate).getTime();
        } catch (Exception e) {
            showToastTip("服务器返回的出勤记录的日期格式无效。");
            return null;
        }
        int color = -1;
        //上午打卡状态：1.正常 2.迟到
        int amStatus = record.getAmStatus();
        if (amStatus == 1) {
            color = Color.parseColor("#1677FF");
        } else if (amStatus == 2) {
            color = Color.parseColor("#FA8C16");
        }
        int pmStatus = record.getPmStatus();
        //下午打卡状态：1.正常 2.早退
        if (pmStatus == 1) {
            if (color == -1) {
                color = Color.parseColor("#1677FF");
            }
        } else if (pmStatus == 2) {
            color = Color.parseColor("#3436C7");
        }
        if (color == -1) {
            return null;
        }
        return new Event(color, timeMillis, record);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_staff_monthly_detail;
    }
}
