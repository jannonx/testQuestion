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
//    public ProductQcReportDetail getReportDetail(BaseProductQcReport report) {
//        ProductQcReportDetail detail = new ProductQcReportDetail(report);
//        detail.setBatchSize(5000);
//        detail.setJudgeCondition("设计图样、国家标准");
//        detail.setProjectId("DEAR20201112");
//        detail.setProjectName("新金大金空分机项目");
//        detail.setQcApproach("抽检");
//        detail.setSampleSize(200);
//        if(report.isNeedVerify()){
//            List<GenericQcLogBean> logs =new ArrayList<>();
//            //1,创建日期
//            LogTypeFirstCreateDate log1 = new LogTypeFirstCreateDate();
//            log1.setDate(CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(System.currentTimeMillis(),3));
//            //2,申请详情
//            LogTypeSubmitDetail log2 =new LogTypeSubmitDetail();
//            log2.setApplierImgUrl("");
//            log2.setApplierName("莫大毛");
//            log2.setDate(CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(System.currentTimeMillis(),3));
//            log2.setDeptName("质检部");
//            log2.setJudgeCondition(detail.getJudgeCondition());
//            log2.setQcApproach(detail.getQcApproach());
//            log2.setSampleSize(detail.getSampleSize());
//            int tag = report.getTag();
//            if(tag == BaseProductQcReport.TAG_TYPE_PASS){
//                log2.setApplierComment("该批产品质量良好。");
//                log2.setQcResult("抽检通过");
//            }else {
//                log2.setApplierComment("该批产品未能通过高温测试。");
//                log2.setQcResult("返工");
//            }
//            Random random = new Random(System.currentTimeMillis());
//            //3,批复结果(pass)
//            LogTypeVerifyResult  log3 = new LogTypeVerifyResult();
//            if(random.nextInt(10)%2==0){
//                log3.setImgUrl("");
//                log3.setComment("同意通过。");
//                log3.setDate(System.currentTimeMillis());
//                log3.setName("莫二毛");
//                log3.setDept("生产部");
//                log3.setStaffId(123456);
//                log3.setResult(Vote.VOTE_RESULT_PASS);
//            }else {
//                //or 3,批复结果(reject)
//                log3.setImgUrl("");
//                log3.setComment("要求重新检测。");
//                log3.setDate(System.currentTimeMillis());
//                log3.setName("莫二毛");
//                log3.setDept("生产部");
//                log3.setStaffId(123456);
//                log3.setResult(Vote.VOTE_RESULT_REJECT);
//            }
//            logs.add(log3.toGenericLogBean());
//            logs.add(log2.toGenericLogBean());
//            logs.add(log1.toGenericLogBean());
//            detail.setVerifyLogs(logs);
//        }
//        return detail;
//    }


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
