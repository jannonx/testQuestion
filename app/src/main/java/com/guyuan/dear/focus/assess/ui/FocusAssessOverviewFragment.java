package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessOverviewBean;
import com.guyuan.dear.focus.assess.data.bean.OverviewBody;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;
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
public class FocusAssessOverviewFragment extends BaseDataBindingFragment<FragmentFocusAssessOverviewBinding, FocusAssessViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";


    public static FocusAssessOverviewFragment newInstance() {
        Bundle args = new Bundle();
        FocusAssessOverviewFragment fragment = new FocusAssessOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_overview;
    }

    @Override
    protected void initialization() {
        if (viewModel != null) {
            initYear();
            setSearch();
            OverviewBody body = new OverviewBody();
            body.setAuditTime(binding.dateTv.getText().toString());
            viewModel.getAssessOverview(CommonUtils.getCommonRequestBody(body));
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
                        OverviewBody body = new OverviewBody();
                        body.setAuditTime(year);
                        viewModel.getAssessOverview(CommonUtils.getCommonRequestBody(body));
                    }
                });
            }
        });
    }


    private void setSearch() {
        binding.include.findViewById(R.id.layout_search_bar_tv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editable = binding.include.etSearch.getText();
                if (editable != null && !TextUtils.isEmpty(editable)) {
                    FocusAssessListActivity.start(getContext(), editable.toString());
                } else {
                    showToastTip(ConstantValue.TIP_SEARCH);
                }
            }
        });
    }

    @Override
    protected int getVariableId() {
        return BR.assessViewModel;
    }
}
