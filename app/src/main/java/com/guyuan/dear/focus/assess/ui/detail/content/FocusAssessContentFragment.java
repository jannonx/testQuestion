package com.guyuan.dear.focus.assess.ui.detail.content;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.FragmentFocusAssessContentBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessContentFragment extends BaseDataBindingFragment<FragmentFocusAssessContentBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusAssessContentFragment";
    public static final String DETAIL = "detail";

    public static FocusAssessContentFragment newInstance(AssessDetailBean detailBean) {

        Bundle args = new Bundle();
        args.putParcelable(DETAIL, detailBean);
        FocusAssessContentFragment fragment = new FocusAssessContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_content;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            AssessDetailBean detailBean = getArguments().getParcelable(DETAIL);
            if (detailBean != null) {
                binding.setVariable(BR.focusAssessDetailBean, detailBean);
                initTab(detailBean);
            }
        }
    }

    private void initTab(AssessDetailBean detailBean) {
        List<Fragment> fragmentList = new ArrayList<>();
        if (detailBean.getAuditFormResultVOList() != null) {
            FocusAssessResultFragment resultFragment = FocusAssessResultFragment.newInstance();
            fragmentList.add(resultFragment);
        }

        if (detailBean.getAuditContentList() != null) {
            FocusAssessPointFragment pointFragment = FocusAssessPointFragment.newInstance();
            fragmentList.add(pointFragment);
        }

        new TabLayoutHelper(getActivity(), binding.assessContentTl, binding.assessResultVp,
                fragmentList, TabLayoutHelper.UNDERLINE);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}