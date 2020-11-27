package com.guyuan.dear.focus.hr.repos;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetStaffAttendRecord;
import com.guyuan.dear.net.resultBeans.NetStaffAttendStatus;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 16:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailRepo {
    public Disposable getStaffBasicInfo(int staffId, DearNetHelper.NetCallback<NetStaffAttendStatus> callback){
        return DearNetHelper.getInstance().getStaffAttendStatus(staffId,callback);
    }

    public Disposable getStaffAttendRecord(int staffId, long date, DearNetHelper.NetCallback<List<NetStaffAttendRecord>> callback){
        String yearAndMonth = CalenderUtils.getInstance().toLongYearAndMonth(date);
        return DearNetHelper.getInstance().getStaffAttendRecord(yearAndMonth,staffId,callback);
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
