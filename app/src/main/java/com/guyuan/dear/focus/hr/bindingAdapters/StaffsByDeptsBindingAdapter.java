package com.guyuan.dear.focus.hr.bindingAdapters;

import android.widget.ExpandableListView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.focus.hr.adapter.StaffsByDeptExpListAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 18:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffsByDeptsBindingAdapter {
    @BindingAdapter("setDeptData")
    public static void setDeptData(ExpandableListView view, List<HrStaffsByDept> depts){
        StaffsByDeptExpListAdapter adapter = new StaffsByDeptExpListAdapter(depts,view.getContext());
        view.setAdapter(adapter);
        view.setGroupIndicator(null);
        view.setChildIndicator(null);
    }
}
