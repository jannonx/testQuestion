package com.guyuan.dear.focus.qc.views.qcReportList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityQcReportListBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * QC报告清单界面
 *
 * @author 廖华凯
 */
public class QcSumTypeListActivity extends BaseToolbarActivity<ActivityQcReportListBinding, QcReportListViewModel> {

    /**
     * 成品通过的QC列表
     */
    public static final int REPORT_TYPE_PRODUCT_PASS_REPORT = 0;
    /**
     * 成品没通过的QC列表
     */
    public static final int REPORT_TYPE_PRODUCT_REJECT_REPORT = 1;
    /**
     * 原材料通过的QC列表
     */
    public static final int REPORT_TYPE_MATERIAL_PASS_REPORT = 2;
    /**
     * 原材料没通过的QC列表
     */
    public static final int REPORT_TYPE_MATERIAL_REJECT_REPORT = 3;
    /**
     * 所有QC列表
     */
    public static final int REPORT_TYPE_ALL = 4;

    /**
     * 显示特定时间段的特定类型的QC报告列表
     *
     * @param context
     * @param title    标题
     * @param type     参考 {@link QcSumTypeListActivity#REPORT_TYPE_PRODUCT_PASS_REPORT}，{@link QcSumTypeListActivity#REPORT_TYPE_PRODUCT_REJECT_REPORT},
     *                 {@link QcSumTypeListActivity#REPORT_TYPE_MATERIAL_PASS_REPORT},{@link QcSumTypeListActivity#REPORT_TYPE_MATERIAL_REJECT_REPORT},
     *                 {@link QcSumTypeListActivity#REPORT_TYPE_ALL}
     * @param dateFrom 起始日期
     * @param dateTo   结束日期
     */
    public static void start(Context context, String title, int type, long dateFrom, long dateTo) {
        Intent starter = new Intent(context, QcSumTypeListActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_REPORT_TYPE, type);
        starter.putExtra(ConstantValue.KEY_DATE_START, dateFrom);
        starter.putExtra(ConstantValue.KEY_DATE_TO, dateTo);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qc_report_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);

        int type = intent.getIntExtra(ConstantValue.KEY_REPORT_TYPE, REPORT_TYPE_ALL);
        long dateFrom = intent.getLongExtra(ConstantValue.KEY_DATE_START, 0L);
        long dateTo = intent.getLongExtra(ConstantValue.KEY_DATE_TO, Long.MAX_VALUE);
        Fragment fragment = null;
        switch (type) {
            case REPORT_TYPE_MATERIAL_PASS_REPORT:
                fragment = QcSumTypeListFragment.getInstance(dateFrom,dateTo, QcSumTypeListFragment.TYPE_MATERIAL_PASS);
                break;
            case REPORT_TYPE_MATERIAL_REJECT_REPORT:
                fragment = QcSumTypeListFragment.getInstance(dateFrom,dateTo, QcSumTypeListFragment.TYPE_MATERIAL_REJECT);
                break;
            case REPORT_TYPE_PRODUCT_PASS_REPORT:
                fragment = QcSumTypeListFragment.getInstance(dateFrom,dateTo, QcSumTypeListFragment.TYPE_PRODUCT_PASS);
                break;
            case REPORT_TYPE_PRODUCT_REJECT_REPORT:
                fragment = QcSumTypeListFragment.getInstance(dateFrom,dateTo, QcSumTypeListFragment.TYPE_PRODUCT_REJECT);
                break;
            default:
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_qc_report_list_frame_layout, fragment)
                    .commit();
        }


    }
}