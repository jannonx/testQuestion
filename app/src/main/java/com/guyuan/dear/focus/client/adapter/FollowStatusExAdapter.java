package com.guyuan.dear.focus.client.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;


import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.view.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/10/27 10:58
 */
public class FollowStatusExAdapter extends BaseExpandableListAdapter {
    private List<CommentsBean> mList;
    private Context mContext;
    private int expandGrpPos = -1;
    private ChildItemClickListener childItemClickListener;
    private final LayoutInflater mInflater;
    private boolean isCommentBtnVisible;

    public FollowStatusExAdapter(Context context, List<CommentsBean> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        LogUtils.showLog("getChildrenCount..+" + groupPosition + "=" + mList.get(groupPosition).getBusinessDetails().size());
        return mList.get(groupPosition).getBusinessDetails().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getBusinessDetails().get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_follow_status_parent, parent, false);
        }

        RoundedImageView ivAvatar = convertView.findViewById(R.id.iv_avatar);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvTime = convertView.findViewById(R.id.tv_time);
        TextView tvComments = convertView.findViewById(R.id.tv_comment);
        TextView tvDepartment = convertView.findViewById(R.id.tv_department);
        TextView tvRemarkBtn = convertView.findViewById(R.id.tv_remark_on);


        CommentsBean bean = mList.get(groupPosition);
        LogUtils.showLog("isCommentBtnVisible=" + isCommentBtnVisible);
        tvRemarkBtn.setVisibility(isCommentBtnVisible ? View.VISIBLE : View.GONE);
        tvRemarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childItemClickListener != null) {
                    childItemClickListener.onCommentClicked(bean);
                }
            }
        });


        GlideUtils.getInstance().loadUrlImage(ivAvatar, bean.getImgUrl());
        tvName.setText(bean.getCreateName());
        tvTime.setText(bean.getCreateTime());
        tvDepartment.setText(bean.getDepartmentName());
        tvComments.setText(bean.getContent());


        return convertView;
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_follow_status_child, parent, false);
        }

        RoundedImageView ivAvatar = convertView.findViewById(R.id.iv_avatar);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvTime = convertView.findViewById(R.id.tv_time);
        TextView tvComments = convertView.findViewById(R.id.tv_comment);
        TextView tvDepartment = convertView.findViewById(R.id.tv_department);


        CommentsBean bean = mList.get(groupPosition).getBusinessDetails().get(childPosition);
        LogUtils.showLog(groupPosition + "-" + childPosition + " childBean=" + bean.getContent());
        GlideUtils.getInstance().loadUrlImage(ivAvatar, bean.getImgUrl());
        tvName.setText(bean.getCreateName());
        tvTime.setText(bean.getCreateTime());
        tvComments.setText(bean.getContent());
        tvDepartment.setText(bean.getDepartmentName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setCommentBtnVisible(boolean commentBtnVisible) {
        isCommentBtnVisible = commentBtnVisible;
    }

    public interface ChildItemClickListener {

        void onCommentClicked(CommentsBean bean);
    }

    public void setChildItemClickListener(ChildItemClickListener childItemClickListener) {
        this.childItemClickListener = childItemClickListener;
    }

    public void setNewList(List<CommentsBean> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public void appendDataList(List<CommentsBean> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }
}
