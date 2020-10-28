package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusReportDetailBinding;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;

/**
 * @description: 我的关注--工程现场--详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusReportDetailFragment extends BaseDataBindingFragment<FragmentFocusReportDetailBinding, FocusProjectSiteViewModel> {

    public static final String TAG = FocusReportDetailFragment.class.getSimpleName();

    public static FocusReportDetailFragment newInstance() {
        Bundle args = new Bundle();
        FocusReportDetailFragment fragment = new FocusReportDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_report_detail;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
