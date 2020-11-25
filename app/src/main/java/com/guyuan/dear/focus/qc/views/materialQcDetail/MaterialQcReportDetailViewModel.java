package com.guyuan.dear.focus.qc.views.materialQcDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.MaterialQcReportDetail;
import com.guyuan.dear.focus.qc.repo.MaterialQcDetailRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

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

    public Disposable getReportDetail(BaseMaterialQcReport report) {
        return repo.getReportDetail(report.getReportId(), new DearNetHelper.NetCallback<MaterialQcReportDetail>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);
            }

            @Override
            public void onGetResult(MaterialQcReportDetail result) {
                isShowLoading.postValue(false);
                MaterialQcReportDetailViewModel.this.report.postValue(result);
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

            }
        });
    }
}
