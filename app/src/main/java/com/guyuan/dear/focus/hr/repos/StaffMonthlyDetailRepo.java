package com.guyuan.dear.focus.hr.repos;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 16:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailRepo {

    private HashMap<Long,List<NetStaffAttendRecord>> cacheAttendRecords = new HashMap<>();

    public Disposable getStaffBasicInfo(int staffId, DearNetHelper.NetCallback<NetStaffAttendStatus> callback){
        return DearNetHelper.getInstance().getStaffAttendStatus(staffId,callback);
    }

    public Disposable getStaffAttendRecord(int staffId, long date, DearNetHelper.NetCallback<List<NetStaffAttendRecord>> callback){
        if(cacheAttendRecords.get(date)!=null){
            callback.onGetResult(cacheAttendRecords.get(date));
        }else {
            String yearAndMonth = CalenderUtils.getInstance().toLongYearAndMonth(date);
            return DearNetHelper.getInstance().getStaffAttendRecord(yearAndMonth, staffId, new DearNetHelper.NetCallback<List<NetStaffAttendRecord>>() {
                @Override
                public void onStart(Disposable disposable) {
                    callback.onStart(disposable);
                }

                @Override
                public void onGetResult(List<NetStaffAttendRecord> result) {
                    cacheAttendRecords.put(date,result);
                    callback.onGetResult(result);

                }

                @Override
                public void onError(Throwable error) {
                    callback.onError(error);
                }
            });
        }
        return null;
    }

    /**
     *
     * @param nowStatus 实时状态：1.未到岗 2.迟到 3.请假 4.到岗 5.下班打卡异常
     * @return
     */
    public String getCurrentStatus(int nowStatus) {
        String desc="未知";
        switch (nowStatus){
            case 1:
                desc="未到岗";
                break;
            case 2:
                desc="迟到";
                break;
            case 3:
                desc="请假";
                break;
            case 4:
                desc="到岗";
                break;
            case 5:
                desc="下班打卡异常";
                break;
            default:
                break;
        }
        return desc;
    }
}
