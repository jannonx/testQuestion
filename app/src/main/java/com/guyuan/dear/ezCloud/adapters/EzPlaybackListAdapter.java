package com.guyuan.dear.ezCloud.adapters;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemEzPlaybackListBinding;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 20:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class EzPlaybackListAdapter extends BaseDBRecycleAdapter<EZDeviceRecordFile, ItemEzPlaybackListBinding> {
    public EzPlaybackListAdapter(List<EZDeviceRecordFile> listData) {
        super(listData, R.layout.item_ez_playback_list);
    }

    @Override
    protected void bindDataToView(Holder holder, EZDeviceRecordFile item, int position) {
        holder.binding.setData(item);
    }
}
