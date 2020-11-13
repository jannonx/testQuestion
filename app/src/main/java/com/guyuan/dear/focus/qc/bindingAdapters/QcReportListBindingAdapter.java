package com.guyuan.dear.focus.qc.bindingAdapters;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 12:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcReportListBindingAdapter {

    @BindingAdapter("setQcReportTag")
    public static void setQcReportTag(AppCompatTextView view,int tag){
        if(tag == BaseProductQcReport.TAG_TYPE_PASS){
            view.setText("合格");
            view.setTextColor(Color.parseColor("#00B578"));
            view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
        }else if(tag == BaseProductQcReport.TAG_TYPE_REJECT){
            view.setText("不合格");
            view.setTextColor(Color.parseColor("#FF6010"));
            view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);

        }
    }

}
