package com.guyuan.dear.work.produce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 管理者审批适配器
 * @author: 许建宁
 * @date: 2020/6/4 10:10
 */
public class AdminApprovalAdapter extends TagAdapter<StaffBean> {
    private Context mContext;
    private boolean isDeleteFlag = true;

    public AdminApprovalAdapter(Context context, List<StaffBean> dataList, boolean isFlag) {
        this(context, dataList);
        isDeleteFlag = isFlag;
    }

    public AdminApprovalAdapter(Context context, List<StaffBean> dataList) {
        this(dataList);
        mContext = context;
    }

    private AdminApprovalAdapter(List<StaffBean> dataList) {
        super(dataList);
    }

    @Override
    public View getView(FlowLayout parent, int position, StaffBean staffBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_admin_approval,
                parent, false);
        TextView name = view.findViewById(R.id.tv_name);
        ImageView deleteBtn = view.findViewById(R.id.iv_delete);
        ImageView avatar = view.findViewById(R.id.iv_avatar);
        deleteBtn.setVisibility(isDeleteFlag ? View.VISIBLE : View.GONE);
        name.setText(staffBean.getName());
        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(avatar, staffBean.getImgUrl());
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagDatas.remove(position);
                notifyDataChanged();
            }
        });
        return view;
    }


    @Override
    public ArrayList<StaffBean> getTagDataList() {
        return (ArrayList<StaffBean>) super.getTagDataList();
    }
}
