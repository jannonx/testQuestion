package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 17:34
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseQcRepo {

    public Disposable getQcApproaches(DearNetHelper.NetCallback<List<BaseQcApproachBean>> callback){
        return DearNetHelper.getInstance().getQcApproaches(callback);
    }

    public Disposable submitQcReport(SubmitQcReportBody body, DearNetHelper.NetCallback<Integer> callback){
        return DearNetHelper.getInstance().submitQcReport(body,callback);
    }

}
