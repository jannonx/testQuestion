package com.guyuan.dear.focus.quality.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.databinding.FragmentQualityDetailBinding;
import com.guyuan.dear.work.customerfollow.fragment.CustomerDetailFragment;

/**
 * @description: 我的关注--质检--详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class QualityDetailFragment extends BaseDataBindingFragment<FragmentQualityDetailBinding> {

    public static final String TAG = QualityDetailFragment.class.getSimpleName();

    public static QualityDetailFragment newInstance() {
        Bundle args = new Bundle();
        QualityDetailFragment fragment = new QualityDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_quality_detail;
    }

    @Override
    protected void initialization() {

    }
}
