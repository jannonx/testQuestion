package com.guyuan.dear.focus.assess.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
 * @description :我的关注-评审详情fragment
 * @since: 2020/10/21 10:27
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessDetailFragment extends BaseDataBindingFragment<FragmentFocusAssessDetailBinding,
        FocusAssessViewModel> implements TabLayoutHelper.TabLayoutListener {

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
        List<Fragment> contentFragmentList = new ArrayList<>();
        if (assessDetailBeans != null) {
            for (AssessDetailBean detailBean : assessDetailBeans) {
                contentFragmentList.add(FocusAssessContentFragment.newInstance(detailBean));
            }

            if (contentFragmentList.size() <= 1) {
                binding.focusAssessTl.setVisibility(View.GONE);
            }
            new TabLayoutHelper(getActivity(), binding.focusAssessTl, binding.focusAssessVp,
                    contentFragmentList, TabLayoutHelper.UNDERLINE).setTab().setListener(this).setCustomView();
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


    @Override
    public void setCustomContent(View customView, int currentPosition) {
        TextView tv = customView.findViewById(R.id.tab_tv);
        tv.setText("第" + (currentPosition + 1) + "次评审");
    }
}