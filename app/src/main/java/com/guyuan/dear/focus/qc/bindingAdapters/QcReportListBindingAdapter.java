package com.guyuan.dear.focus.qc.bindingAdapters;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 12:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcReportListBindingAdapter {

    @BindingAdapter("setQcReportTag")
    public static void setQcReportTag(AppCompatTextView view, int tag) {
        if (tag == BaseProductQcReport.TAG_TYPE_PASS) {
            view.setText("合格");
            view.setTextColor(Color.parseColor("#00B578"));
            view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
        } else if (tag == BaseProductQcReport.TAG_TYPE_REJECT) {
            view.setText("不合格");
            view.setTextColor(Color.parseColor("#FF6010"));
            view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);

        }
    }

    @BindingAdapter("showQcReportState")
    public static void showQcReportState(AppCompatTextView view, int state) {
        switch (state) {
            case BaseMaterialQcReport.REPORT_STATE_PENDING_FOR_APPROVAL:
                view.setText("待审批");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_transparent_solid_round_corner_5_blue_e7f1ff_strok);
                break;
            case BaseMaterialQcReport.REPORT_STATE_PASS:
                view.setText("通过");
                view.setTextColor(Color.parseColor("#00B578"));
                view.setBackgroundResource(R.drawable.bg_transparent_solid_round_corner_5_green_d4fff1_strok);
                break;
            case BaseMaterialQcReport.REPORT_STATE_REJECT:
                view.setText("驳回");
                view.setTextColor(Color.parseColor("#FF6010"));
                view.setBackgroundResource(R.drawable.bg_transparent_solid_round_corner_5_orange_ffece3_strok);
                break;
            default:
                view.setText("");
                view.setBackgroundResource(0);
                break;
        }

    }

}
