package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentAcceptanceRecordBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;


/**
 * @description: 我的关注--工程现场--客户验收报告--详情页面--验收记录
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class AcceptanceRecordFragment extends BaseDataBindingFragment<FragmentAcceptanceRecordBinding, FocusProjectSiteViewModel> {

    public static final String TAG = AcceptanceRecordFragment.class.getSimpleName();
    private SiteExploreBean detailProjectData;

    public static AcceptanceRecordFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        AcceptanceRecordFragment fragment = new AcceptanceRecordFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_acceptance_record;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }

        detailProjectData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);

        binding.tvRecorder.setText(detailProjectData.getCheckName());
        binding.tvRemark.setText(detailProjectData.getCheckRemark());

    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
