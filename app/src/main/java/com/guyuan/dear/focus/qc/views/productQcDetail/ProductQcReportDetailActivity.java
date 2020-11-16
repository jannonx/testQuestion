package com.guyuan.dear.focus.qc.views.productQcDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityProductQcReportDetailBinding;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 产品QC报告详情
 * @author 廖华凯
 */
public class ProductQcReportDetailActivity extends BaseToolbarActivity<ActivityProductQcReportDetailBinding, ProductQcReportDetailViewModel> {

    public static void start(Context context, String title,BaseProductQcReport report) {
        Intent starter = new Intent(context, ProductQcReportDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_QC_REPORT,report);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_product_qc_report_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        ProductQcReportDetailFragment fragment = ProductQcReportDetailFragment.getInstance(
                intent.getParcelableExtra(ConstantValue.KEY_QC_REPORT)
        );
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_product_qc_report_detail_frame_layout,fragment)
                .commit();
    }
}