package com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.hr.repos.StaffMonthlyDetailRepo;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.utils.CalenderUtils;

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
    public MutableLiveData<List<NetStaffAttendRecord>> attendRecords = new MutableLiveData<>();
    public MutableLiveData<NetStaffAttendRecord> currentSelectRecord = new MutableLiveData<>();

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
}
