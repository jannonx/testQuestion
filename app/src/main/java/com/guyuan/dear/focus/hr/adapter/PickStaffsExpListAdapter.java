package com.guyuan.dear.focus.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemPickStaffsExpListChildBinding;
import com.guyuan.dear.databinding.ItemPickStaffsExpListParentBinding;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/4 11:10
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsExpListAdapter extends BaseExpandableListAdapter {

    private List<PickStaffsExpParentBean> list;
    private Context context;

    public PickStaffsExpListAdapter(List<PickStaffsExpParentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getStaffs().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupPosition * 10000 + childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition * 10000 + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemPickStaffsExpListParentBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_pick_staffs_exp_list_parent, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else {
            binding = (ItemPickStaffsExpListParentBinding) convertView.getTag();
        }

        binding.setData(list.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemPickStaffsExpListChildBinding binding = null;
        if(convertView==null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_pick_staffs_exp_list_child,parent,false);
            convertView =binding.getRoot();
            convertView.setTag(binding);
        }else {
            binding = (ItemPickStaffsExpListChildBinding) convertView.getTag();
        }
        PickStaffBean bean = list.get(groupPosition).getStaffs().get(childPosition);
        binding.setData(bean);
        binding.itemPickStaffsExpListChildCbxSelectStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setPick(!bean.isPick());
                notifyDataSetChanged();
                if(itemCallback!=null){
                    itemCallback.onTogglePickStaff(groupPosition,childPosition,bean);
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    public interface ItemCallback{
        void onTogglePickStaff(int grpPos, int childPos, PickStaffBean item);
    }
}
