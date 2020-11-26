package com.guyuan.dear.focus.hr.repos;

import com.guyuan.dear.focus.hr.bean.HrSummaryBean;
import com.guyuan.dear.net.DearNetHelper;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 17:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryRepo {
    public Disposable getStaffSummary(DearNetHelper.NetCallback<HrSummaryBean> callback){
        return DearNetHelper.getInstance().getHrSummary(callback);
    }
}
