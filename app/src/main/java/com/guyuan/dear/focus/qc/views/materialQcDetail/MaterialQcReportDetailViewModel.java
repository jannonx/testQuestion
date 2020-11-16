package com.guyuan.dear.focus.qc.views.materialQcDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.MaterialQcReportDetail;
import com.guyuan.dear.focus.qc.repo.MaterialQcDetailRepo;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 14:20
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcReportDetailViewModel extends BaseViewModel {
    private MutableLiveData<MaterialQcReportDetail> report = new MutableLiveData<>();
    private MaterialQcDetailRepo repo = new MaterialQcDetailRepo();

    public MutableLiveData<MaterialQcReportDetail> getReport() {
        return report;
    }

    public void getReportDetail(BaseMaterialQcReport report) {
        MaterialQcReportDetail detail = repo.getReportDetail(report);
        this.report.postValue(detail);
    }
}
