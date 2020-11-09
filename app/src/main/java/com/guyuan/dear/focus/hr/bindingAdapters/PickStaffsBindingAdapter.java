package com.guyuan.dear.focus.hr.bindingAdapters;

import android.widget.ExpandableListView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.hr.adapter.PickStaffsExpListAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;
import java.util.Locale;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsBindingAdapter {
    @BindingAdapter(value = {"setPickStaffsExpListData", "setPickStaffItemCallback"}, requireAll = true)
    public static void setPickStaffsExpListData(ExpandableListView view, List<PickStaffsExpParentBean> data,
                                                PickStaffsExpListAdapter.ItemCallback callback) {
        if (data == null) {
            return;
        }
        PickStaffsExpListAdapter adapter = new PickStaffsExpListAdapter(data, view.getContext());
        view.setAdapter(adapter);
        view.setDividerHeight(0);
        view.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setExpanded(i == groupPosition);
                    if (i != groupPosition) {
                        view.collapseGroup(i);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setItemCallback(callback);

    }


    @BindingAdapter(value = {"setPickStaffsHistoryStaffs", "setPickStaffsHistoryItemClickListener"}, requireAll = true)
    public static void setPickStaffsHistoryStaffs(RecyclerView view, List<PickStaffBean> data,
                                                  PickStaffsHistoryItemClickListener listener) {
        if (data == null) {
            return;
        }
        if (view.getAdapter() == null) {
            GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 5, RecyclerView.VERTICAL, false);
            PickStaffsHistoryStaffsAdapter adapter = new PickStaffsHistoryStaffsAdapter(data, view.getContext());
            view.setLayoutManager(layoutManager);
            view.setAdapter(adapter);
            adapter.setItemClickListener(listener);
        }
    }

    @BindingAdapter("setExpListParentRightArrowIndicator")
    public static void setExpListParentRightArrowIndicator(AppCompatImageView view, boolean isExpanded){
        if(isExpanded){
            view.setImageResource(R.drawable.ic_svg_bottom_arrow);
        }else {
            view.setImageResource(R.drawable.ic_svg_right_arrow);
        }
    }

    @BindingAdapter("setShowSelectVsTotal")
    public static void setShowSelectVsTotal(AppCompatTextView view,PickStaffsExpParentBean data){
        int total = data.getStaffTotal();
        int selectCount=0;
        for (PickStaffBean staff : data.getStaffs()) {
            if(staff.isPick()){
                selectCount++;
            }
        }
        view.setText(String.format(Locale.CHINA,"%d/%d",selectCount,total));
    }


}
