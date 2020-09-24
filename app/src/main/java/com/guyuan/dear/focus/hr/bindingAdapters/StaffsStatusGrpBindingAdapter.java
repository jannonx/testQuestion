package com.guyuan.dear.focus.hr.bindingAdapters;

import android.widget.ExpandableListView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.focus.hr.adapter.StaffsDeptGrpExpListAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.bean.StaffBasicInfo;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 18:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffsStatusGrpBindingAdapter {
    @BindingAdapter(value = {"setDeptData", "setCallback"}, requireAll = false)
    public static void setDeptData(ExpandableListView view, List<HrStaffsByDept> depts, StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback callback) {
        StaffsDeptGrpExpListAdapter adapter = new StaffsDeptGrpExpListAdapter(depts, view.getContext());
        view.setAdapter(adapter);
        view.setGroupIndicator(null);
        view.setChildIndicator(null);
        view.setDividerHeight(0);
        view.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < depts.size(); i++) {
                    if (groupPosition != i) {
                        view.collapseGroup(i);
                    }
                }
            }
        });
        if (callback != null) {
            adapter.setCallback(new StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback() {
                @Override
                public void onClickLoadMore(int grpType, long deptId, int pageStartIndex, int pageSize, int grpPos) {
                    callback.onClickLoadMore(grpType, deptId, pageStartIndex, pageSize, grpPos);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.expandGroup(grpPos,true);
                            view.smoothScrollToPosition(grpPos);
                        }
                    },4020);
                }

                @Override
                public void onClickStaff(StaffBasicInfo bean) {
                    callback.onClickStaff(bean);

                }
            });
        }
    }
}
