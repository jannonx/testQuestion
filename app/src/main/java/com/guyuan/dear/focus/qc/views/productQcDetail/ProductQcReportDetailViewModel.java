package com.guyuan.dear.focus.qc.views.productQcDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.ProductQcReportDetail;
import com.guyuan.dear.focus.qc.repo.ProductQcDetailRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 18:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcReportDetailViewModel extends BaseDearViewModel {
    private MutableLiveData<ProductQcReportDetail> report = new MutableLiveData<>();
    private ProductQcDetailRepo repo = new ProductQcDetailRepo();

    public MutableLiveData<ProductQcReportDetail> getReport() {
        return report;
    }

    public Disposable getReportDetail(BaseProductQcReport report) {
        return repo.getReportDetail(report.getReportId(), new BaseNetCallback<ProductQcReportDetail>() {
            @Override
            protected void handleResult(ProductQcReportDetail result) {
                ProductQcReportDetailViewModel.this.report.postValue(result);
            }
        });
    }

}
