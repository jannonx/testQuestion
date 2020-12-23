package com.guyuan.dear.focus.assess.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.databinding.ItemFocusAssessListBinding;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;

import java.util.List;

/**
 * @author : tl
 * @description :评审列表adapter
 * @since: 2020/10/21 11:44
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessListAdapter extends BaseDBRecycleAdapter<AssessListBean.ContentBean, ItemFocusAssessListBinding> {


    public AssessListAdapter(List<AssessListBean.ContentBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseDBRecycleAdapter.Holder holder, AssessListBean.ContentBean item, int position) {
        holder.binding.setVariable(BR.assessListContentBean, item);
    }
}