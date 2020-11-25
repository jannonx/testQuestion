package com.guyuan.dear.focus.qc.repo;

import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 14:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcListRepo {

    public Disposable getProductPassList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {

        return DearNetHelper.getInstance().getProductQcPassList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getProductRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {
        return DearNetHelper.getInstance().getProductQcRejectList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getMaterialPassList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {

        return DearNetHelper.getInstance().getMaterialQcPassList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getMaterialRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {
        return DearNetHelper.getInstance().getMaterialQcRejectList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getAllRejectedQcList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {
        return DearNetHelper.getInstance().getAllRejectedQcList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getAllQcList(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {
        return DearNetHelper.getInstance().getAllTypesOfQcList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }

    public Disposable getMyQcReports(long dateFrom, long dateTo, int pageIndex, int pageSize, DearNetHelper.NetCallback<List<GenericQcReport>> callback) {
        return DearNetHelper.getInstance().getMyQcReportList(pageIndex, pageSize, dateFrom, dateTo, callback);
    }
}
