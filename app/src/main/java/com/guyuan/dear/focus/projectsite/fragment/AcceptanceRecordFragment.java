package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentAcceptanceRecordBinding;
import com.guyuan.dear.databinding.FragmentCheckSafeContentBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面--到货清单
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class AcceptanceRecordFragment extends BaseDataBindingFragment<FragmentAcceptanceRecordBinding, FocusProjectSiteViewModel> {

    public static final String TAG = AcceptanceRecordFragment.class.getSimpleName();


    public static AcceptanceRecordFragment newInstance() {
        Bundle bundle = new Bundle();
        AcceptanceRecordFragment fragment = new AcceptanceRecordFragment();
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


    }


    private void setProduceData(FocusProduceBean data) {


    }

    @Override
    protected int getVariableId() {
        return 0;
    }


}
