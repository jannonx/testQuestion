package com.guyuan.dear.work.contractPause.adapters;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ItemMyApplyListBinding;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListAdapter extends BaseDBRecycleAdapter<MyApplyBean, ItemMyApplyListBinding> {
    private boolean isShowCauseType;
    public MyApplyListAdapter(List<MyApplyBean> listData,boolean isShowCauseType) {
        super(listData, R.layout.item_my_apply_list);
        this.isShowCauseType = isShowCauseType;
    }

    @Override
    protected void bindDataToView(Holder holder, MyApplyBean item, int position) {
        holder.binding.setData(item);
        holder.binding.setIsShowCauseType(isShowCauseType);
    }

}
