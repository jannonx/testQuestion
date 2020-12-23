package com.guyuan.dear.focus.security.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;
import com.guyuan.dear.focus.security.data.beans.SecurityContentBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 10:43
 * @company : 固远（深圳）信息技术有限公司
 **/
public class SecurityReportAdapter extends BaseRecyclerAdapter<SecurityContentBean> {

    private SecurityListener listener;
    private boolean isHide;

    public SecurityReportAdapter(Context context, @NonNull List<SecurityContentBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, SecurityContentBean item, int position) {
        String imgUrl = item.getImgUrl();
        if (imgUrl != null) {
            List<String> photoList = StringUtils.splitPhotoUrl(imgUrl);
            String url = photoList.size() > 0 ? photoList.get(0) : "";
            GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.danger_head_iv),
                    url, 4);
        }
        TextView control_action = holder.getView(R.id.security_finish_tv);
        if (isHide) {
            control_action.setVisibility(View.GONE);
        }
        holder.getView(R.id.danger_head_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(item.getImgUrl())) {
                    FullScreenShowActivity.start(context, StringUtils.splitPhotoUrl(imgUrl));
                }
            }
        });

        switch (item.getStatus()) {
            case 0://未处理
                control_action.setEnabled(true);
                control_action.setText("执行");
                control_action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.finish(item.getId());
                        }
                    }
                });
                break;

            case 1://已处理
                control_action.setText("已执行");
                control_action.setEnabled(false);
                break;
        }

        holder.setText(R.id.danger_report_person_tv, item.getCreateName());
        holder.setText(R.id.danger_time_tv, item.getCreateTime());

        SecurityBaseBean baseBean = item.getTSecurityBaseVo();
        if (baseBean != null) {
            holder.setText(R.id.danger_head_tv, baseBean.getName());
            holder.setText(R.id.danger_code_tv, baseBean.getCode());
            holder.setText(R.id.danger_position_tv, baseBean.getWorkshopName());
            holder.setText(R.id.danger_person_tv, baseBean.getDutyName());
        }

    }

    public void hideFinishBtn(boolean isHide) {
        this.isHide = isHide;
    }


    public void setListener(SecurityListener listener) {
        this.listener = listener;
    }

    public interface SecurityListener {
        void finish(int id);
    }
}
