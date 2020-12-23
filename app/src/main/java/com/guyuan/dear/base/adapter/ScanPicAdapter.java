package com.guyuan.dear.base.adapter;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.base.bean.PicBean;
import com.guyuan.dear.databinding.ItemPicScanBinding;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/3 12:14
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ScanPicAdapter extends BaseDBRecycleAdapter<PicBean, ItemPicScanBinding> {
    public ScanPicAdapter(List<PicBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, PicBean item, int position) {
        holder.binding.setVariable(BR.picBean, item);
    }

}