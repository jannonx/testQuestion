package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 现场勘察点/安全排查点适配器
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */

public class CheckContentAdapter extends BaseRecyclerAdapter<ProjectSiteOpinionBean> {
    private ProjectReportType projectReportType;


    public CheckContentAdapter(Context context, @NonNull List<ProjectSiteOpinionBean> listData, int layoutID, ProjectReportType projectReportType) {
        super(context, listData, layoutID);
        this.projectReportType = projectReportType;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProjectSiteOpinionBean item,
                                  int position) {
        holder.setText(R.id.label_explore_point, (ProjectReportType.TYPE_SITE_EXPLORATION == projectReportType
                ? "勘察点" : "排查点") + (position + 1));

        holder.setText(R.id.tv_content, item.getName());

        ImageView imageView = holder.getView(R.id.image_view);
        GlideUtils.getInstance().loadLocalImage(imageView, item.getResultType().getImageView());

        View lineBottom = holder.getView(R.id.line_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);
    }
}
