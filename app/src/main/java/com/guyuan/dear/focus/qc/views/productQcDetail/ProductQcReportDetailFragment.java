package com.guyuan.dear.focus.qc.views.productQcDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentProductQcReportDetailBinding;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 11:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcReportDetailFragment extends BaseMvvmFragment<FragmentProductQcReportDetailBinding, ProductQcReportDetailViewModel> {

    private BaseProductQcReport report;

    public static ProductQcReportDetailFragment getInstance(BaseProductQcReport report) {
        ProductQcReportDetailFragment fragment = new ProductQcReportDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_QC_REPORT, report);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_product_qc_report_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        report = bundle.getParcelable(ConstantValue.KEY_QC_REPORT);
        getViewModel().getReportDetail(report);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_product_qc_report_detail;
    }
}
