package com.guyuan.dear.focus.assess.ui.detail;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.FragmentFocusAssessDetailBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.focus.assess.ui.detail.content.FocusAssessContentFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:27
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessDetailFragment extends BaseDataBindingFragment<FragmentFocusAssessDetailBinding,
        FocusAssessViewModel> {

    public static final String TAG = "FocusAssessDetailFragment";
    public static final String CONTRACT_NUMBER = "contractNumber";

    public static FocusAssessDetailFragment newInstance(int id, String contractNumber) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_ID, id);
        args.putString(CONTRACT_NUMBER, contractNumber);
        FocusAssessDetailFragment fragment = new FocusAssessDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_detail;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            int id = getArguments().getInt(ConstantValue.KEY_ID, 0);
            String contractNumber = getArguments().getString(CONTRACT_NUMBER);
            if (viewModel != null) {
                viewModel.getAssessDetail(id, contractNumber);
            }

        }
    }

    public void initTab(List<AssessDetailBean> assessDetailBeans) {
        List<Fragment> contentFragment = new ArrayList<>();
        if (assessDetailBeans != null) {
            for (AssessDetailBean detailBean : assessDetailBeans) {
                contentFragment.add(FocusAssessContentFragment.newInstance(detailBean));
            }

            new TabLayoutHelper(getActivity(), binding.focusAssessTl, binding.focusAssessVp,
                    contentFragment, R.layout.tab_blue_under_line);
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}