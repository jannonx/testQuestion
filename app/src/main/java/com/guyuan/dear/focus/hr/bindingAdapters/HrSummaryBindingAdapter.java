package com.guyuan.dear.focus.hr.bindingAdapters;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.customizeview.PieChartView;
import com.guyuan.dear.focus.hr.adapter.HrSummaryAdapter;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.view.hrStatusGrp.HrStatusGroupActivity;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 17:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryBindingAdapter {


    @BindingAdapter("setHrGroup")
    public static void setHrGroup(BaseRecyclerView view, List<HrStatusGroup> data){
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
        view.setLayoutManager(manager);
        HrSummaryAdapter adapter = new HrSummaryAdapter(view.getContext(),data);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setAdapter(wrapper);
        view.setPullRefreshEnabled(false);
        view.setLoadMoreEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                HrStatusGroup group = data.get(i);
                HrStatusGroupActivity.start(view.getContext(), group.getName(),group.getGrpType());
            }
        });
    }

    @BindingAdapter("setHrPieData")
    public static void setHrPieChartData(PieChartView view, List<HrStatusGroup> data){
        if(data==null){
            return;
        }
        LinkedHashMap<String,Float> pieData=new LinkedHashMap<String,Float>(data.size());
        int total=0;
        for (HrStatusGroup group : data) {
            total+=group.getStaffTotal();
        }
        for (HrStatusGroup group : data) {
            float value = group.getStaffTotal()*1.f/total;
            String label = String.format(Locale.CHINA,"%s%.0f%%",group.getName(),value*100);
            pieData.put(label,value);
        }
        view.setData(pieData,"");
    }

    @BindingAdapter("setHrSumDateFormat")
    public static void setHrSumDateFormat(AppCompatTextView view,long date){
        StringBuilder sb = new StringBuilder();
        sb.append(CalenderUtils.getInstance().toChineseMonthAndDay(date));
        sb.append(" ");
        sb.append(CalenderUtils.getInstance().getChineseDayOfWeek(date));
        view.setText(sb.toString());
    }

}
