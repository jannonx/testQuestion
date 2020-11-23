package com.guyuan.dear.work.matterapply.adapter;

import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemWorkMatterApplyBinding;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:26
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyAdapter extends BaseDBRecycleAdapter<Object, ItemWorkMatterApplyBinding> {
    public MatterApplyAdapter(List<Object> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, Object item, int position) {

    }
}