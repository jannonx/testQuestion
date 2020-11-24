package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SingleItemResultType;
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
    private SiteExploreBean siteExploreBean;


    public CheckContentAdapter(Context context, @NonNull List<ProjectSiteOpinionBean> listData, int layoutID, SiteExploreBean siteExploreBean) {
        super(context, listData, layoutID);
        this.siteExploreBean = siteExploreBean;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ProjectSiteOpinionBean item,
                                  int position) {
        ProjectReportType projectReportType = siteExploreBean.getProjectReportType();
        holder.setText(R.id.label_explore_point, (ProjectReportType.TYPE_SITE_EXPLORATION == projectReportType
                ? "勘察点" : "排查点") + (position + 1));

        holder.setText(R.id.tv_content, item.getName());

        ImageView imageView = holder.getView(R.id.image_view);
        GlideUtils.getInstance().loadLocalImage(imageView, item.getResultType().getImageView());

        View lineBottom = holder.getView(R.id.line_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);

        ProjectModuleType moduleType = siteExploreBean.getModuleType();
        //我的关注
        imageView.setVisibility(moduleType == ProjectModuleType.TYPE_FOCUS ? View.VISIBLE : View.GONE);
        LinearLayout clConfirm = holder.getView(R.id.rl_confirm);
        clConfirm.setVisibility(moduleType == ProjectModuleType.TYPE_FOCUS ? View.GONE : View.VISIBLE);

        if (moduleType == ProjectModuleType.TYPE_FOCUS) return;

        //我的工作
        AppCompatImageView ivRightBtn = holder.getView(R.id.iv_right_btn);
        AppCompatImageView ivWrongBtn = holder.getView(R.id.iv_wrong_btn);

        item.setSingleItemResult(SingleItemResultType.TYPE_PASS.getCode());
        ivRightBtn.setImageResource(R.mipmap.right);
        ivWrongBtn.setImageResource(R.mipmap.ic_wrong_gray_unselect);

        View viewBottom = holder.getView(R.id.line_bottom);
        viewBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);
        ivRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSingleItemResult(SingleItemResultType.TYPE_PASS.getCode());
                ivRightBtn.setImageResource(R.mipmap.right);
                ivWrongBtn.setImageResource(R.mipmap.ic_wrong_gray_unselect);
            }
        });
        ivWrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSingleItemResult(SingleItemResultType.TYPE_REJECT.getCode());
                ivRightBtn.setImageResource(R.mipmap.ic_right_gray_unselect);
                ivWrongBtn.setImageResource(R.mipmap.wrong);
            }
        });
    }
}
