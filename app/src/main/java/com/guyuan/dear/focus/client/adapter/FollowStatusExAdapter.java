package com.guyuan.dear.focus.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.widget.AppCompatTextView;


import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.R;
import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/27 14:50
 */
public class FollowStatusExAdapter extends BaseExpandableListAdapter {
  private List<CommentsBean> mList;
  private Context mContext;
  private int expandGrpPos = -1;
  private ChildItemClickListener childItemClickListener;

  public FollowStatusExAdapter(Context context, List<CommentsBean> list) {
    this.mList = list;
    this.mContext = context;
  }

  @Override
  public int getGroupCount() {
    return mList.size();
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return mList.get(groupPosition).getBusinessDetails().size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return mList.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return mList.get(groupPosition).getBusinessDetails().get(childPosition);
//    DepartmentDataBean group = (DepartmentDataBean) getGroup(groupPosition);
//    return group.getStaffs().get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return groupPosition * 100 + childPosition;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                           ViewGroup parent) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_follow_status_parent, null);
//    AppCompatTextView tvDeptName = view.findViewById(R.id.tv_title);
    //AppCompatTextView tvStaffNumber = view.findViewById(R.id.tv_number);
    //AppCompatImageView ivArrow = view.findViewById(R.id.iv_arrow_next);

    CommentsBean bean = mList.get(groupPosition);

//    tvDeptName.setText(bean.getLabel());
    //tvStaffNumber.setText(bean.getCount() + "");
    //ivArrow.setSelected(expandGrpPos == groupPosition);

    return view;
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
    expandGrpPos = groupPosition;
    notifyDataSetChanged();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
    if (expandGrpPos == groupPosition) {
      expandGrpPos = -1;
      notifyDataSetChanged();
    }
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                           View convertView, ViewGroup parent) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_follow_status_child, null);

    AppCompatTextView tvName = view.findViewById(R.id.tv_title);
    //AppCompatTextView tvLocation = view.findViewById(R.id.tv_department);
    //AppCompatTextView tvNumber = view.findViewById(R.id.tv_number);


    CommentsBean staff = mList.get(groupPosition).getBusinessDetails().get(childPosition);

//    tvName.setText(staff);
    //tvLocation.setText(staff.getDeptName());
    //tvNumber.setText(staff.getSingleLateCount() + " 次");
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (childItemClickListener != null) {
//          childItemClickListener.onChildClicked(staff);
        }
      }
    });

    return view;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }

  public interface ChildItemClickListener {
    void onChildClicked(String bean);
  }

  public void setChildItemClickListener(ChildItemClickListener childItemClickListener) {
    this.childItemClickListener = childItemClickListener;
  }
}
