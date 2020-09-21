package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.ui.detail.FocusDeviceDetailActivity;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;
import java.util.List;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class DeviceExceptionAdapter extends BaseRecyclerAdapter<DeviceExceptionBean.ContentBean> {

    public DeviceExceptionAdapter(Context context,
                                  @NonNull List<DeviceExceptionBean.ContentBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder,
                                  DeviceExceptionBean.ContentBean item, int position) {
        if (item != null) {
            String imgUrl = item.getImgUrl();
            if (imgUrl != null) {
                List<String> photoList = StringUtils.splitPhotoUrl(imgUrl);
                String url = photoList.size() > 0 ? photoList.get(0) : "";
                GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.fix_iv), url, 4);
            }

            holder.setText(R.id.fix_number_tv, item.getEquipmentCode());
            holder.setText(R.id.fix_name_tv, item.getVequipmentName());
            holder.setText(R.id.fix_name_exception_reason_tv, item.getExExplain());
            holder.setText(R.id.fix_name_person, item.getVrepairBy());
            holder.setText(R.id.fix_person_tv, item.getVcreateBy());
            holder.setText(R.id.fix_time, item.getExTime());

            holder.getView(R.id.real_time_exception_cv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FocusDeviceDetailActivity.class);
                    intent.putExtra(FocusDeviceDetailActivity.TYPE, FocusDeviceDetailActivity.EXCEPTION);
                    intent.putExtra(FocusDeviceDetailActivity.CONTENT, item);
                    context.startActivity(intent);
                }
            });
        }

    }

}
