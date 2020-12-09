package com.guyuan.dear.focus.qc.repo;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.ProductQcReportDetail;
import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeFirstCreateDate;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeSubmitDetail;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeVerifyResult;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 18:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcDetailRepo {

    public Disposable getReportDetail(int reportId, DearNetHelper.NetCallback<ProductQcReportDetail> callback){
        return DearNetHelper.getInstance().getProductQcReportDetailById(reportId, new DearNetHelper.NetCallback<ProductQcReportDetail>() {
            @Override
            public void onStart(Disposable disposable) {
                callback.onStart(disposable);
            }

            @Override
            public void onGetResult(ProductQcReportDetail result) {
                getReportApproveFlow(result,callback);
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
    }

    private void getReportApproveFlow(ProductQcReportDetail detail, DearNetHelper.NetCallback<ProductQcReportDetail> callback) {
        DearNetHelper.getInstance().getQcApproveFlowById(detail.getReportId(), new DearNetHelper.NetCallback<List<GenericQcLogBean>>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onGetResult(List<GenericQcLogBean> result) {
                detail.setVerifyLogs(result);
                callback.onGetResult(detail);
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
    }


}
