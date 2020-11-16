package com.guyuan.dear.focus.qc.views.materialQcDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityMaterialQcReportDetailBinding;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 原材料qc详情界面
 *
 * @author 廖华凯
 * @date 2020/11/13
 */
public class MaterialQcReportDetailActivity extends BaseToolbarActivity<ActivityMaterialQcReportDetailBinding, MaterialQcReportDetailViewModel> {

    public static void start(Context context, String title, BaseMaterialQcReport report) {
        Intent starter = new Intent(context, MaterialQcReportDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_QC_REPORT, report);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_material_qc_report_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        MaterialQcReportDetailFragment fragment = MaterialQcReportDetailFragment
                .getInstance(intent.getParcelableExtra(ConstantValue.KEY_QC_REPORT));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_material_qc_report_detail_frame_layout, fragment)
                .commit();
    }
}