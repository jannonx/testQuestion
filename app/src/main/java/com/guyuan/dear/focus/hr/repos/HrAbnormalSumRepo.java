package com.guyuan.dear.focus.hr.repos;

import com.guyuan.dear.focus.hr.bean.HrSummaryBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.CalenderUtils;

import io.reactivex.disposables.Disposable;

/**
 * Author: 廖华凯
 * Date: 2020/11/28
 * Project: Dear
 * Description:
 */
public class HrAbnormalSumRepo{
    public Disposable getHrAbnormalSum(long date, DearNetHelper.NetCallback<HrSummaryBean> callback){
        String yearAndMonth = CalenderUtils.getInstance().toLongYearAndMonth(date);
        return DearNetHelper.getInstance().getHrAbnormalSumByDate(yearAndMonth,callback);
    }

}
