package com.guyuan.dear.work.device.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.work.device.data.bean.ControlRepairBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

public class Fixadapter extends BaseRecyclerAdapter<ControlRepairBean.RepairEntity> {

    private FixadapterListener listener;

    public Fixadapter(Context context, @NonNull List<ControlRepairBean.RepairEntity> listData,
                      int layoutID) {
        super(context, listData, layoutID);
    }

    public void setListener(FixadapterListener listener) {
        this.listener = listener;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ControlRepairBean.RepairEntity item,
                                  int position) {

        String imgUrl = item.getErrorImgUrl();
        if (imgUrl != null) {
            List<String> photoList = StringUtils.splitPhotoUrl(item.getErrorImgUrl());
            String url = photoList.size() > 0 ? photoList.get(0) : "";

            GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.control_item_iv),
                    url, 4);

            holder.getView(R.id.control_item_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(item.getErrorImgUrl())) {
                        FullScreenShowActivity.start(context, photoList, position);
                    }
                }
            });
        }


        holder.setText(R.id.control_fix_name, item.getEquipmentName());
        holder.setText(R.id.control_position, item.getWorkshopName());
        holder.setText(R.id.control_device_code_tv, item.getEquipmentCode());
        holder.setText(R.id.control_person, item.getUserName());
        holder.setText(R.id.control_type, item.getErrorDetail());
        holder.setText(R.id.control_time, item.getReportTime());
        TextView actionView = holder.getView(R.id.control_action);
        CardView work_device_repair_cv = holder.getView(R.id.work_device_repair_cv);

        int type = item.getStatus();
        switch (type) {
            case 0://未执行
                actionView.setText("执行");
                actionView.setEnabled(true);
                work_device_repair_cv.setClickable(false);
                break;

            case 1://执行中
                work_device_repair_cv.setClickable(false);
                actionView.setText("执行完成");
                actionView.setEnabled(true);
                break;

            case 2://已完成
                work_device_repair_cv.setClickable(true);
                work_device_repair_cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onRepaired(item.getId());
                    }
                });
                actionView.setText("已执行");
                actionView.setEnabled(false);
                break;
        }

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(type, (long) item.getId());
            }
        });
    }

    public interface FixadapterListener {
        void onClick(int type, Long msgID);

        void onRepaired(long deviceID);
    }
}
