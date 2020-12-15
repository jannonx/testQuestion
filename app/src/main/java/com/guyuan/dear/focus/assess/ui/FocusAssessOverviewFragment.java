package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.SearchBar;
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
 * @description :我的关注-评审-概览fragment
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

        binding.totalCardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = viewModel.assessOverviewBean.getValue().getSellOrderSumNumber();
                if (total > 0) {
                    FocusAssessListActivity.start(getContext(), "评审总次数列表", "", FocusAssessListFragment.TOTAL);
                }
            }
        });

        binding.notPassCvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notPass = viewModel.assessOverviewBean.getValue().getNoPassSumNumber();
                if (notPass > 0) {
                    FocusAssessListActivity.start(getContext(), "评审不通过列表", "", FocusAssessListFragment.NOT_PASS);
                }
            }
        });

        binding.passCvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pass = viewModel.assessOverviewBean.getValue().getPassSumNumber();
                if (pass > 0) {
                    FocusAssessListActivity.start(getContext(), "评审通过列表", "", FocusAssessListFragment.PASS);
                }
            }
        });
    }


    private void setSearch() {
        binding.searchBar.setHint("输入客户名称、合同编号");
        binding.searchBar.setSearchListener(new SearchBar.OnSearchListener() {
            @Override
            public void onSearch(String searchContent) {
                if (!TextUtils.isEmpty(searchContent)) {
                    FocusAssessListActivity.start(getContext(), "评审列表", searchContent, FocusAssessListFragment.TOTAL);
                } else {
                    showToastTip(ConstantValue.TIP_SEARCH);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int getVariableId() {
        return BR.assessViewModel;
    }
}
