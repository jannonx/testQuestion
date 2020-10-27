package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:55
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessOverviewFragment extends BaseDataBindingFragment<FragmentFocusAssessOverviewBinding> {

    public static final String TAG = "FocusAssessOverviewFrag";
    private FocusAssessViewModel viewModel;

    public static FocusAssessOverviewFragment newInstance() {
        Bundle args = new Bundle();
        FocusAssessOverviewFragment fragment = new FocusAssessOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusAssessActivity assessActivity = (FocusAssessActivity) context;
        viewModel = assessActivity.getViewModel();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_overview;
    }

    @Override
    protected void initialization() {
        if (viewModel != null) {
            initYear();
            viewModel.getAssessOverview(binding.dateTv.getText().toString());
        }
    }

    private void initYear() {
        binding.dateTv.setText(CalenderUtils.getInstance().getCurrentYearByString());
        binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.pickYear(getContext(), "请选择查询年份", new AlertDialogUtils.YearStringListener() {
                    @Override
                    public void onSelected(String year) {
                        binding.dateTv.setText(year);
                        viewModel.getAssessOverview(year);
                    }
                });
            }
        });
    }

    public void setUI(AssessOverviewBean assessOverviewBean) {
        binding.setVariable(BR.AssessOverviewBean, assessOverviewBean);
    }


}
