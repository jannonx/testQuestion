package com.guyuan.dear.focus.hr.bindingAdapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.focus.hr.adapter.HrStaffAdapter;
import com.guyuan.dear.focus.hr.adapter.StaffsByDeptExpListAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.bean.StaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 18:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffsByDeptBindingAdapter {
    @BindingAdapter(value = {"setDeptData","setCallback"},requireAll = false)
    public static void setDeptData(ExpandableListView view, List<HrStaffsByDept> depts, HrStaffAdapter.HrStaffAdapterCallback callback){
        StaffsByDeptExpListAdapter adapter = new StaffsByDeptExpListAdapter(depts,view.getContext());
        view.setAdapter(adapter);
        view.setGroupIndicator(null);
        view.setChildIndicator(null);
        view.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for(int i=0;i<depts.size();i++){
                    if(groupPosition!=i){
                        view.collapseGroup(i);
                    }
                }
            }
        });
        if(callback!=null){
            adapter.setCallback(callback);
        }
    }
}
