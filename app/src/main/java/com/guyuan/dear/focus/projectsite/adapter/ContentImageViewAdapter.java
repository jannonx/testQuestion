package com.guyuan.dear.focus.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description: 工程现场--图片浏览
 * @author: 许建宁
 * @since: 2020/11/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 */
public class ContentImageViewAdapter extends BaseRecyclerAdapter<String> {


    public ContentImageViewAdapter(Context context, @NonNull List<String> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, String item,
                                  int position) {
        ImageView imageView = holder.getView(R.id.image_view);
        GlideUtils.getInstance().loadUrlImage(imageView, item);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullScreenShowActivity.start(context, listData, position);
            }
        });

    }
}