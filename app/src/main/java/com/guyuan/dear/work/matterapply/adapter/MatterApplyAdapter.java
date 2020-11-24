package com.guyuan.dear.work.matterapply.adapter;

import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemWorkMatterApplyBinding;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:26
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyAdapter extends BaseDBRecycleAdapter<MatterApplyBean, ItemWorkMatterApplyBinding> {
    public MatterApplyAdapter(List<MatterApplyBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, MatterApplyBean item, int position) {
        holder.binding.setVariable(BR.matterApplyBean, item);
    }
}