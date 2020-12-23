package com.guyuan.dear.focus.device.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;

import java.util.List;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * Created by TL
 * on 2019/11/26
 */
public class DeviceItemAdapter extends BaseRecyclerAdapter<EquipmentBean> {

    private FarmDetailListener listener;

    public DeviceItemAdapter(Context context, @NonNull List<EquipmentBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }


    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, EquipmentBean item, int position) {
        EquipmentBean bean = listData.get(position);
        holder.setText(R.id.item_content_tv, bean.getName());

        String imgUrl = bean.getImg();
        if (imgUrl != null) {
            List<String> photoList = StringUtils.splitPhotoUrl(imgUrl);
            String url = photoList.size() > 0 ? photoList.get(0) : "";
            GlideUtils.getInstance().loadImageInRound((ImageView) holder.getView(R.id.item_content_iv), url, 4);
        }

        holder.getView(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(bean);
                }
            }
        });
    }

    public void setListener(FarmDetailListener listener) {
        this.listener = listener;
    }

    public interface FarmDetailListener {
        void onClick(EquipmentBean bean);
    }
}
