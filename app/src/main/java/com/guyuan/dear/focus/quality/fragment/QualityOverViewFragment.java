package com.guyuan.dear.focus.quality.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.focus.quality.data.FocusQualityViewModel;

/**
 * @description: 我的关注--质检--质检概况
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class QualityOverViewFragment extends BaseDataBindingFragment<FragmentFocusAssessOverviewBinding, FocusQualityViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static QualityOverViewFragment newInstance() {
        Bundle args = new Bundle();
        QualityOverViewFragment fragment = new QualityOverViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_overview;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
