package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.projectsite.type.CheckSafeSatisfyType;
import com.guyuan.dear.focus.projectsite.type.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.type.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.type.SingleItemResultType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.projectsite.type.SiteProjectSatisfyType;
import com.guyuan.dear.utils.GlideUtils;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

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
//        LogUtils.showLog("getResultType=" + item.getResultType().getDes());
        View lineBottom = holder.getView(R.id.line_bottom);
        lineBottom.setVisibility(listData.size() - 1 == position ? View.GONE : View.VISIBLE);

        FunctionModuleType moduleType = siteExploreBean.getModuleType();

        //勘查中，排查中
        SiteProjectSatisfyType siteProjectSatisfyType = siteExploreBean.getSiteProjectSatisfyType();
        CheckSafeSatisfyType checkSafeSatisfyType = siteExploreBean.getCheckSafeSatisfyType();
        boolean isSiteExplore = (SiteProjectSatisfyType.TYPE_EXPLORE_WAIT == siteProjectSatisfyType
                || SiteProjectSatisfyType.TYPE_EXPLORE_ING == siteProjectSatisfyType);
        boolean isCheckSafe = checkSafeSatisfyType == CheckSafeSatisfyType.TYPE_CHECK_WAIT
                || checkSafeSatisfyType == CheckSafeSatisfyType.TYPE_CHECK_ING;
        LinearLayout clConfirm = holder.getView(R.id.rl_confirm);
        //我的关注
        if (moduleType == FunctionModuleType.TYPE_FOCUS) {
//            LogUtils.showLog("11111");
            imageView.setVisibility(View.VISIBLE);
            clConfirm.setVisibility(View.GONE);
            //我的工作--勘查中/排查中
        } else if (isSiteExplore || isCheckSafe) {
//            LogUtils.showLog("22222");
            clConfirm.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        } else {
//            LogUtils.showLog("33333");
            imageView.setVisibility(View.VISIBLE);
            clConfirm.setVisibility(View.GONE);
        }

        if (clConfirm.getVisibility() == View.GONE) return;
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
