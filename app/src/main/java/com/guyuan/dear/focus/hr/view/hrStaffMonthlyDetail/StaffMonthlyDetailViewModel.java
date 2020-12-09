package com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.hr.repos.StaffMonthlyDetailRepo;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 16:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailViewModel extends BaseDearViewModel {
    private StaffMonthlyDetailRepo repo = new StaffMonthlyDetailRepo();

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> imgUrl = new MutableLiveData<>();
    public MutableLiveData<String> workId = new MutableLiveData<>();
    public String contactNo;
    public MutableLiveData<String> dept = new MutableLiveData<>();
    public MutableLiveData<String> post = new MutableLiveData<>();
    public MutableLiveData<String> enrollment = new MutableLiveData<>();
    public MutableLiveData<Long> currentSelectDate = new MutableLiveData<>(System.currentTimeMillis());
    public MutableLiveData<Long> calendarDisplayDate = new MutableLiveData<>(System.currentTimeMillis());
    public MutableLiveData<List<NetStaffAttendRecord>> attendRecords = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<NetStaffAttendRecord> currentSelectRecord = new MutableLiveData<>();
    public MutableLiveData<Boolean> isMe = new MutableLiveData<>(false);

    public MutableLiveData<View.OnClickListener> onClickTelNumber = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickToPreMonth = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickToNextMonth = new MutableLiveData<>();

    public Disposable getDataFromNet(int staffId) {
        return repo.getStaffBasicInfo(staffId, new BaseNetCallback<NetStaffAttendStatus>() {
            @Override
            protected void handleResult(NetStaffAttendStatus result) {
                name.postValue(result.getName());
                imgUrl.postValue(result.getImgUrl());
                workId.postValue(result.getWorkId());
                dept.postValue(result.getDeptIdName());
                post.postValue(result.getRoleName());
                //如果是当前用户，则把拨打电话的选项隐藏。
                isMe.postValue(result.getId()== CommonUtils.getCurrentUserId());
                contactNo = result.getUserPhone();
                //实时状态：1.未到岗 2.迟到 3.请假 4.到岗 5.下班打卡异常
                enrollment.postValue(result.getNowStatus());
                Disposable disposable = repo.getStaffAttendRecord(
                        staffId,
                        System.currentTimeMillis(),
                        new BaseNetCallback<List<NetStaffAttendRecord>>() {
                            @Override
                            protected void handleResult(List<NetStaffAttendRecord> result) {
                                attendRecords.postValue(result);
                                //遍历是否今天有记录，有的话显示当天的记录
                                String today = CalenderUtils.getInstance().toSmartFactoryDateFormatByDay(System.currentTimeMillis());
                                for (NetStaffAttendRecord record : result) {
                                    String todayDate = record.getTodayDate();
                                    if (!TextUtils.isEmpty(todayDate) && todayDate.equals(today)) {
                                        currentSelectRecord.postValue(record);
                                        break;
                                    }
                                }
                            }
                        });
                addSubscription(disposable);

            }
        });
    }


    public void updateAttendRecordsByMonth(int staffId, Date month) {
        Disposable disposable = repo.getStaffAttendRecord(
                staffId,
                month.getTime(),
                new BaseNetCallback<List<NetStaffAttendRecord>>() {
                    @Override
                    protected void handleResult(List<NetStaffAttendRecord> result) {
                        attendRecords.postValue(result);
                    }
                });
        addSubscription(disposable);
    }

    public void updateTodayAttendRecord(int staffId) {
        //清除缓存中的本月信息
        repo.clearCacheData(System.currentTimeMillis());
        //获取最新本月信息
        Disposable disposable = repo.getStaffAttendRecord(staffId, System.currentTimeMillis(), updateToadyRecordCallBack);
        addSubscription(disposable);
    }

    private BaseNetCallback<List<NetStaffAttendRecord>> updateToadyRecordCallBack = new BaseNetCallback<List<NetStaffAttendRecord>>() {
        @Override
        protected void handleResult(List<NetStaffAttendRecord> result) {
            //判断当前界面显示的日历月份是否就是本月
            Calendar curDisplayMonth = Calendar.getInstance();
            curDisplayMonth.setTimeInMillis(calendarDisplayDate.getValue());
            Calendar todayCalendar = Calendar.getInstance();
            if (curDisplayMonth.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
                    && curDisplayMonth.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH)) {
                //如果是，才刷新日历
                attendRecords.postValue(result);
                //如果当前被选的日子为当天，也要更新当天的考勤时间
                NetStaffAttendRecord value = currentSelectRecord.getValue();
                if (value != null) {
                    long time = CalenderUtils.getInstance().parseSmartFactoryDateFormatByDay(value.getTodayDate()).getTime();
                    Calendar selectCalendar = Calendar.getInstance();
                    selectCalendar.setTimeInMillis(time);
                    if (selectCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
                            && selectCalendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH)
                            && selectCalendar.get(Calendar.DAY_OF_MONTH) == todayCalendar.get(Calendar.DAY_OF_MONTH)) {
                        if (!result.isEmpty()) {
                            NetStaffAttendRecord record = result.get(result.size()-1);
                            currentSelectRecord.postValue(record);
                        } else {
                            currentSelectRecord.postValue(null);
                        }
                    }
                }
            }
        }
    };
}
