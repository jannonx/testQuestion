package com.guyuan.dear.work.assess.adapter;

import com.guyuan.dear.BR;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemWorkAssessBinding;
import com.guyuan.dear.work.assess.data.bean.WorkAssessListBean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 19:44
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessAdapter extends BaseDBRecycleAdapter<WorkAssessListBean, ItemWorkAssessBinding> {

    public WorkAssessAdapter(List<WorkAssessListBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, WorkAssessListBean item, int position) {
        holder.binding.setVariable(BR.workAssessListBean, item);
    }
}