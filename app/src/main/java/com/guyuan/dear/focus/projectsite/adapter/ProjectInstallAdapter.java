package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyInnerType;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

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
        //没有实际开始，显示预计开始和预计完工
        CalenderUtils calenderUtils = CalenderUtils.getInstance();
        String debugStartTime = calenderUtils.toSmartFactoryDateFormatByFull(item.getDebugStartTime());
        String debugEndTime = calenderUtils.toSmartFactoryDateFormatByFull(item.getDebugEndTime());
        String realStartTime = calenderUtils.toSmartFactoryDateFormatByFull(item.getRealityStartTime());
        String realEndTime = calenderUtils.toSmartFactoryDateFormatByFull(item.getRealityEndTime());
        LogUtils.showLog("debugStartTime=" + debugStartTime + "...debugEndTime=" + debugEndTime
                + "...realStartTime=" + realStartTime + "...realEndTime=" + realEndTime);
        String showTime = TextUtils.isEmpty(realStartTime) ? (debugStartTime + "~" + debugEndTime) : (realStartTime + "~" + realEndTime);
        holder.setText(R.id.tv_time, showTime);
        //状态属性设置
        holder.setText(R.id.tv_project_status, InstallDebugSatisfyInnerType.toType(item.getStatus()).getDes());
        TextView tvStatus = holder.getView(R.id.tv_project_status);
        //可见
//        tvStatus.setVisibility(InstallDebugSatisfyInnerType.toType(item.getStatus()).getStatusTextVisible() ? View.VISIBLE : View.GONE);
        tvStatus.setBackgroundResource(InstallDebugSatisfyInnerType.toType(item.getStatus()).getTextBgColor());
        int statusTextColor = InstallDebugSatisfyInnerType.toType(item.getStatus()).getTextColor();
        tvStatus.setTextColor(context.getResources().getColor(statusTextColor));

    }
}
