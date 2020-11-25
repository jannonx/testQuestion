package com.guyuan.dear.focus.qc.views.productQcDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
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
public class ProductQcReportDetailViewModel extends BaseViewModel {
    private MutableLiveData<ProductQcReportDetail> report = new MutableLiveData<>();
    private ProductQcDetailRepo repo = new ProductQcDetailRepo();

    public MutableLiveData<ProductQcReportDetail> getReport() {
        return report;
    }

    public Disposable getReportDetail(BaseProductQcReport report) {
        return repo.getReportDetail(report.getReportId(), getDetailCallback);
    }

    private DearNetHelper.NetCallback<ProductQcReportDetail> getDetailCallback = new DearNetHelper.NetCallback<ProductQcReportDetail>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(ProductQcReportDetail result) {
            isShowLoading.postValue(false);
            report.postValue(result);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };
}
