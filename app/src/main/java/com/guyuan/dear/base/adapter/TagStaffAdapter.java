package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.view.flowlayout.FlowLayout;
import com.guyuan.dear.utils.view.flowlayout.TagAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 员工列表adapter
 * @author:Jannonx
 * @date: 2020/6/4 10:10
 */
public class TagStaffAdapter extends TagAdapter<StaffBean> {
    private Context mContext;
    private boolean isDeleteFlag = true;

    public TagStaffAdapter(Context context, List<StaffBean> dataList, boolean isFlag) {
        this(context, dataList);
        isDeleteFlag = isFlag;
    }

    public TagStaffAdapter(Context context, List<StaffBean> dataList) {
        this(dataList);
        mContext = context;
    }

    private TagStaffAdapter(List<StaffBean> dataList) {
        super(dataList);
    }

    @Override
    public View getView(FlowLayout parent, int position, StaffBean staffBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_small_person,
                parent, false);
        TextView tv = view.findViewById(R.id.item_deletable_tag_tv_name);
        ImageView iv = view.findViewById(R.id.item_deletable_tag_iv_remove);
        ImageView icon = view.findViewById(R.id.item_deletable_tag_iv_icon);
        iv.setVisibility(isDeleteFlag ? View.VISIBLE : View.GONE);
        tv.setText(staffBean.getName());
        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(icon, staffBean.getImgUrl());
        iv.setOnClickListener(new View.OnClickListener() {
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
