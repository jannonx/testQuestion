package com.guyuan.dear.work.device.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.work.device.data.bean.MaintainListBean;

import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * Created by TL
 * on 2019/12/21
 */
public class MaintainListAdapter extends BaseRecyclerAdapter<MaintainListBean> {

    public MaintainListAdapter(Context context, @NonNull List<MaintainListBean> listData,
                               int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, MaintainListBean item,
                                  int position) {

        String imgUrl = item.getImgUrl();
        if (imgUrl != null) {
            List<String> photoList = StringUtils.splitPhotoUrl(item.getImgUrl());
            String url = photoList.size() > 0 ? photoList.get(0) : "";
            GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.control_maintain_device_iv),
                    url, 4);
            holder.getView(R.id.control_maintain_device_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(item.getImgUrl())) {
                        FullScreenShowActivity.start(context, photoList, position);
                    }
                }
            });
        }

        holder.setText(R.id.control_maintain_device_name_tv, item.getEquipmentName());
        holder.setText(R.id.control_maintain_code_tv, item.getMequipmentCode());
        holder.setText(R.id.control_maintain_factory_tv, item.getFactoryName());
        holder.setText(R.id.control_maintain_workShop_tv, item.getWorkshopName());
        holder.setText(R.id.control_maintain_person, item.getMaintenanceByName());
        holder.setText(R.id.control_maintain_type, item.getType());
        holder.setText(R.id.control_maintain_content, item.getDetail());
        holder.setText(R.id.control_maintain_time, item.getMaintainTime());
    }
}
