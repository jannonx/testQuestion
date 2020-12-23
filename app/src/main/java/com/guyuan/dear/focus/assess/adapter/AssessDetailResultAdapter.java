package com.guyuan.dear.focus.assess.adapter;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.databinding.ItemFocusAssessDetailResultBinding;
import com.guyuan.dear.focus.assess.data.bean.AuditFormResultBean;

import java.util.List;

/**
 * @author : tl
 * @description :评审结果adapter
 * @since: 2020/10/21 14:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessDetailResultAdapter extends BaseDBRecycleAdapter<AuditFormResultBean,
        ItemFocusAssessDetailResultBinding> {

    public AssessDetailResultAdapter(List<AuditFormResultBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, AuditFormResultBean item, int position) {
        holder.binding.setVariable(BR.focusAssessResultBean, item);
    }
}