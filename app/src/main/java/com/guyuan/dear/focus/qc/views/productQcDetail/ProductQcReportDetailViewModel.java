package com.guyuan.dear.focus.qc.views.productQcDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.ProductQcReportDetail;
import com.guyuan.dear.focus.qc.repo.ProductQcDetailRepo;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 18:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcReportDetailViewModel extends BaseViewModel {
    private MutableLiveData<ProductQcReportDetail> report = new MutableLiveData<>();
    private ProductQcDetailRepo repo = new ProductQcDetailRepo();

    public MutableLiveData<ProductQcReportDetail> getReport() {
        return report;
    }

    public void getReportDetail(BaseProductQcReport report){
        ProductQcReportDetail detail = repo.getReportDetail(report);
        this.report.postValue(detail);
    }
}
