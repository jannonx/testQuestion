package com.guyuan.dear.focus.qc.repo;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.MaterialQcReportDetail;
import com.guyuan.dear.focus.qc.beans.ProductQcReportDetail;
import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeFirstCreateDate;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeSubmitDetail;
import com.guyuan.dear.focus.qc.beans.verfifyLog.LogTypeVerifyResult;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 14:19
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcDetailRepo {
    public MaterialQcReportDetail getReportDetail(BaseMaterialQcReport report) {
        MaterialQcReportDetail detail = new MaterialQcReportDetail(report);
        detail.setBatchSize(5000);
        detail.setJudgeCondition("设计图样、国家标准");
        detail.setProjectId("DEAR20201112");
        detail.setProjectName("新金大金空分机项目");
        detail.setQcApproach("抽检");
        detail.setSampleSize(200);
        detail.setComment("这是迪尔空分上个月和供应商绍兴天籁五金批发公司的订单。");
        if(report.isNeedVerify()){
            List<GenericQcLogBean> logs =new ArrayList<>();
            //1,创建日期
            LogTypeFirstCreateDate log1 = new LogTypeFirstCreateDate();
            log1.setDate(CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(System.currentTimeMillis(),3));
            //2,申请详情
            LogTypeSubmitDetail log2 =new LogTypeSubmitDetail();
            log2.setApplierImgUrl("");
            log2.setApplierName("莫大毛");
            log2.setDate(CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(System.currentTimeMillis(),3));
            log2.setDeptName("质检部");
            log2.setJudgeCondition(detail.getJudgeCondition());
            log2.setQcApproach(detail.getQcApproach());
            log2.setSampleSize(detail.getSampleSize());
            int tag = report.getTag();
            if(tag == BaseProductQcReport.TAG_TYPE_PASS){
                log2.setApplierComment("该批原材料质量可以接收。");
                log2.setQcResult("抽检通过");
            }else {
                log2.setApplierComment("该批产品尺寸存在误差。");
                log2.setQcResult("退货");
            }
            Random random = new Random(System.currentTimeMillis());
            //3,批复结果(pass)
            LogTypeVerifyResult log3 = new LogTypeVerifyResult();
            if(random.nextInt(10)%2==0){
                log3.setImgUrl("");
                log3.setComment("同意通过。");
                log3.setDate(System.currentTimeMillis());
                log3.setName("莫二毛");
                log3.setDept("生产部");
                log3.setStaffId(123456);
                log3.setResult(Vote.VOTE_RESULT_PASS);
            }else {
                //or 3,批复结果(reject)
                log3.setImgUrl("");
                log3.setComment("建议重新抽检。");
                log3.setDate(System.currentTimeMillis());
                log3.setName("莫二毛");
                log3.setDept("生产部");
                log3.setStaffId(123456);
                log3.setResult(Vote.VOTE_RESULT_REJECT);
            }
            logs.add(log3.toGenericLogBean());
            logs.add(log2.toGenericLogBean());
            logs.add(log1.toGenericLogBean());
            detail.setVerifyLogs(logs);
        }
        return detail;
    }
}
