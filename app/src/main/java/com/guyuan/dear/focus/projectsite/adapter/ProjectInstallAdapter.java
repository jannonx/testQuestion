package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.type.InstallDebugSatisfyInnerType;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @description: 2020/11/17 12:26
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectInstallAdapter extends BaseRecyclerAdapter<InstallDebugBean> {
    public ProjectInstallAdapter(Context context, @NonNull List<InstallDebugBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, InstallDebugBean item,
                                  int position) {
        holder.setText(R.id.tv_project_name, item.getProjectName());
        holder.setText(R.id.tv_check_person, item.getCustomerName());

        holder.setText(R.id.tv_time, item.getShowTime());
        //状态属性设置
        holder.setText(R.id.tv_project_status, InstallDebugSatisfyInnerType.toType(item).getDes());
        TextView tvStatus = holder.getView(R.id.tv_project_status);
        //可见
//        tvStatus.setVisibility(InstallDebugSatisfyInnerType.toType(item.getStatus()).getStatusTextVisible() ? View.VISIBLE : View.GONE);
        tvStatus.setBackgroundResource(InstallDebugSatisfyInnerType.toType(item).getTextBgColor());
        int statusTextColor = InstallDebugSatisfyInnerType.toType(item).getTextColor();
        tvStatus.setTextColor(context.getResources().getColor(statusTextColor));

    }
}
