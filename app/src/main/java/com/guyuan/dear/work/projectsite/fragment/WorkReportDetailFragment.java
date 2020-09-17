package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.databinding.FragmentWorkReportDetailBinding;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkReportDetailFragment extends BaseDataBindingFragment<FragmentWorkReportDetailBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static WorkReportDetailFragment newInstance() {
        Bundle args = new Bundle();
        WorkReportDetailFragment fragment = new WorkReportDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_report_detail;
    }

    @Override
    protected void initialization() {

    }
}
