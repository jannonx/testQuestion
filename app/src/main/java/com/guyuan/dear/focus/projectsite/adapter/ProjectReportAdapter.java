package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 2020/11/17 12:26
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectReportAdapter extends BaseRecyclerAdapter<SiteExploreBean> {

    private ProjectReportType reportType;

    public ProjectReportAdapter(Context context, @NonNull List<SiteExploreBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    public ProjectReportAdapter(Context context, @NonNull List<SiteExploreBean> listData, int layoutID, ProjectReportType type) {
        super(context, listData, layoutID);
        this.reportType = type;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, SiteExploreBean item,
                                  int position) {
        //==================工程现场
        holder.setText(R.id.tv_project_name, item.getProjectName());
        holder.setText(R.id.tv_project_code, item.getProjectNum());
        //状态属性设置
        holder.setText(R.id.tv_project_status, item.getStatusText());
        TextView tvStatus = holder.getView(R.id.tv_project_status);
        //可见
        tvStatus.setVisibility(item.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        tvStatus.setBackgroundResource(item.getStatusTextBg());
        int statusTextColor = item.getStatusTextColor();
        tvStatus.setTextColor(context.getResources().getColor(statusTextColor));

        TextView labelCheckPerson = holder.getView(R.id.label_project_number);

        holder.setText(R.id.tv_project_number, item.getName());

        TextView labelDestination = holder.getView(R.id.label_operator);
        labelDestination.setText("目的地：");
        holder.setText(R.id.tv_operator, item.getDestination());
        holder.setText(R.id.tv_time, item.getCreateTime());


        switch (reportType) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                labelCheckPerson.setText("勘查人员：");
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                labelCheckPerson.setText("安全人员：");
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                break;

            default:
        }

    }
}
