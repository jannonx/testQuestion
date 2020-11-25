package com.guyuan.dear.focus.qc.views.materialQcDetail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMaterialQcReportDetailBinding;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 14:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcReportDetailFragment extends BaseMvvmFragment<FragmentMaterialQcReportDetailBinding, MaterialQcReportDetailViewModel> {

    public static MaterialQcReportDetailFragment getInstance(BaseMaterialQcReport report) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_QC_REPORT, report);
        MaterialQcReportDetailFragment fragment = new MaterialQcReportDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_material_qc_report_detail_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        BaseMaterialQcReport report = bundle.getParcelable(ConstantValue.KEY_QC_REPORT);
        addDisposable(getViewModel().getReportDetail(report));
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_material_qc_report_detail;
    }
}
