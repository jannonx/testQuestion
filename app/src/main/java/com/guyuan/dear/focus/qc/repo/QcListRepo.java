package com.guyuan.dear.focus.qc.repo;

import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 14:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcListRepo {

    public List<BaseProductQcReport> getProductPassList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<BaseProductQcReport> reports = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            BaseProductQcReport report = new BaseProductQcReport();
            report.setBatchId("BD20201112");
            report.setDate(System.currentTimeMillis());
            report.setNeedVerify(i % 2 == 0);
            report.setProductId("DEAR001" + i);
            report.setProductName("产品" + i + 1);
            report.setQualityChecker("莫大毛");
            report.setTag(BaseProductQcReport.TAG_TYPE_PASS);
            reports.add(report);
        }
        return reports;
    }

    public List<BaseProductQcReport> getProductRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<BaseProductQcReport> reports = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            BaseProductQcReport report = new BaseProductQcReport();
            report.setBatchId("BD20201112");
            report.setDate(System.currentTimeMillis());
            report.setNeedVerify(i % 2 == 0);
            report.setProductId("DEAR001" + i);
            report.setProductName("产品" + i + 1);
            report.setQualityChecker("莫二毛");
            report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
            reports.add(report);
        }
        return reports;
    }

    public List<BaseMaterialQcReport> getMaterialPassList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<BaseMaterialQcReport> reports = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            BaseMaterialQcReport report = new BaseMaterialQcReport();
            report.setDate(System.currentTimeMillis());
            report.setMaterialId("MT210210" + i);
            report.setMaterialName("原材料" + i + 1);
            report.setNeedVerify(i % 2 == 0);
            report.setSpec("203*504");
            report.setQualityChecker("莫二毛");
            report.setTag(BaseProductQcReport.TAG_TYPE_PASS);
            report.setMaterialType("金属");
            reports.add(report);
        }
        return reports;
    }

    public List<BaseMaterialQcReport> getMaterialRejectList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<BaseMaterialQcReport> reports = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            BaseMaterialQcReport report = new BaseMaterialQcReport();
            report.setDate(System.currentTimeMillis());
            report.setMaterialId("MT999910" + i);
            report.setMaterialName("原材料" + i + 1);
            report.setNeedVerify(i % 2 == 0);
            report.setSpec("203*504");
            report.setQualityChecker("莫三毛");
            report.setMaterialType("金属");
            report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
            reports.add(report);
        }
        return reports;
    }

    public List<GenericQcReport> getAllRejectedQcList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<GenericQcReport> list = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            if (i % 2 == 0) {
                BaseProductQcReport report = new BaseProductQcReport();
                report.setBatchId("BD20201112");
                report.setDate(System.currentTimeMillis());
                report.setNeedVerify(i / 2 % 2 == 0);
                report.setProductId("DEAR001" + i);
                report.setProductName("产品" + i + 1);
                report.setQualityChecker("莫大毛");
                report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
                list.add(report.toGenericQcReport());
            } else {
                BaseMaterialQcReport report = new BaseMaterialQcReport();
                report.setDate(System.currentTimeMillis());
                report.setMaterialId("MT999910" + i);
                report.setMaterialName("原材料" + i + 1);
                report.setNeedVerify(i / 2 % 2 == 0);
                report.setSpec("203*504");
                report.setQualityChecker("莫三毛");
                report.setMaterialType("金属");
                report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
                list.add(report.toGenericQcReport());
            }
        }
        return list;
    }

    public List<GenericQcReport> getAllQcList(long dateFrom, long dateTo, int pageIndex, int pageSize) {
        List<GenericQcReport> list = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            int j = i % 4;
            switch (j) {
                case 0:
                {
                    BaseProductQcReport report = new BaseProductQcReport();
                    report.setBatchId("BD20201112");
                    report.setDate(System.currentTimeMillis());
                    report.setNeedVerify(i / 2 % 2 == 0);
                    report.setProductId("DEAR001" + i);
                    report.setProductName("产品" + i + 1);
                    report.setQualityChecker("莫大毛");
                    report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
                    list.add(report.toGenericQcReport());
                }
                    break;
                case 1:
                {
                    BaseProductQcReport report = new BaseProductQcReport();
                    report.setBatchId("BD20201112");
                    report.setDate(System.currentTimeMillis());
                    report.setNeedVerify(i / 2 % 2 == 0);
                    report.setProductId("DEAR001" + i);
                    report.setProductName("产品" + i + 1);
                    report.setQualityChecker("莫大毛");
                    report.setTag(BaseProductQcReport.TAG_TYPE_PASS);
                    list.add(report.toGenericQcReport());
                }
                    break;
                case 2:
                {
                    BaseMaterialQcReport report = new BaseMaterialQcReport();
                    report.setDate(System.currentTimeMillis());
                    report.setMaterialId("MT999910" + i);
                    report.setMaterialName("原材料" + i + 1);
                    report.setNeedVerify(i / 2 % 2 == 0);
                    report.setSpec("203*504");
                    report.setQualityChecker("莫三毛");
                    report.setMaterialType("金属");
                    report.setTag(BaseProductQcReport.TAG_TYPE_REJECT);
                    list.add(report.toGenericQcReport());
                }
                    break;
                case 3:
                default:
                {
                    BaseMaterialQcReport report = new BaseMaterialQcReport();
                    report.setDate(System.currentTimeMillis());
                    report.setMaterialId("MT999910" + i);
                    report.setMaterialName("原材料" + i + 1);
                    report.setNeedVerify(i / 2 % 2 == 0);
                    report.setSpec("203*504");
                    report.setQualityChecker("莫三毛");
                    report.setMaterialType("金属");
                    report.setTag(BaseProductQcReport.TAG_TYPE_PASS);
                    list.add(report.toGenericQcReport());
                }
                    break;
            }
        }
        return list;
    }
}
