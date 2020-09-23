package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.bean.StaffBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 14:35
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffsDeptGrpExpListAdapter extends BaseExpandableListAdapter {
    private List<HrStaffsByDept> groupList;
    private Context context;

    public StaffsDeptGrpExpListAdapter(List<HrStaffsByDept> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        if(groupList==null){
            return 0;
        }
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).getStaffs();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        HrStaffsByDept group = groupList.get(groupPosition);
        ParentViewHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hr_staffs_by_dept_group_view,parent,false);
            holder = new ParentViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ParentViewHolder) convertView.getTag();
        }
        holder.tvDeptName.setText(group.getGrpLabel());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        HrStaffsByDept group = groupList.get(groupPosition);
        List<StaffBean> staffs = group.getStaffs();
        ChildViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hr_staffs_by_dept_child_view,parent,false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        initChildView(holder,staffs,group.getGrpType(),group.getDeptId(),groupPosition);
        return convertView;
    }

    private void initChildView(ChildViewHolder holder, List<StaffBean> staffs, int grpType, long deptId, int groupPosition) {
        BaseRecyclerView recyclerView = holder.recyclerView;
        GridLayoutManager layoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        HrStaffAdapter adapter = new HrStaffAdapter(context,staffs,grpType,deptId);
        BaseRecyclerViewAdapter adapterWrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(adapterWrapper);
        recyclerView.setLoadMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        adapter.setCallback(new HrStaffAdapter.HrStaffAdapterCallback() {
            @Override
            public void onClickLoadMore(int grpType, long deptId, int pageStartIndex, int pageSize, int position) {
                if(callback!=null){
                    callback.onClickLoadMore(grpType,deptId, pageStartIndex, pageSize, groupPosition);
                }
            }

            @Override
            public void onClickStaff(StaffBean item) {
                if(callback!=null){
                    callback.onClickStaff(item);
                }
            }
        });
    }

    private DeptGrpExpAdapterCallback callback;

    public void setCallback(DeptGrpExpAdapterCallback callback) {
        this.callback = callback;
    }


    public interface DeptGrpExpAdapterCallback{
        void onClickLoadMore(int grpType, long deptId, int pageStartIndex, int pageSize, int grpPos);
        void onClickStaff(StaffBean bean);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ParentViewHolder{
        private AppCompatTextView tvDeptName;
        private AppCompatImageView ivIndicator;

        public ParentViewHolder(View convertView) {
            tvDeptName = convertView.findViewById(R.id.item_hr_staffs_by_dept_group_view_tv_dept_name);
            ivIndicator = convertView.findViewById(R.id.item_hr_staffs_by_dept_group_view_iv_indicator);
        }
    }

    private class ChildViewHolder{
        private BaseRecyclerView recyclerView;

        public ChildViewHolder(View convertView) {
            recyclerView = convertView.findViewById(R.id.item_hr_staffs_by_dept_child_view_recycler_view);
        }
    }
}
